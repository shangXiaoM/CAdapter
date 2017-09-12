package com.shangxiaom.commonlist.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * **************************************************
 *
 * @ 项目名称:com.shangxiaom.commonlist.adapter
 * @ 日        期:2017/9/12 15:16
 * @ 作        者:Administrator
 * @ Copyright (c) 2017, RencareHealth All Rights Reserved.
 * **************************************************
 */
public class ViewPageAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragmentList;
    private String[] titles = { "首页"};

    public ViewPageAdapter(FragmentManager fm, List<Fragment> fragList) {
        super(fm);
        mFragmentList = fragList;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    /**
     * Return the Fragment associated with a specified position.
     *
     * @param position
     */
    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return mFragmentList.size();
    }
}
