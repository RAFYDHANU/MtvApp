package com.aryosatria.submissionexpt3.UI.main;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.aryosatria.submissionexpt3.listmovie.MovieFragment;
import com.aryosatria.submissionexpt3.listtvshow.TvShowFragment;
import com.aryosatria.submissionexpt3.R;

class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_movies, R.string.tab_tv_shows};
    private final Context mContext;
    private MovieFragment moviesFragment;
    private TvShowFragment tvShowFragment;

    SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mContext = context;
        moviesFragment = new MovieFragment();
        tvShowFragment = new TvShowFragment();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        if (position == 0) {
            return moviesFragment;
        } else {
            return tvShowFragment;
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        return 2;
    }
}
