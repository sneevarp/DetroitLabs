package com.project.android.detroitlabs.Model;

import com.google.gson.annotations.SerializedName;

public class Coord {
    @SerializedName("lon")
    public float lon;
    @SerializedName("lat")
    public float lat;

    @Override
    public String toString() {
        return new StringBuilder("[").append(this.lat).append(",").append(this.lon).append("]").toString();
    }
}
