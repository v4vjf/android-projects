package com.justdial.volleyapp;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by justdial on 13/6/16.
 */
public class VolleySingleton {

    private static VolleySingleton sInstance = null;

    private static RequestQueue requestQueue;
    private Context context = null;

    private VolleySingleton(){
        requestQueue = Volley.newRequestQueue(MyApplication.getContext());
    }

    public static VolleySingleton getsInstance(){
        if(sInstance == null){
            sInstance = new VolleySingleton();
        }
        return sInstance;
    }

    public RequestQueue getRequestQueue(){
        return requestQueue;
    }



}
