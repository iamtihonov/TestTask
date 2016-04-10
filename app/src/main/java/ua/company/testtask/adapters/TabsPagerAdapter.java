package ua.company.testtask.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import ua.company.testtask.R;
import ua.company.testtask.fragments.DrawingFragment;
import ua.company.testtask.fragments.GalleryFragment;
import ua.company.testtask.fragments.ListOfMachinesFragment;

public class TabsPagerAdapter extends FragmentPagerAdapter {
    private static final int COUNT_TABS_VALUE = 3;

    private String[] mTabsName;
    public TabsPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        mTabsName = context.getResources().getStringArray(R.array.tabs_name);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new ListOfMachinesFragment();

            case 1:
                return new DrawingFragment();

            case 2:
                return new GalleryFragment();

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return COUNT_TABS_VALUE;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTabsName[position];
    }
}
