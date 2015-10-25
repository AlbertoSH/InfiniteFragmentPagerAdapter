package com.github.albertosh.infinitefragmentpageradapter;

import android.support.v4.app.Fragment;

public abstract class InfiniteFragmentPagerFragmentImpl extends Fragment implements InfiniteFragmentPagerFragment {

    private int globalPosition;

    @Override
    public int getGlobalPosition() {
        return globalPosition;
    }

    void setGlobalPosition(int position) {
        this.globalPosition = position;
    }
}
