package com.example.a4vjf.restaurantmaterialdesign;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by 4vjf on 02-Jul-16.
 */
public class MainRecyclerViewAdapter extends RecyclerView.Adapter {
    private RequestQueue requestQueue;
    private  VolleySingleton volleySingleton;
    LayoutInflater inflater;
    Context mCtx;
    JSONObject obj;
    ImageLoader imageLoader;


    MainRecyclerViewAdapter(Context context, JSONObject obj){
        mCtx = context;
        this.obj = obj;
        inflater = LayoutInflater.from(context);
        volleySingleton = VolleySingleton.getInstance();
        requestQueue = volleySingleton.getRequestQueue();
        imageLoader = volleySingleton.getImageLoader();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // banner view
        if(viewType == 0) {
            CardView view = (CardView) inflater.inflate(R.layout.banner_layout, parent, false);
            RecyclerView bannerRecyclerView = (RecyclerView) view.findViewById(R.id.bannerRecyclerView);
            bannerRecyclerView.setLayoutManager(new LinearLayoutManager(mCtx, LinearLayoutManager.HORIZONTAL, false));
            bannerRecyclerView.setAdapter(new BannerAdapter(mCtx,obj.optJSONArray(Keys.BANNER)));
            return new RecyclerView.ViewHolder(view) {
                @Override
                public String toString() {
                    return super.toString();
                }
            };
        }

        if (viewType == 1){
            JSONArray task = obj.optJSONArray(Keys.TASK);
            LinearLayout view= (LinearLayout) inflater.inflate(R.layout.task_layout,null);
            for(int i=0;i<task.length();i++) {
                View newView = inflater.inflate(R.layout.task_element,null);
                ImageView imageView = (ImageView)newView.findViewById(R.id.taskElementImage);
                TextView textView = (TextView)newView.findViewById(R.id.taskElementText);
                textView.setText(task.optJSONObject(i).optString(Keys.CATDNAME));
                imageLoader.get(UtilClass.getImagePath(task.optJSONObject(i).optString(Keys.IMG)), ImageLoader.getImageListener(imageView,R.drawable.null_image,0));
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(R.dimen.zero_dp,LinearLayout.LayoutParams.MATCH_PARENT,1);
                params.setMarginStart(R.dimen.inside_margin_horizontal);
                params.setMarginEnd(R.dimen.inside_margin_horizontal);
                newView.setLayoutParams(params);
                view.addView(newView);
            }

            return new RecyclerView.ViewHolder(view) {
                @Override
                public String toString() {
                    return super.toString();
                }
            };
        }
        if (viewType == 2){
            View view = inflater.inflate(R.layout.category_section_layout,parent,false);
            return new RecyclerView.ViewHolder(view) {
                @Override
                public String toString() {
                    return super.toString();
                }
            };
        }

            View view = inflater.inflate(R.layout.suggestion_element,parent,false);
            return new RecyclerView.ViewHolder(view) {
                @Override
                public String toString() {
                    return super.toString();
                }
            };


    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Log.i("vyom","onBindViewHolder() called");
    }


    @Override
    public int getItemViewType(int position) {
        if(position<3){
            return position;
        }
        return 3;
    }

    @Override
    public int getItemCount() {
        return 6;
    }


}
