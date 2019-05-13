package com.example.currencyconverter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.example.currencyconverter.ui.main.SectionsPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OverviewFragment.OverviewFrListener, SpecifiedFragment.SpecifiedFragmentListener {

    private val manager : FragmentManager = supportFragmentManager
    private val overviewFragment = OverviewFragment()
    private val specifiedFragment = SpecifiedFragment()

    private val url = "https://api.exchangeratesapi.io/latest?base=USD"
    var ratesInfo = RatesInfoDto()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        sectionsPagerAdapter.setFragments(overviewFragment, specifiedFragment)
        view_pager.adapter = sectionsPagerAdapter
        tabs.setupWithViewPager(view_pager)

        val apiCaller = ApiCaller(url, this)
        apiCaller.call()

    }

    fun updateOverviewData() {
        overviewFragment.updateViewData()
    }
}