package com.project.android.detroitlabs.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtil {
    public static String convertUnixToDate(float dt){
        Date date=new Date((int)dt*1000L);
        SimpleDateFormat df=new SimpleDateFormat("HH:mm EEE MM yyyy");
        String formatedDate=df.format(date);
        return formatedDate;
    }
}
