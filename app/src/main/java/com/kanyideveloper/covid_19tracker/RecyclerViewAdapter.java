package com.kanyideveloper.covid_19tracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<StatisticsList> statisticsLists;
    Context context;

    public RecyclerViewAdapter(List<StatisticsList> statisticsLists, Context context){
        this.statisticsLists = statisticsLists;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_itemlist,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final StatisticsList statisticsList = statisticsLists.get(position);
        holder.country.setText(statisticsList.getCountry());
        holder.activeCases.setText(statisticsList.getActiveCase());
        holder.recoveredCases.setText(statisticsList.getRecovered());
        holder.deaths.setText(statisticsList.getDeaths());
        holder.totalCases.setText(statisticsList.getTotalCase());
    }

    @Override
    public int getItemCount() {
        return statisticsLists.size();
    }


    public void filterList(ArrayList<StatisticsList> filteredList){
        statisticsLists = filteredList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{


        TextView country,activeCases,recoveredCases,totalCases,deaths;
        GridLayout gridLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            country = itemView.findViewById(R.id.countryTextField);
            activeCases = itemView.findViewById(R.id.activeCases);
            recoveredCases = itemView.findViewById(R.id.recoveredCases);
            totalCases = itemView.findViewById(R.id.totalCases);
            deaths = itemView.findViewById(R.id.deathCases);
            gridLayout = itemView.findViewById(R.id.grid_layout);
        }
    }
}
