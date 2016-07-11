package com.example.a4vjf.restaurantmaterialdesign;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.example.a4vjf.restaurantmaterialdesign.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
         /*
            volleySingleton = VolleySingleton.getInstance();
        requestQueue = volleySingleton.getRequestQueue();

        //getting json object for main page
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, main_url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        //adding jsonObjectRequest to requestQueue
        requestQueue.add(jsonObjectRequest);
 */

        //reading jsonResponse from file
        JSONObject obj=null;
        try {
            obj = new JSONObject(loadJSONFromAsset("jsonResponse.json"));
            obj = processJsonObject(obj);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        recyclerView = (RecyclerView) findViewById(R.id.mainRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MainRecyclerViewAdapter(this,obj));

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

    public JSONObject processJsonObject(JSONObject obj){
        JSONObject results = obj.optJSONObject(Keys.RESULTS);
        JSONArray columns = results.optJSONArray(Keys.COLUMNS);
        JSONArray bannerArray = results.optJSONArray(Keys.BANNER);
        JSONArray taskArray = results.optJSONArray(Keys.TASK);
        JSONArray categoryArray = results.optJSONArray(Keys.CATEGORY);
        JSONArray viewAllArray = results.optJSONArray(Keys.VIEW_ALL);
        JSONArray suggestionArray = results.optJSONArray(Keys.SUGGESTION);
        JSONObject finalObject = new JSONObject();



        //creating customised json object finalObject for the ui


        try{
            //processing banner
            JSONArray banner = new JSONArray();
            //creating key value pair for banner
            for(int i=0;i<bannerArray.length();i++){
                JSONArray tempArray = bannerArray.optJSONArray(i);
                JSONObject tempObject = new JSONObject();
                for(int j=0;j<tempArray.length();j++){
                    tempObject.put(columns.optString(j),tempArray.optString(j));
                }
                banner.put(tempObject);
            }

            //processing task
            JSONArray task = new JSONArray();
            for(int i=0;i<taskArray.length();i++){
                JSONArray tempArray = taskArray.optJSONArray(i);
                JSONObject tempObject = new JSONObject();
                for(int j=0;j<tempArray.length();j++){
                    tempObject.put(columns.optString(j),tempArray.optString(j));
                }
                task.put(tempObject);
            }

            //processing category section
            JSONArray category = new JSONArray();
            for(int i=0;i<categoryArray.length();i++){
                JSONArray tempArray = categoryArray.optJSONArray(i);
                JSONObject tempObject = new JSONObject();
                for(int j=0;j<tempArray.length();j++){
                    tempObject.put(columns.optString(j),tempArray.optString(j));
                }
                category.put(tempObject);
            }

            // processing viewAll array
            JSONObject viewAll = new JSONObject();
            for(int j=0;j<viewAllArray.length();j++){
                viewAll.put(columns.optString(j),viewAllArray.optString(j));
            }

            //processing suggestion section
            Log.i("vyom","suggestionArray\n"+suggestionArray.toString());
            JSONArray suggestion = new JSONArray();
            for(int i=0;i<suggestionArray.length();i++){
                JSONArray tempArray = suggestionArray.optJSONArray(i);
                JSONObject tempObject = new JSONObject();
                for(int j=0;j<tempArray.length();j++){
                    tempObject.put(columns.optString(j),tempArray.optString(j));
                }
                suggestion.put(tempObject);
            }


            //adding all the processed items to final object
            finalObject.put(Keys.BANNER,banner);
            finalObject.put(Keys.TASK,task);
            finalObject.put(Keys.CATEGORY,category);
            finalObject.put(Keys.VIEW_ALL,viewAll);
            finalObject.put(Keys.SUGGESTION,suggestion);
            Log.i("vyom","suggestion\n"+suggestion.toString());
            finalObject.put(Keys.IMAGE_PATH,results.optString(Keys.IMAGE_PATH));
            finalObject.put(Keys.TOTAL_NUM_OF_RESULTS,results.optInt(Keys.TOTAL_NUM_OF_RESULTS));
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        Log.i("vyom","final object\n"+finalObject.toString());
        return finalObject;
    }


    public String loadJSONFromAsset(String fileName) {
        String json = null;
        try {

            InputStream is = getAssets().open(fileName);

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;

    }
}
