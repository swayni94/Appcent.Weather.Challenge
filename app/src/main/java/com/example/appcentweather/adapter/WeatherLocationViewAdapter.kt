package com.example.appcentweather.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appcentweather.R
import com.example.appcentweather.adapter.interfacehelper.RecyclerViewClickListenerToWeatherDate
import com.example.appcentweather.models.RepoLoctionWeather
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.weather_item.view.*
import java.text.SimpleDateFormat
import java.util.*

//
class WeatherLocationViewAdapter(val weather: RepoLoctionWeather,
                                 val listiner:RecyclerViewClickListenerToWeatherDate,
                                 val simpleDateFormat: SimpleDateFormat):
    RecyclerView.Adapter<WeatherLocationViewAdapter.ViewHolder>() {

    class ViewHolder (inflater:LayoutInflater, parent: ViewGroup):RecyclerView.ViewHolder(
        inflater.inflate(R.layout.weather_item, parent, false)){
        private var temp :TextView? = null
        private var dayname:TextView? = null
        private var stateImage:ImageView? = null

        init {
            temp = itemView.findViewById(R.id.weather_item_temp)
            dayname = itemView.findViewById(R.id.weather_item_dayName)
            stateImage = itemView.findViewById(R.id.weather_item_image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater, parent)
    }

    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS",
        "RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS"
    )
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.weather_item_temp.text = weather.consolidated_weather?.get(position)?.the_temp?.toInt().toString() + "\u2103"

        val format = SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH)
        val dateFormat = format.parse(weather.consolidated_weather?.get(position)?.applicable_date)
        holder.itemView.weather_item_dayName.text = simpleDateFormat.format(dateFormat.time).toUpperCase(Locale.ROOT)
        val url:String = "https://www.metaweather.com/static/img/weather/png/64/"+weather.consolidated_weather?.get(position)?.weather_state_abbr+".png"
        Picasso.get().load(url).into(holder.itemView.weather_item_image)

        holder.itemView.setOnClickListener {
            listiner.onItemClickListener(holder.itemView, weather.consolidated_weather!![position],
                weather.woeid?.toInt()!!
            )
        }
    }

    override fun getItemCount(): Int {
        return weather.consolidated_weather?.size!!
    }
}