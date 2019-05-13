package com.example.currencyconverter

import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import android.widget.AdapterView.OnItemSelectedListener


class SpecifiedSpinnerListener(private val spinnerIndex: Int, private val context: SpecifiedFragment) : OnItemSelectedListener {

    override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
        if(spinnerIndex == 0){
            context.currencyFrom = parent.getItemAtPosition(pos).toString()
        }
        if(spinnerIndex == 1){
            context.currencyTo = parent.getItemAtPosition(pos).toString()
        }
        context.updateViewData()
    }

    override fun onNothingSelected(arg0: AdapterView<*>) {
    }

}