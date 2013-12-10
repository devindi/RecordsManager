package com.devindi.records;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);

    }
    @Override
    public Fragment getItem(int position) {
        Fragment f = new Fragment();
        switch(position){
            case 0:
                f= OutCallsActivity.newInstance();
                break;
            case 1:
                f= OutCallsActivity.newInstance();
                break;
        }
        return f;
    }
    @Override
    public int getCount() {
        return 2;
    }

}
