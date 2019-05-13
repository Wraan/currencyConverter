package com.example.currencyconverter

import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import android.widget.AdapterView.OnItemSelectedListener


class OverviewSpinnerListener(private val context: OverviewFragment) : OnItemSelectedListener {

    override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
        context.currentCurrency = parent.getItemAtPosition(pos).toString()
        context.updateViewData()
    }

    override fun onNothingSelected(arg0: AdapterView<*>) {
        // TODO Auto-generated method stub
    }

}