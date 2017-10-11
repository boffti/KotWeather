package com.melkoa.kotweather

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.melkoa.kotweather.adapter.WeatherListAdapter
import com.melkoa.kotweather.utility.Weather2
import com.melkoa.kotweather.model.WeatherNew
import com.melkoa.kotweather.utility.WeatherRetriever
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ForecastActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast)

        val weatherRecyclerView = findViewById<RecyclerView>(R.id.weatherListRecyclerView)

        weatherRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        val searchTerm = intent.extras.getString("searchString")
        Log.i("ForcastActivitySrchStr", searchTerm)
        val weather = ArrayList<WeatherNew>()
        var retriever = WeatherRetriever()

        val callback = object: Callback<Weather2> {
            override fun onResponse(call: Call<Weather2>?, response: Response<Weather2>?) {
                Log.i("Response","Obtained")
                val forecastResponse = response?.body()?.query?.results?.channel?.item?.forecast
                title = response?.body()?.query?.results?.channel?.title
                for(resp in forecastResponse!!) {
                    Log.i("Text", resp.text)
                    weather.add(WeatherNew(resp.date.substring(0, 2), resp.day, resp.high, resp.low, resp.text, resp.date.substring(3, 7)))
                }
                val adapter = WeatherListAdapter(weather)
                weatherRecyclerView.adapter = adapter
            }

            override fun onFailure(call: Call<Weather2>?, t: Throwable?) {

            }
        }
        retriever.getForecast(callback, searchTerm)
    }


}
