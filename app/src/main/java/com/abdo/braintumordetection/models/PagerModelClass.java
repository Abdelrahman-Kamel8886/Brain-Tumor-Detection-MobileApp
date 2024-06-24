package com.abdo.braintumordetection.models;

import androidx.fragment.app.Fragment;

public class PagerModelClass {

    private Fragment fragment;
    private String title;

    public PagerModelClass(Fragment fragment, String title) {
        this.fragment = fragment;
        this.title = title;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public String getTitle() {
        return title;
    }
}
