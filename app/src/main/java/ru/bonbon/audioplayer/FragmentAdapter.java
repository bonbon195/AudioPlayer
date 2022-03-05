package ru.bonbon.audioplayer;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class FragmentAdapter extends FragmentStateAdapter {
    public FragmentAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new PlayerFragment();
            case 1:
                return new AllTracksListFragment();
            case 2:
                return new PlaylistFragment();
        }
        return new PlayerFragment();
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
