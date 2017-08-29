package appy.aparna.example.com.hyderabadtourism;

import android.app.Fragment;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Administrator on 5/24/2017.
 */

public class CategoryAdapter extends FragmentPagerAdapter {

    private Context context;

    //Constructor
    public CategoryAdapter(FragmentManager fm, Context c) {
        super(fm);
        context = c;
    }

    //Give labels to Tabs
    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return context.getString(R.string.category_a);
        }
        if (position == 1) {
            return context.getString(R.string.category_b);
        }
        if (position == 2) {
            return context.getString(R.string.category_c);
        } else {
            return context.getString(R.string.category_d);
        }

    }

    //Link fragments to positions
    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        if (position == 0) {
            return new HistoricalFragment();
        }
        if (position == 1) {
            return new SiteFragment();
        }
        if (position == 2) {
            return new RestrauntFragment();
        } else {
            return new HotelFragment();
        }
    }

    //No of fragments
    @Override
    public int getCount() {
        return 4;
    }
}
