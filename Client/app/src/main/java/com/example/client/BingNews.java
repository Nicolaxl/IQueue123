package com.example.client;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.TaskStackBuilder;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class BingNews extends AppCompatActivity {

    Toolbar mtool;
    TabLayout tabLayout;
    TabItem mhome;
    NewsAdapter newsAdapter;

    String apinews="1b979c38d120493c9f5a7ebdbad0e41d";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bingnews);

        mtool = findViewById(R.id.toolbarnews);
        setSupportActionBar(mtool);

        mhome = findViewById(R.id.hometabs);
        tabLayout = findViewById(R.id.homy);

        ViewPager viewPager = findViewById(R.id.news);
        newsAdapter= new NewsAdapter(getSupportFragmentManager(),1);
        viewPager.setAdapter(newsAdapter);
        newsAdapter.notifyDataSetChanged();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if(tab.getPosition()==0){
                    newsAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        //viewPager.addOnAdapterChangeListener( new TabLayout.TabLayoutOnPageChangeListener(tabLayout));







    }

}
