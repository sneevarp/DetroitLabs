package com.project.android.detroitlabs.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.project.android.detroitlabs.Adapter.WeatherForecastAdapter;
import com.project.android.detroitlabs.Constants;
import com.project.android.detroitlabs.Model.WeatherForecast;
import com.project.android.detroitlabs.Model.WeatherResponse;
import com.project.android.detroitlabs.R;
import com.project.android.detroitlabs.Utils.NetworkUtils;
import com.project.android.detroitlabs.Utils.WeatherService;
import com.squareup.picasso.Picasso;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class Tab2Fragment extends Fragment {

    static Tab2Fragment instance;
    CompositeDisposable compositeDisposable;
    WeatherService mService;

    TextView txt_city_name;
    RecyclerView recyclerView;

    public static Tab2Fragment getInstance() {
        if(instance==null)
            instance=new Tab2Fragment();
        return instance;
    }

    public Tab2Fragment() {
        compositeDisposable = new CompositeDisposable();
        Retrofit retrofit = NetworkUtils.getInstance();
        mService=retrofit.create(WeatherService.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View itemView= inflater.inflate(R.layout.fragment_two, container, false);

        txt_city_name=itemView.findViewById(R.id.txt_city_name);
        recyclerView=itemView.findViewById(R.id.recycle_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        getWeatheInfo();
        return itemView;
    }

    private void getWeatheInfo() {
        compositeDisposable.add(mService.getForecastWeatherData(String.valueOf(Constants.current_location.getLatitude()),
                String.valueOf(Constants.current_location.getLongitude()),
                Constants.APP_ID,"imperial")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<WeatherForecast>() {
                    @Override
                    public void accept(WeatherForecast weatherForecast) throws Exception {
                        displayFragment(weatherForecast);

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(getActivity(), "", Toast.LENGTH_SHORT).show();
                    }
                }));
    }

    private void displayFragment(WeatherForecast weatherForecast) {
        txt_city_name.setText(weatherForecast.city.name.toString());
        WeatherForecastAdapter weatherForecastAdapter=new WeatherForecastAdapter(getContext(),weatherForecast);
        recyclerView.setAdapter(weatherForecastAdapter);
    }
}