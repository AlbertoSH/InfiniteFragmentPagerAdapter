package com.github.albertosh.infinitefragmentpageradapter.sample;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import com.github.albertosh.infinitefragmentpageradapter.InfiniteFragmentPagerAdapter;

public class MyFragmentPagerAdapter extends InfiniteFragmentPagerAdapter {

    public MyFragmentPagerAdapter(ViewPager viewPager, FragmentManager fm) {
        super(viewPager, fm);
    }

    @Override
    protected Fragment getPage(int index) {
        return SingleFragment.newInstance(index);
    }
}
