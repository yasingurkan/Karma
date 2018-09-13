package com.example.androidtools.karma.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.androidtools.karma.Fragment.YasinFragment1;
import com.example.androidtools.karma.Fragment.YasinFragment2;
import com.example.androidtools.karma.Fragment.YasinFragment3;


public class YasinPagerAdapter extends FragmentStatePagerAdapter
{
    int mNumOfTabs;

    public YasinPagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                YasinFragment1 tab1 = new YasinFragment1();
                return tab1;
            case 1:
                YasinFragment2 tab2 = new YasinFragment2();
                return tab2;
            case 2:
                YasinFragment3 tab3 = new YasinFragment3();
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}