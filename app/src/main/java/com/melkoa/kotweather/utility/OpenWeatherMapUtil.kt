package com.melkoa.kotweather.utility

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by melkoa on 10/11/17.
 */
interface OpenWeatherMapAPI {
    @GET("&mode=json&units=metric&cnt=14&APIKEY=9adaf9a99c4964df0d6ae829644bf8f8")
    fun getForecast(@Query("q") q: String) : Call<OpenWeather>
}

class OpenWeather(val list: ArrayList<DailyForecast>)
class DailyForecast(val temp: List<DailyTemp>, val weather: ArrayList<DailyWeather>, val humidity: String)
class DailyTemp(val min: String, val max: String)
class DailyWeather (val main: String, val description: String, val icon: String)

class OpenWeatherRetriever {
    val service : OpenWeatherMapAPI

    init {
        val retrofit =  Retrofit.Builder().baseUrl("http://api.openweathermap.org/data/2.5/forecast/daily").addConverterFactory(GsonConverterFactory.create()).build()
        service = retrofit.create(OpenWeatherMapAPI::class.java)
    }

    fun getForecast(callback: Callback<OpenWeather>, searchTerm: String) {
        var searchT = searchTerm
        if(searchT =="")
            searchT = "Bangalore"
        val q = searchT
        val call = service.getForecast(q)
        call.enqueue(callback)
    }
}