package com.example.appcentweather.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appcentweather.R
import com.example.appcentweather.models.Consolidated_Weather
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.weather_time_item.view.*
//
class WeatherWoeidToTimeAdapter(val datas:List<Consolidated_Weather>) : RecyclerView.Adapter<WeatherWoeidToTimeAdapter.ViewHolder>() {

    class ViewHolder(inflater: LayoutInflater, parent: ViewGroup):RecyclerView.ViewHolder(
        inflater.inflate(R.layout.weather_time_item, parent, false)
    ){
        var statename :TextView? = null
        var temp : TextView? = null
        var image:ImageView? = null
        var humidity : TextView? = null
        var wind : TextView? = null
        var pressure : TextView? = null

        init {
            statename = itemView.findViewById(R.id.weather_time_item_statename)
            temp = itemView.findViewById(R.id.weather_time_item_temp)
            image = itemView.findViewById(R.id.weather_time_item_image)
            humidity = itemView.findViewById(R.id.weather_time_item_humidity)
            wind = itemView.findViewById(R.id.weather_time_item_wind)
            pressure = itemView.findViewById(R.id.weather_time_item_pressure)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater, parent)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.weather_time_item_statename.text = datas.get(position).weather_state_name
        holder.itemView.weather_time_item_temp.text = datas.get(position).the_temp?.toInt().toString()+"\u2103"
        holder.itemView.weather_time_item_humidity.text = datas.get(position).humidity?.toInt().toString()+"%"
        holder.itemView.weather_time_item_wind.text = datas.get(position).wind_direction?.toInt().toString()+"mph"
        holder.itemView.weather_time_item_pressure.text = datas.get(position).air_pressure?.toInt().toString()+"mbar"
        val url : String = "https://www.metaweather.com/static/img/weather/png/64/"+datas.get(position).weather_state_abbr+".png"
        Picasso.get().load(url).into(holder.itemView.weather_time_item_image)
    }

    override fun getItemCount(): Int {
        return datas.size
    }
}