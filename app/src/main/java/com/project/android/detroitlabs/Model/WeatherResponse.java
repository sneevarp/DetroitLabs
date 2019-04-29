package com.project.android.detroitlabs.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class WeatherResponse {
        @SerializedName("coord")
        public Coord coord;

    public WeatherResponse(Coord coord, Sys sys, ArrayList<Weather> weather, Main main, Wind wind, Rain rain, Clouds clouds, float dt, int id, String name, float cod) {
        this.coord = coord;
        this.sys = sys;
        this.weather = weather;
        this.main = main;
        this.wind = wind;
        this.rain = rain;
        this.clouds = clouds;
        this.dt = dt;
        this.id = id;
        this.name = name;
        this.cod = cod;
    }

    public Coord getCoord() {
        return coord;
    }

    public Sys getSys() {
        return sys;
    }

    public ArrayList<Weather> getWeather() {
        return weather;
    }

    public Main getMain() {
        return main;
    }

    public Wind getWind() {
        return wind;
    }

    public Rain getRain() {
        return rain;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public float getDt() {
        return dt;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getCod() {
        return cod;
    }

    @SerializedName("sys")
        public Sys sys;
        @SerializedName("weather")
        public ArrayList<Weather> weather = new ArrayList<Weather>();
        @SerializedName("main")
        public Main main;
        @SerializedName("wind")
        public Wind wind;
        @SerializedName("rain")
        public Rain rain;
        @SerializedName("clouds")
        public Clouds clouds;
        @SerializedName("dt")
        public float dt;
        @SerializedName("id")
        public int id;
        @SerializedName("name")
        public String name;
        @SerializedName("cod")
        public float cod;
    }

