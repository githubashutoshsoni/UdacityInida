package com.example.kakashi.tourguide;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class CategoryAdapter extends FragmentPagerAdapter {
    Context mContext;

    public CategoryAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int i) {
        if (i == 1)
            return new HistoricalFragment();
        else if (i == 2)
            return new HistoricalFragment();
        else if(i==3)
            return new HistoricalFragment();
        else
            return new HistoricalFragment();

    }

    @Override
    public int getCount() {
        return 4;
    }

    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return mContext.getString(R.string.historical_site);
        } else if (position == 1) {
            return mContext.getString(R.string.restaurant);
        } else if (position == 2) {
            return mContext.getString(R.string.park);
        } else {
            return mContext.getString(R.string.temples);
        }
    }
}