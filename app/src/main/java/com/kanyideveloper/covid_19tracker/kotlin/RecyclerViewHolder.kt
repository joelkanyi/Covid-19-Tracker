package com.kanyideveloper.covid_19tracker.kotlin

import android.view.View
import android.widget.GridLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kanyideveloper.covid_19tracker.R

class RecyclerViewHolder(itemView: View, private val statisticsListObject: List<StatisticsList>) :
        RecyclerView.ViewHolder(itemView) {

    var country: TextView
    var activeCases: TextView
    var recoveredCases: TextView
    var totalCases: TextView
    var deaths: TextView
    var gridLayout: GridLayout

    init {
        country = itemView.findViewById(R.id.countryTextField)
        activeCases = itemView.findViewById(R.id.activeCases)
        recoveredCases = itemView.findViewById(R.id.recoveredCases)
        totalCases = itemView.findViewById(R.id.totalCases)
        deaths = itemView.findViewById(R.id.deathCases)
        gridLayout = itemView.findViewById(R.id.grid_layout)
    }

}