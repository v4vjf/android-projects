package com.justdial.listviewid;

import android.support.v4.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String[] url;
    EditText editText;
    LinearLayout loadingSection;
    Handler handler;
    ProgressBar progressBar;
    MyFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {

            }
        };
        if(savedInstanceState == null) {

            fragment = new MyFragment();
           getSupportFragmentManager().beginTransaction().add(fragment,"TaskFragment").commit();
        }
        else{

            fragment = (MyFragment) getSupportFragmentManager().findFragmentByTag("TaskFragment");
        }
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        ListView listView = (ListView) findViewById(R.id.listView);
        url = getResources().getStringArray(R.array.url_array);
        editText = (EditText) findViewById(R.id.url_text);
        loadingSection = (LinearLayout) findViewById(R.id.loadingSection);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                editText.setText(url[position]);
            }
        });


    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);

        if(fragment != null){

        }
    }

    public void downloadImage(View view){

        String string = editText.getText().toString();
        fragment.beginTask(string);

    }
    public void updateProgress(int progress){
                if(loadingSection.getVisibility() != View.VISIBLE) {
                    loadingSection.setVisibility(View.VISIBLE);
                }
                progressBar.setProgress(progress);

    }

    public void showProgress(){


                if (loadingSection.getVisibility() == View.GONE) {

                    loadingSection.setVisibility(View.VISIBLE);
                }

    }
    public void hideProgress(){
            if(loadingSection.getVisibility() == View.VISIBLE){
                loadingSection.setVisibility(View.GONE);
            }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
