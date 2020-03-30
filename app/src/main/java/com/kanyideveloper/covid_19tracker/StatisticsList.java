package com.kanyideveloper.covid_19tracker;

public class StatisticsList {

    private String country;
    private String activeCase;
    private String recovered;
    private String deaths;
    private String totalCases;


    public String getCountry(){
        return country;
    }


    public String getActiveCase(){
        return activeCase;
    }

    public String getRecovered(){
        return recovered;
    }

    public String getDeaths(){
        return deaths;
    }


    public String getTotalCase(){
        return totalCases;
    }


    public StatisticsList(String country,String activeCase,
                          String recovered, String deaths,String totalCases){

        this.country = country;
        this.activeCase = activeCase;
        this.recovered = recovered;
        this.deaths = deaths;
        this.totalCases = totalCases;
    }

}
