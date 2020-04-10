package com.kanyideveloper.covid_19tracker.kotlin

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.kanyideveloper.covid_19tracker.R

class RecyclerViewAdapter(private var context: Context, private var statisticsLists: ArrayList<StatisticsList>) :
        RecyclerView.Adapter<RecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val viewHolder: RecyclerViewHolder?
        val layoutView = LayoutInflater.from(parent.context).inflate(R.layout.content_itemlist, parent, false)
        viewHolder = RecyclerViewHolder(layoutView, statisticsLists)
        return viewHolder
    }

    override fun getItemCount(): Int {
        return statisticsLists.size
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.country.text = statisticsLists[position].country
        holder.activeCases.text = statisticsLists[position].activeCase
        holder.recoveredCases.text = statisticsLists[position].recovered
        holder.deaths.text = statisticsLists[position].deaths
        holder.totalCases.text = statisticsLists[position].totalCases
        Glide.with(context).load(statisticsLists[position].imageUrl)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                        holder.imageViewFlag.visibility = View.GONE
                        return false
                    }

                    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                        return false
                    }

                }).into(holder.imageViewFlag)
    }

    fun filterList(filteredList: java.util.ArrayList<StatisticsList>) {
        statisticsLists = filteredList
        notifyDataSetChanged()
    }

}