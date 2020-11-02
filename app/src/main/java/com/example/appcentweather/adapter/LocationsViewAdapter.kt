package com.example.appcentweather.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appcentweather.R
import com.example.appcentweather.adapter.interfacehelper.RecyclerViewClickListener
import com.example.appcentweather.models.RepoLocation
import kotlinx.android.synthetic.main.location_item.view.*

//
class LocationsViewAdapter(val locationDatas : List<RepoLocation>,
                           val listener:RecyclerViewClickListener) : RecyclerView.Adapter<LocationsViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.location_title.text = locationDatas.get(position).title
        holder.itemView.location_distance.text = locationDatas.get(position).distance.toString()
        holder.itemView.location_type.text = locationDatas.get(position).location_type
        holder.itemView.location_latt_long.text = locationDatas.get(position).latt_long
        holder.itemView.location_woeid.text = locationDatas.get(position).woeid.toString()

        holder.itemView.setOnClickListener {
            listener.onItemClickListener(holder.itemView, locationDatas.get(position))
        }
    }

    override fun getItemCount(): Int {
        return locationDatas.size
    }

    class ViewHolder(inflater:LayoutInflater, parent: ViewGroup) : RecyclerView.ViewHolder(
        inflater.inflate(R.layout.location_item, parent, false)
    ){
        private var title : TextView? = null
        private var distance : TextView? = null
        private var location_type : TextView? = null
        private var woeid : TextView? = null
        private var latt_long : TextView?=null

        init {
            title = itemView.findViewById(R.id.location_title)
            distance = itemView.findViewById(R.id.location_distance)
            location_type = itemView.findViewById(R.id.location_type)
            woeid = itemView.findViewById(R.id.location_woeid)
            latt_long = itemView.findViewById(R.id.location_latt_long)
        }

    }
}