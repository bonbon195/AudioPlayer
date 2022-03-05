//package ru.bonbon.audioplayer;
//
//import android.Manifest;
//import android.content.ContentResolver;
//import android.content.pm.PackageManager;
//import android.database.Cursor;
//import android.media.MediaPlayer;
//import android.net.Uri;
//import android.os.Bundle;
//import android.provider.MediaStore;
//import android.util.Log;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.SeekBar;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.app.ActivityCompat;
//import androidx.core.content.ContextCompat;
//import androidx.fragment.app.Fragment;
//
//import java.util.ArrayList;
//
//public class PlayerFragment extends Fragment,
//        SeekBar.OnSeekBarChangeListener {
//
//    TextView position, duration;
//    ImageView rewind, pause, play, forward;
//    SeekBar seekbar;
//    MediaPlayer player;
//    MyThread myThread;
//
//    ArrayList<String> list;
//    final int MY_PERMISSION_REQUEST = 1;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        list = new ArrayList<>();
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
//                != PackageManager.PERMISSION_GRANTED){
//            ActivityCompat.requestPermissions(this, new String[]{
//                    Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSION_REQUEST);}
//        else {
//            getMusic();
//        }
//
//
//        play = findViewById(R.id.play);
//        play.setOnClickListener(this);
//        pause = findViewById(R.id.pause);
//        pause.setOnClickListener(this);
//        seekbar = findViewById(R.id.seekbar);
//        player = MediaPlayer.create(this, R.raw.t);
//        seekbar.setMax(player.getDuration());
//        position = findViewById(R.id.position);
//        duration = findViewById(R.id.duration);
//        duration.setText(timeFormatter(player.getDuration()));
//        seekbar.setOnSeekBarChangeListener(this);
//        myThread = new MyThread();
//        myThread.start();
//        Log.d("MyTag", String.valueOf(list.size()));
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
//                                           @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == MY_PERMISSION_REQUEST) {
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                if (ContextCompat.checkSelfPermission(
//                        this, Manifest.permission.READ_EXTERNAL_STORAGE)
//                        == PackageManager.PERMISSION_GRANTED) {
//                    Toast.makeText(this, "PERMISSION GRANTED", Toast.LENGTH_SHORT)
//                            .show();
//                    getMusic();
//                } else {
//
//                    Toast.makeText(this, "PERMISSION DENIED", Toast.LENGTH_SHORT)
//                            .show();
//                    finish();
//                }
//            }
//        }
//    }
//
//    public void getMusic(){
//        ContentResolver contentResolver = getContentResolver();
//        Uri songUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
//        Cursor songCursor = contentResolver.query(songUri, null, null,
//                null, null);
//
//        if (songCursor != null && songCursor.moveToFirst()){
//            int songTitle = songCursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
//            int songArtist = songCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
//
//            do{
//                String currentTitle = songCursor.getString(songTitle);
//                String currentArtist = songCursor.getString(songArtist);
//                list.add(currentTitle + "\n" + currentArtist);
//            }while (songCursor.moveToNext());
//        }
//    }
//
//    public String timeFormatter(int time){
//        time = time / 1000;
//        int second = time % 60;
//        int minute = time / 60;
//        String result = String.format("%02d:%02d", minute, second);
//        return result;
//    }
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.play:
//                player.start();
//                play.setVisibility(View.GONE);
//                Log.d("MyTag", play.toString());
//                pause.setVisibility(View.VISIBLE);
//                break;
//            case R.id.pause:
//                player.pause();
//                play.setVisibility(View.VISIBLE);
//                pause.setVisibility(View.GONE);
//                break;
//        }
//    }
//
//    @Override
//    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//        position.setText(timeFormatter(progress));
//    }
//
//    @Override
//    public void onStartTrackingTouch(SeekBar seekBar) {
//
//    }
//
//    @Override
//    public void onStopTrackingTouch(SeekBar seekBar) {
//
//    }
//
//    @Override
//    public void onPointerCaptureChanged(boolean hasCapture) {
//
//    }
//
//    class MyThread extends Thread{
//        @Override
//        public void run(){
//            while (true){
//                seekbar.setProgress(player.getCurrentPosition());
//                try {
//                    sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//}