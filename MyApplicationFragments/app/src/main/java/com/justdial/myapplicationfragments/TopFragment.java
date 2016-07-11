package com.justdial.myapplicationfragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.EditText;
import android.app.Activity;

public class TopFragment extends Fragment {

    private static EditText bottomTextInput;

    public interface TopSectionListener{
        public void createMeme(String text);
    }

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        try{
            activityCommander = (TopSectionListener) activity;
        }
        catch(ClassCastException e){
            throw new ClassCastException(activity.toString());
        }
    }
    TopSectionListener activityCommander;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.top_fragment,container,false);
        bottomTextInput = (EditText) view.findViewById(R.id.bottomTextInput);
        final Button createButton = (Button) view.findViewById(R.id.createButton);
        createButton.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        buttonClicked(v);
                    }
                }
        );
        return view;
    }
    public void buttonClicked(View v){
        activityCommander.createMeme(bottomTextInput.getText().toString());
    }
}
