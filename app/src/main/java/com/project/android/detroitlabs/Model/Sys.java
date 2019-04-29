package com.project.android.detroitlabs.Model;

import com.google.gson.annotations.SerializedName;

public class Sys {
        @SerializedName("country")
        public String country;
        @SerializedName("sunrise")
        public long sunrise;
        @SerializedName("sunset")
        public long sunset;
    }
