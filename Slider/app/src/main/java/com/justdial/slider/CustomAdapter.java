package com.justdial.slider;

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

/**
 * Created by justdial on 23/6/16.
 */
public class CustomAdapter extends PagerAdapter {
    private int[] imageArray;
    private Context ctx;
    private LayoutInflater inflater;
    private String[] bannerDescription;


    public CustomAdapter(Context ctx, int[] imageArray, String[] bannerDescription){
        this.ctx = ctx;
        this.imageArray = imageArray;
        this.bannerDescription = bannerDescription;
        inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return imageArray.length;
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
        ImageView imageView = (ImageView) view.findViewById(R.id.bannerImage);
        TextView textView = (TextView) view.findViewById(R.id.bannerDescriptionText);
        imageView.setImageResource(imageArray[position]);
        textView.setText(bannerDescription[position]);
        container.addView(view);
        Log.i("TAG","view attached");
        return view;
    }
}
