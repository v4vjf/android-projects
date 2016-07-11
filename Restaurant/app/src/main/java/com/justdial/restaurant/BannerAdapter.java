package com.justdial.restaurant;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;

import org.json.JSONArray;

/**
 * Created by justdial on 23/6/16.
 */
public class BannerAdapter extends PagerAdapter {
    private Context ctx;
    private LayoutInflater inflater;
    private JSONArray bannerArray;
    private VolleySingleton volleySingleton;
    private ImageLoader imageLoader;
    private RequestQueue requestQueue;

    public BannerAdapter(Context ctx, JSONArray bannerArray){
        this.ctx = ctx;
        this.bannerArray = bannerArray;
        inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        volleySingleton = VolleySingleton.getInstance();
        requestQueue = volleySingleton.getRequestQueue();
        imageLoader = volleySingleton.getImageLoader();
    }

    @Override
    public int getCount() {
        return bannerArray.length();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == (FrameLayout)object);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((FrameLayout)object);
        Log.i("TAG","veiw destroyed");
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = inflater.inflate(R.layout.banner_layout,container,false);


        String description = bannerArray.optJSONObject(position).optString(Keys.CATDNAME);
        String imgPath = UtilClass.getImagePath(bannerArray.optJSONObject(position).optString(Keys.IMG));
        ImageView imageView = (ImageView) view.findViewById(R.id.bannerImageView);
        TextView textView = (TextView) view.findViewById(R.id.bannerDescriptionText);
        textView.setText(description);
        imageLoader.get(imgPath,ImageLoader.getImageListener(imageView,R.drawable.null_image,0));

        container.addView(view);
        Log.i("TAG","view attached");
        return view;
    }

}
