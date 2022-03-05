package ru.bonbon.audioplayer;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends FragmentActivity {
    private static ViewPager2 viewPager;
    private FragmentAdapter adapter;
    private TabLayout tabLayout;
    private final String[] titles = new String[]{"Главная", "Вся музыка", "Плейлисты"};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adapter = new FragmentAdapter(this);
        viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(adapter);
        tabLayout = findViewById(R.id.tab_layout);
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> tab.setText(titles[position])).attach();
    }
}
