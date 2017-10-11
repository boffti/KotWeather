package com.melkoa.kotweather.model

/**
 * Created by melkoa on 10/11/17.
 */
data class Weather(
        val name: String,
        val date: String,
        val temperature: String,
        val main: String,
        val desc: String
)