package com.example.currencyconverter

import android.util.Log
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException
import kotlin.concurrent.thread

class ApiCaller(private var url: String, private val view: MainActivity){
    private val client = OkHttpClient()

    fun call(){
        thread(start=true, isDaemon = true){
            val gson = Gson()
            val request = Request.Builder().url(url).build()
            var info : RatesInfoDto

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {}
                override fun onResponse(call: Call, response: Response){
                    info = gson.fromJson(response.body()!!.string(), RatesInfoDto::class.java)
                    view.ratesInfo = info
                    view.runOnUiThread {
                        view.updateOverviewData()
                    }
                }
            })
        }
    }

}