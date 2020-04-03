package com.kanyideveloper.covid_19tracker.kotlin

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
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
    }

    fun filterList(filteredList: java.util.ArrayList<StatisticsList>) {
        statisticsLists = filteredList
        notifyDataSetChanged()
    }

}