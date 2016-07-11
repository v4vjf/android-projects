package com.justdial.myapplicationintents;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
public class NewService extends Service {
    private static final String TAG = "myapplicationintents";
    public NewService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<5;i++){
                    long futureTime = System.currentTimeMillis()+2000;
                    while(System.currentTimeMillis()<futureTime){
                        synchronized (this){
                            try{
                                wait(futureTime-System.currentTimeMillis());
                                Log.i(TAG,"service called" + System.currentTimeMillis()/(1000*60*60));
                            }
                            catch(Exception e){

                            }
                        }
                    }
                }
            }
        };
        Thread thread = new Thread(r);
        thread.start();
        return Service.START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }
}
