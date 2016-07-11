package com.justdial.restaurant;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class Main2Activity extends AppCompatActivity {
    ListView list;
    private static final String main_url = "http://win.justdial.com/26june2015/filternew.php?vid=89&search=&level=1&city=Bangalore&wap=1&max=21&pg_no=1&source=2&version=5.3.4&native=1&highres=2";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
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
        } catch (JSONException e) {
            e.printStackTrace();
        }
        finally {
           populateListView(processJsonObject(obj));
        }
    }

    public void populateListView(JSONObject elements){

        //populating the list view
        list = (ListView) findViewById(R.id.listView);
        list.setAdapter(new ListViewAdapter(this,elements));
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
