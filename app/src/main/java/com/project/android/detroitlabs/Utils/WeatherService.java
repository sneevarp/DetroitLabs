package com.project.android.detroitlabs.Utils;

import com.project.android.detroitlabs.Model.WeatherResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherService {
    @GET("weather?")
    Observable<WeatherResponse> getCurrentWeatherData(@Query("lat") String lat, @Query("lon") String lon, @Query("APPID") String app_id, @Query("units") String unit);
}
