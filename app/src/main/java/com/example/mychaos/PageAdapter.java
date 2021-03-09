package com.example.mychaos;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PageAdapter extends FragmentPagerAdapter {
    int mNumofTabs;
    public PageAdapter(@NonNull FragmentManager fm,int behaviour) {
        super(fm);
        this.mNumofTabs=behaviour;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                return  new manage();
            case 1:
                return new connect();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumofTabs;
    }
}
