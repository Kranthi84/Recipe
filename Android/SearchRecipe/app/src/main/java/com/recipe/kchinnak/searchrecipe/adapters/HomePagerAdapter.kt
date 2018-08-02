package com.recipe.kchinnak.searchrecipe.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.recipe.kchinnak.searchrecipe.R
import com.recipe.kchinnak.searchrecipe.fragments.TopratedFragment
import com.recipe.kchinnak.searchrecipe.fragments.TrendingFragment

class HomePagerAdapter(fragmentManager: FragmentManager, var mContext: Context) : FragmentPagerAdapter(fragmentManager) {
    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> return TrendingFragment.newInstance()

            else -> return TopratedFragment.newInstance()

        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> return mContext.getString(R.string.trending_fragment_title)
            1 -> return mContext.getString(R.string.top_rated_fragment_title)
            else -> return null
        }
    }
}