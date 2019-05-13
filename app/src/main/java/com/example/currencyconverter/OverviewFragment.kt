package com.example.currencyconverter

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_overview.*
import kotlinx.android.synthetic.main.fragment_overview.view.*
import java.util.*

class OverviewFragment : Fragment() {
    private var listener: OverviewFrListener? = null


    var currentCurrency : String = "USD"

    private val images = Arrays.asList(R.drawable.united_states, R.drawable.european_union, R.drawable.poland,
        R.drawable.united_kingdom, R.drawable.japan, R.drawable.switzerland, R.drawable.australia)
    //    private val currencies = resources.getStringArray(R.array.currencies_arrays)
//        Arrays.asList("USD", "EUR", "PLN", "GBP", "JPY", "AUD", "CHF", "BGN", "NZD", "ILS",
//        "RUB", "CAD", "PHP", "TRY", "HKD", "MYR", "HRK", "CZK", "IDR", "DKK", "NOK",
//        "HUF", "MXN", "HRK", "THB", "ISK", "ZAR", "BRL", "SGD", "INR", "KRW", "RON",
//        "CNY", "SEK")
    private var imgViews = Arrays.asList(img1)
    private var curTextViews = Arrays.asList(cur1)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_overview, container, false)
        imgViews = Arrays.asList(view.img1, view.img2, view.img3, view.img4, view.img5, view.img6)
        curTextViews = Arrays.asList(view.cur1, view.cur2, view.cur3, view.cur4, view.cur5, view.cur6)
        view.overviewSpinner.onItemSelectedListener = OverviewSpinnerListener(this)
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OverviewFrListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OverviewFrListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OverviewFrListener {
    }

    fun updateViewData(){
        var ratesInfo = (context as MainActivity).ratesInfo
        val rates = getListFromInfoDto(ratesInfo)
        val currencies = resources.getStringArray(R.array.currencies_arrays)

        val currentCurrencyIndex = currencies.indexOf(currentCurrency)
        val currentCurrencyRate = rates[currentCurrencyIndex]

        for(i in 0 until rates.size)
            rates[i] = rates[i] / currentCurrencyRate

        var flag = 0
        for(i in 0 until 6){
            if(i == currentCurrencyIndex)
                flag = 1
            curTextViews[i].text = String.format("%.4f %s", rates[i + flag], currencies[i + flag])
            imgViews[i].setImageResource(images[i + flag])
        }
    }
    private fun getListFromInfoDto(data: RatesInfoDto): MutableList<Double>{
        return Arrays.asList(data.rates.USD, data.rates.EUR, data.rates.PLN,
            data.rates.GBP, data.rates.JPY, data.rates.AUD, data.rates.CHF, data.rates.BGN,
            data.rates.NZD, data.rates.ILS, data.rates.RUB, data.rates.CAD, data.rates.PHP,
            data.rates.TRY, data.rates.HKD, data.rates.MYR, data.rates.HRK, data.rates.CZK,
            data.rates.IDR, data.rates.DKK, data.rates.NOK, data.rates.HUF, data.rates.MXN,
            data.rates.HRK, data.rates.THB, data.rates.ISK, data.rates.ZAR, data.rates.BRL,
            data.rates.SGD, data.rates.INR, data.rates.KRW, data.rates.RON, data.rates.CNY,
            data.rates.SEK)
    }

}
