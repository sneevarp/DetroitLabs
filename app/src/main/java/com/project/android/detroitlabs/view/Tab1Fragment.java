package com.project.android.detroitlabs.view;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.project.android.detroitlabs.Constants;
import com.project.android.detroitlabs.Model.WeatherResponse;
import com.project.android.detroitlabs.R;
import com.project.android.detroitlabs.Utils.DateFormatUtil;
import com.project.android.detroitlabs.Utils.NetworkUtils;
import com.project.android.detroitlabs.Utils.WeatherService;
import com.squareup.picasso.Picasso;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class Tab1Fragment extends Fragment {

    ImageView img_weather;
    TextView txt_wind,txt_pressure,txt_city_name,txt_humidity,txt_sunrise,txt_sunset,txt_temperature,txt_desc,txt_date_time;
    LinearLayout weather_panel;
    ProgressBar loading;

    CompositeDisposable compositeDisposable;
    WeatherService mService;

    static Tab1Fragment instance;

    public static Tab1Fragment getInstance() {
        if(instance==null){
            instance=new Tab1Fragment();
        }
        return instance;
    }

    public Tab1Fragment() {
        compositeDisposable = new CompositeDisposable();
        Retrofit retrofit = NetworkUtils.getInstance();
        mService=retrofit.create(WeatherService.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View itemView= inflater.inflate(R.layout.fragment_one, container, false);

        img_weather=itemView.findViewById(R.id.img_weather);
        txt_city_name=itemView.findViewById(R.id.txt_city_name);
        txt_date_time=itemView.findViewById(R.id.txt_date_time);
        txt_desc=itemView.findViewById(R.id.txt_description);
        txt_humidity=itemView.findViewById(R.id.txt_humidity);
        txt_sunrise=itemView.findViewById(R.id.txt_sunrise);
        txt_sunset=itemView.findViewById(R.id.txt_sunset);
        txt_temperature=itemView.findViewById(R.id.txt_temperature);
        txt_pressure=itemView.findViewById(R.id.txt_pressure);
        txt_wind=itemView.findViewById(R.id.txt_wind);

        weather_panel=itemView.findViewById(R.id.weather_panel);
        loading=itemView.findViewById(R.id.loading);

        getWeatherInformation();

        return itemView;
    }

    private void getWeatherInformation() {
        compositeDisposable.add(mService.getCurrentWeatherData(String.valueOf(Constants.current_location.getLatitude()),
                String.valueOf(Constants.current_location.getLongitude()),
                Constants.APP_ID,"imperial")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<WeatherResponse>() {
                    @Override
                    public void accept(WeatherResponse weatherResponse) throws Exception {
                        Picasso.get().load(new StringBuilder("http://openweathermap.org/img/w/")
                        .append(weatherResponse.getWeather().get(0).icon)
                        .append(".png").toString()).into(img_weather);

                        txt_city_name.setText(weatherResponse.getName());
                        txt_desc.setText(new StringBuilder("Weather in ")
                        .append(weatherResponse.getName()).toString());
                        txt_temperature.setText(new StringBuilder
                                (String.valueOf(weatherResponse.getMain().temp))
                                .append("°F").toString());
                        txt_wind.setText(new StringBuilder("Speed ")
                                .append(String.valueOf(weatherResponse.wind.speed))
                                .append("  Deg ")
                                .append(String.valueOf(weatherResponse.wind.deg)).toString());
                        txt_date_time.setText(DateFormatUtil.convertUnixToDate(weatherResponse.getDt()));
                        txt_pressure.setText(new StringBuilder(String.valueOf(weatherResponse.getMain().pressure)).append(" hpa").toString());
                        txt_humidity.setText(new StringBuilder(String.valueOf(weatherResponse.getMain().humidity)).append("%").toString());
                        txt_sunrise.setText(DateFormatUtil.convertUnixToDate(weatherResponse.getSys().sunrise));
                        txt_sunset.setText(DateFormatUtil.convertUnixToDate(weatherResponse.getSys().sunset));

                        weather_panel.setVisibility(View.VISIBLE);
                        loading.setVisibility(View.GONE);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(getActivity(), "", Toast.LENGTH_SHORT).show();
                    }
                }));

    }

}