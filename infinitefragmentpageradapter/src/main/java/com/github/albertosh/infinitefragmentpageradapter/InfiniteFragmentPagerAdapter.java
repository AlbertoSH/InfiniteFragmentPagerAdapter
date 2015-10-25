package com.github.albertosh.infinitefragmentpageradapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

/**
 * @author Alberto Sanz
 * @since 25/10/2015
 */
public abstract class InfiniteFragmentPagerAdapter
        extends FragmentPagerAdapter
        implements ViewPager.OnPageChangeListener {

    private final static int PAGE_LEFT = 0;
    private final static int PAGE_MIDDLE = 1;
    private final static int PAGE_RIGHT = 2;

    private Fragment[] fragments;
    private int currentPageSelected;
    private int currentCentralPosition;

    public InfiniteFragmentPagerAdapter(ViewPager viewPager, FragmentManager fm) {
        super(fm);
        currentCentralPosition = 0;
        fragments = new Fragment[getCount()];

        viewPager.setOffscreenPageLimit(1);
        viewPager.setAdapter(this);
        viewPager.setCurrentItem(1, false);
        viewPager.addOnPageChangeListener(this);
    }

    public void swipeRight() {
        currentCentralPosition++;
        fragments[PAGE_LEFT] = fragments[PAGE_MIDDLE];
        fragments[PAGE_MIDDLE] = fragments[PAGE_RIGHT];
        fragments[PAGE_RIGHT] = null;
    }

    public void swipeLeft() {
        currentCentralPosition--;
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
        return getPageChecked(currentCentralPosition - 1);
    }

    private Fragment getMiddlePage() {
        return getPageChecked(currentCentralPosition);
    }

    private Fragment getRightPage() {
        return getPageChecked(currentCentralPosition + 1);
    }

    private Fragment getPageChecked(int index) {
        Fragment fragment = getPage(index);
        if (fragment instanceof InfiniteFragmentPagerFragmentImpl)
            ((InfiniteFragmentPagerFragmentImpl)fragment).setGlobalPosition(index);

        if (fragment instanceof InfiniteFragmentPagerFragment)
            return fragment;
        else
            throw new IllegalArgumentException("The fragment returned by getPage must implement InfiniteFragmentPagerFragment");
    }

    /**
     * Provides the fragment that correspond to position index
     * @param index Global position of the fragment
     * @return
     */
    protected abstract Fragment getPage(int index);

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public int getItemPosition(Object object) {
        InfiniteFragmentPagerFragment fragment = (InfiniteFragmentPagerFragment) object;
        int globalPosition = fragment.getGlobalPosition();
        int diff = globalPosition - currentCentralPosition;
        int newPosition;
        if (Math.abs(diff) > 1)
            newPosition = POSITION_NONE;
        else
            newPosition = diff + 1;

        return newPosition;
    }

    @Override
    public long getItemId(int position) {
        return currentCentralPosition - 1 + position;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

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
            notifyDataSetChanged();
        }
    }
}
