package com.example.sunmin_project;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.sunmin_project.fragment.Fragment1;
import com.example.sunmin_project.fragment.Fragment2;
import com.example.sunmin_project.fragment.Fragment3;

public class PageViewAdapter extends FragmentStatePagerAdapter {

    protected FragmentManager fragmentManager;
    private Fragment[] fragments = {new Fragment1(),new Fragment2(), new Fragment3()};

    public PageViewAdapter(FragmentManager fm) {
        super(fm);
        this.fragmentManager = fm;
    }

    public FragmentManager getFragmentManager(){
        return this.fragmentManager;
    }
    @Override
    public Fragment getItem(int i) {
        return fragments[i];
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "OBJECT " + (position + 1);
    }

}