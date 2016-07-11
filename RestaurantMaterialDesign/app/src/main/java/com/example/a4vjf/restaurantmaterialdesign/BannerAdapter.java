package com.example.a4vjf.restaurantmaterialdesign;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by 4vjf on 02-Jul-16.
 */
public class BannerAdapter extends RecyclerView.Adapter<BannerAdapter.BannerViewHolder>{
    private  ImageLoader imageLoader;
    private RequestQueue requestQueue;
    private  VolleySingleton volleySingleton;
    private LayoutInflater inflater;
    private JSONArray banner;
    BannerAdapter(Context context, JSONArray banner){
        inflater = LayoutInflater.from(context);
        this.banner = banner;
        volleySingleton = VolleySingleton.getInstance();
        requestQueue = volleySingleton.getRequestQueue();
        imageLoader = volleySingleton.getImageLoader();
    }

    @Override
    public BannerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        FrameLayout frameLayout = (FrameLayout) inflater.inflate(R.layout.banner_element,parent,false);
        BannerViewHolder bannerViewHolder = new BannerViewHolder(frameLayout);
        return bannerViewHolder;

    }

    @Override
    public void onBindViewHolder(BannerViewHolder holder, int position) {
        String description = banner.optJSONObject(position).optString(Keys.CATDNAME);
        String imgPath = UtilClass.getImagePath(banner.optJSONObject(position).optString(Keys.IMG));
        imageLoader.get(imgPath, ImageLoader.getImageListener(holder.imageView,R.drawable.null_image,0));
        holder.textView.setText(description);
    }


    @Override
    public int getItemCount() {
        return banner.length();
    }

    public class BannerViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        ImageView imageView;
        public BannerViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.bannerText);
            imageView = (ImageView) itemView.findViewById(R.id.bannerImage);
        }
    }
}
