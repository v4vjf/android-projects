package com.justdial.listviewid;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MyFragment extends Fragment {
    MyClass myClass;
    private Activity activity;
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);
    }

    public void beginTask(String... url){

        myClass = new MyClass(activity);
        myClass.execute(url[0]);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        this.activity =  (Activity) context;
        if(this.myClass == null){

        }
        if(this.myClass != null){

            this.myClass.onAttach(activity);
        }
    }

    @Override
    public void onDetach() {

        super.onDetach();
        if(myClass!=null) {
            myClass.onDetach();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
