package com.justdial.myapplicationfragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.view.LayoutInflater;

import org.w3c.dom.Text;


public class BottomFragment extends Fragment{
    private static TextView topMemeText;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_fragment,container,false);
        topMemeText = (TextView) view.findViewById(R.id.bottomText);
        return view;
    }
    public void setText(String string){
        topMemeText.setText(string);
    }
}
