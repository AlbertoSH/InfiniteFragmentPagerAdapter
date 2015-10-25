package com.github.albertosh.infinitefragmentpageradapter.sample;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

public class InfiniteFragmentPagerAdapter extends FragmentPagerAdapter implements ViewPager.OnPageChangeListener {

    private final static int PAGE_LEFT = 0;
    private final static int PAGE_MIDDLE = 1;
    private final static int PAGE_RIGHT = 2;

    private Fragment[] fragments;
    private final int rootIndex;
    private int currentIndex;
    private ViewPager viewPager;

    public InfiniteFragmentPagerAdapter(ViewPager viewPager, FragmentManager fm) {
        super(fm);
        currentIndex = rootIndex = 0;
        fragments = new Fragment[getCount()];
        this.viewPager = viewPager;

        viewPager.setAdapter(this);

        viewPager.setCurrentItem(1, false);

        viewPager.addOnPageChangeListener(this);
    }

    public void swipeRight() {
        currentIndex++;
        fragments[PAGE_LEFT] = fragments[PAGE_MIDDLE];
        fragments[PAGE_MIDDLE] = fragments[PAGE_RIGHT];
        fragments[PAGE_RIGHT] = null;
    }

    public void swipeLeft() {
        currentIndex--;
        fragments[PAGE_RIGHT] = fragments[PAGE_MIDDLE];
        fragments[PAGE_MIDDLE] = fragments[PAGE_LEFT];
        fragments[PAGE_LEFT] = null;
    }

    @Override
    public Fragment getItem(int position) {
        if (fragments[position] == null) {
            switch (position) {
                case PAGE_LEFT:
                    fragments[PAGE_LEFT] = getLeftPage();
                    break;
                case PAGE_MIDDLE:
                    fragments[PAGE_MIDDLE] = getMiddlePage();
                    break;
                case PAGE_RIGHT:
                    fragments[PAGE_RIGHT] = getRightPage();
                    break;
            }
        }
        return fragments[position];
    }

    private Fragment getLeftPage() {
        return SingleFragment.newInstance(currentIndex - 1);
    }

    private Fragment getMiddlePage() {
        return SingleFragment.newInstance(currentIndex);
    }

    public Fragment getRightPage() {
        return SingleFragment.newInstance(currentIndex + 1);
    }

    @Override
    public int getCount() {
        return 3;
    }


    /*
    @Override
    public int getItemPosition(Object object) {
        SingleFragment fragment = (SingleFragment) object;
        int id = fragment.getMyId();
        int diff = id - currentIndex;
        int newPosition;
        if (Math.abs(diff) > 1)
            newPosition = POSITION_NONE;
        else
            newPosition = diff + 1;

        return newPosition;
    }
*/

    @Override
    public int getItemPosition(Object object) {

        int newPosition;
            newPosition = POSITION_NONE;

        return newPosition;
    }

    @Override
    public long getItemId(int position) {
        return currentIndex - 1 + position;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    int currentPageSelected;

    @Override
    public void onPageSelected(int position) {
        currentPageSelected = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (state == ViewPager.SCROLL_STATE_IDLE) {
            // user swiped to right direction --> left page
            if (currentPageSelected == PAGE_LEFT) {
                swipeLeft();

                // user swiped to left direction --> right page
            } else if (currentPageSelected == PAGE_RIGHT) {
                swipeRight();
            }
            viewPager.setCurrentItem(PAGE_MIDDLE, false);
            notifyDataSetChanged();
        }
    }
}
