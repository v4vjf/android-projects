/*package com.justdial.restaurant;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    RequestQueue requestQueue;
    String example;
    String URL = "http://win.justdial.com/26june2015/filternew.php?vid=89&search=&level=1&city=Bangalore&wap=1&max=21&pg_no=1&source=2&version=5.3.4&native=1&highres=2";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("TAG","onCreate");

        requestQueue = VolleySingleton.getInstance().getRequestQueue();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONObject>() {
                    JSONArray columns;
                    JSONArray banner;
                    JSONArray[] items;
                    JSONArray finalArray;
                    JSONArray data
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("TAG","JSON respomse");
                        try {
                            columns = response.getJSONObject("results").getJSONArray("columns");
                            banner = response.getJSONObject("results").getJSONArray("banner");
                            int n = response.getJSONObject("results").getJSONArray("columns").length();
                            JSONObject temp = new JSONObject();
                            for(int i=0;i<n;i++){
                                temp.put(columns.getString(i),banner.getString(i));
                            }
                            finalArray.put(temp);
                            
                            Log.i("tag",example+ items[1].getString(2));
                        } catch (JSONException e) {
                            Log.i("TAG","JSON error");
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("VOLLEY","Error!");
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

}
*/