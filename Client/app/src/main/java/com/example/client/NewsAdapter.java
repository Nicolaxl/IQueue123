package com.example.client;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class NewsAdapter extends FragmentPagerAdapter {

    int count;

    public NewsAdapter(@NonNull FragmentManager fm, int behavior){

        super(fm, behavior);
        count = behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if(position == 0)
            return new FinanceFragment();
        else
            return null;
    }

    @Override
    public int getCount() {
        return count;
    }
}
