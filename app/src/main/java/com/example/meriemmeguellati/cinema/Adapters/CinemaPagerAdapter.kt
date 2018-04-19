package com.example.meriemmeguellati.cinema.Adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * Created by Meriem Meguellati on 03/04/2018.
 */
class CinemaPagerAdapter(fm: FragmentManager?): FragmentPagerAdapter(fm) {

    var mfm =fm
    var mFragmentItems: ArrayList<Fragment> = ArrayList()
    var mFragmentTitle: ArrayList<String> = ArrayList()

    fun addFragments(fragmentItem: Fragment, fragmentTitle: String){
        mFragmentTitle.add(fragmentTitle)
        mFragmentItems.add(fragmentItem)
    }

    override fun getItem(position: Int): Fragment {
        return mFragmentItems[position]
    }

    override fun getCount(): Int {
        return mFragmentItems.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mFragmentTitle[position]
    }

}