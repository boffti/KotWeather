package com.melkoa.kotweather.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.melkoa.kotweather.R
import com.melkoa.kotweather.model.WeatherNew

/**
 * Created by melkoa on 10/11/17.
 */
class WeatherListAdapter (val weatherList: ArrayList<WeatherNew>) : RecyclerView.Adapter<WeatherListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.weather_list_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val weather: WeatherNew = weatherList[position]
        holder?.textDate?.text = weather.date
        holder?.textMain?.text = weather.text
        holder?.textDesc?.text = weather.day
        holder?.textTemp?.text = weather.high
        holder?.textMonth?.text = weather.month
    }

    override fun getItemCount(): Int = weatherList.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textDate = itemView.findViewById<TextView>(R.id.dateView)
        val textMonth = itemView.findViewById<TextView>(R.id.monthView)
        val textMain = itemView.findViewById<TextView>(R.id.mainView)
        val textDesc = itemView.findViewById<TextView>(R.id.descView)
        val textTemp = itemView.findViewById<TextView>(R.id.tempView)
    }
}