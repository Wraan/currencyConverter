package com.example.currencyconverter.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.currencyconverter.MainActivity
import com.example.currencyconverter.OverviewFragment
import com.example.currencyconverter.R
import com.example.currencyconverter.SpecifiedFragment

private val TAB_TITLES = arrayOf(
    R.string.tab_text_1,
    R.string.tab_text_2
)
class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private lateinit var overviewFragment : OverviewFragment
    private lateinit var specifiedFragment : SpecifiedFragment

    override fun getItem(position: Int): Fragment {
        return if (position == 1) specifiedFragment
        else overviewFragment
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        return 2
    }
    fun setFragments(overviewFragment: OverviewFragment, specifiedFragment: SpecifiedFragment) {
        this.overviewFragment = overviewFragment
        this.specifiedFragment = specifiedFragment
    }
}