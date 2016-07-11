package com.justdial.boundservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;


public class MyService extends Service {
    //default constructor
    public MyService() {
    }

    //this IBinder object will be returned
    public IBinder binder = new MyLocalBinder();

    //creatting local class to get reference to this MyService Class
    public class MyLocalBinder extends Binder{
        MyService getService(){
            return MyService.this;
        }
    }

    //this method will return current time as a string
    public String getCurrentTime(){
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss",Locale.ENGLISH);
        return (df.format(new Date()));
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }
}
