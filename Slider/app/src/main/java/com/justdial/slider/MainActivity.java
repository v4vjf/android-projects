package com.justdial.slider;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    int[] bannerImageArray = {R.drawable.banner_0,R.drawable.banner_1,R.drawable.banner_2};
    String[] bannerDescription = {"banner1","banner2","banner3"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager viewPager= (ViewPager) findViewById(R.id.viewPager);
        CustomAdapter adapter = new CustomAdapter(MainActivity.this,bannerImageArray,bannerDescription);
        viewPager.setAdapter(adapter);
    }

}
