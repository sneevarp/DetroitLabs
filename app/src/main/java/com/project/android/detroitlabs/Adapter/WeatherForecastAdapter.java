package com.project.android.detroitlabs.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.android.detroitlabs.Model.WeatherForecast;
import com.project.android.detroitlabs.R;
import com.squareup.picasso.Picasso;

public class WeatherForecastAdapter extends RecyclerView.Adapter<WeatherForecastAdapter.MyViewHolder> {

    Context context;
    WeatherForecast weatherForecast;

    public WeatherForecastAdapter(Context context, WeatherForecast weatherForecast) {
        this.context = context;
        this.weatherForecast = weatherForecast;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView= LayoutInflater.from(context)
                .inflate(R.layout.item_view,viewGroup,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Picasso.get().load(new StringBuilder("http://openweathermap.org/img/w/")
                .append(weatherForecast.list.get(i).weather.get(0).icon)
                .append(".png").toString()).into(myViewHolder.img_weather);

        myViewHolder.txt_temperature.setText(new StringBuilder(String.valueOf(weatherForecast.list.get(i).main.temp))
        .append("°F"));
        Log.d("Praveen",weatherForecast.list.get(i).dt_txt);
        myViewHolder.txt_date.setText(weatherForecast.list.get(i).dt_txt);
        myViewHolder.txt_description.setText(weatherForecast.list.get(i).weather.get(0).description.toString());
    }

    @Override
    public int getItemCount() {
        return weatherForecast.list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txt_date,txt_description,txt_temperature;
        ImageView img_weather;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            img_weather=(ImageView)itemView.findViewById(R.id.img_weather);
            txt_date=itemView.findViewById(R.id.txt_date);
            txt_description=itemView.findViewById(R.id.txt_description);
            txt_temperature=itemView.findViewById(R.id.txt_temperature);

        }
    }
}
