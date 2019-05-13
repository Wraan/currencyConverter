package com.example.currencyconverter

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_specified.view.*
import java.util.*
import android.text.Editable



class SpecifiedFragment : Fragment() {

    private var listener: SpecifiedFragmentListener? = null
    var currencyFrom = "USD"
    var currencyTo = "USD"

    private lateinit var convertedNumberTV : TextView
    private lateinit var numberToConvertET : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_specified, container, false)
        view.specFromSpinner.onItemSelectedListener = SpecifiedSpinnerListener(0, this)
        view.specToSpinner.onItemSelectedListener = SpecifiedSpinnerListener(1, this)
        numberToConvertET = view.specFromNumber
        convertedNumberTV = view.specToNumber
        view.specFromNumber.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if(p0 != null && p0.toString() != "")
                    updateViewData()
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }
        })
        // Inflate the layout for this fragment
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is SpecifiedFragmentListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement SpecifiedFragmentListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface SpecifiedFragmentListener {
    }

    fun updateViewData(){
        var ratesInfo = (context as MainActivity).ratesInfo
        val rates = getListFromInfoDto(ratesInfo)
        val currencies = resources.getStringArray(R.array.currencies_arrays)

        val currencyFromIndex = currencies.indexOf(currencyFrom)
        val currencyToIndex = currencies.indexOf(currencyTo)

        val currencyToRate = rates[currencyToIndex] / rates[currencyFromIndex]

        val calculated = numberToConvertET.text.toString().toDouble() * currencyToRate

        convertedNumberTV.text = String.format("%.4f ", calculated)
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
