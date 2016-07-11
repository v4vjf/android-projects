package com.justdial.restaurant;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

/**
 * Created by justdial on 27/6/16.
 */
public class ListViewAdapter extends BaseAdapter {
    private Context ctx;
    private JSONObject elements;

    private ImageLoader imageLoader;
    private VolleySingleton volleySingleton;
    private RequestQueue requestQueue;




    ListViewAdapter(Context c, JSONObject elements){
        this.ctx = c;
        this.elements = elements;
        volleySingleton = VolleySingleton.getInstance();
        requestQueue = volleySingleton.getRequestQueue();
        imageLoader = volleySingleton.getImageLoader();

    }
    int[] viewType={0,1,2,3};

    @Override
    public int getViewTypeCount() {
        return super.getViewTypeCount();
    }

    @Override
    public int getItemViewType(int position) {
        if(position<3)
            return viewType[position];
        return 0;
    }

    @Override
    public int getCount() {
        return elements.optInt(Keys.TOTAL_NUM_OF_RESULTS);
    }

    @Override
    public Object getItem(int position) {
        return new Object();
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row=null;

        if(position<3){
            //banner
            if(getItemViewType(position) == 0){
                row = inflater.inflate(R.layout.banner_slide,parent,false);
                ViewPager viewPager = (ViewPager) row.findViewById(R.id.viewPager);
                BannerAdapter adapter = new BannerAdapter(ctx,elements.optJSONArray(Keys.BANNER));
                viewPager.setAdapter(adapter);
            }
            else
                //task
                if(getItemViewType(position) == 1){
                    row = inflater.inflate(R.layout.tasks,parent,false);

                    ImageView imageView;
                    TextView textView;
                    JSONArray task = elements.optJSONArray(Keys.TASK);
                    //image view for order now hotkey
                    imageView = (ImageView)row.findViewById(R.id.orderNowImage);
                    textView = (TextView)row.findViewById(R.id.orderNowText);
                    imageLoader.get(UtilClass.getImagePath(task.optJSONObject(0).optString(Keys.IMG)),ImageLoader.getImageListener(imageView,R.drawable.null_image,0));
                    textView.setText(task.optJSONObject(0).optString(Keys.CATDNAME));

                    //image view for bookTable hotkey
                    imageView = (ImageView)row.findViewById(R.id.bookTableImage);
                    textView = (TextView)row.findViewById(R.id.bookTableText);
                    textView.setText(task.optJSONObject(1).optString(Keys.CATDNAME));
                    imageLoader.get(UtilClass.getImagePath(task.optJSONObject(1).optString(Keys.IMG)),ImageLoader.getImageListener(imageView,R.drawable.null_image,0));
                }

                else
                    //category section
                    if(getItemViewType(position) == 2){

                        /*
                            JSONArray category = elements.optJSONArray(Keys.CATEGORY);
                        row = inflater.inflate(R.layout.category_section,parent,false);
                        row.setMinimumHeight(parent.getMeasuredHeight());
                        ImageView imageView;
                        TextView textView;
                        imageView = (ImageView)row.findViewById(R.id.trendingImageView);
                        imageLoader.get(UtilClass.getImagePath(category.optJSONObject(0).optString(Keys.IMG)),ImageLoader.getImageListener(imageView,R.drawable.null_image,0));
                        textView = (TextView)row.findViewById(R.id.trendingText);
                        textView.setText(category.optJSONObject(0).optString(Keys.CATDNAME));
                        imageView = (ImageView)row.findViewById(R.id.cuisinesImageView);
                        imageView = (ImageView)row.findViewById(R.id.foodieDelightsImageView);
                        imageView = (ImageView)row.findViewById(R.id.pizzasImageView);
                        imageView = (ImageView)row.findViewById(R.id.coffeImageView);
                        imageView = (ImageView)row.findViewById(R.id.bakeriesImageView);
                        imageView = (ImageView)row.findViewById(R.id.dessersImageView);
                        imageView = (ImageView)row.findViewById(R.id.nightLifeImageView);
                        imageView = (ImageView)row.findViewById(R.id.viewAllImageView);
                         */

                        JSONArray category = elements.optJSONArray(Keys.CATEGORY);
                        row = inflater.inflate(R.layout.new_category_section,parent,false);
                        GridLayout gridLayout = (GridLayout) row.findViewById(R.id.categoryGridView);
                        int n = category.length();
                        int width = (parent.getWidth()-parent.getPaddingRight()-parent.getPaddingLeft())/2;
                        int height = (parent.getHeight()-120)/4;
                        for(int i=0;i<n;i++){
                            View view = inflater.inflate(R.layout.category_item,null);
                            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) new LinearLayout.LayoutParams(width,height);
                            ImageView imageView = (ImageView) view.findViewById(R.id.categoryItemImageView);
                            TextView textView = (TextView) view.findViewById(R.id.categoryItemText);
                            imageLoader.get(UtilClass.getImagePath(category.optJSONObject(i).optString(Keys.IMG)),ImageLoader.getImageListener(imageView,R.drawable.null_image,0));
                            textView.setText(category.optJSONObject(i).optString(Keys.CATDNAME));
                            view.setLayoutParams(params);
                            gridLayout.addView(view);
                        }
                    }
            return row;
        }

        row = inflater.inflate(R.layout.section_item,parent,false);
        int num = position - 3;
        ImageView imageView;
        TextView textView;
        JSONObject currObj = elements.optJSONArray(Keys.SUGGESTION).optJSONObject(num);
        String restaurantName = currObj.optString(Keys.CATDNAME);
        String imagePath = UtilClass.getImagePath(currObj.optString(Keys.IMG));
        imageView = (ImageView) row.findViewById(R.id.restaurantImage);
        textView = (TextView) row.findViewById(R.id.restaurantName);
        textView.setText(restaurantName);
        textView = (TextView) row.findViewById(R.id.restaurantDistance);
        textView.setText((10+position)+"km");
        textView = (TextView) row.findViewById(R.id.restaurantRating);
        textView.setText((num+1+0.5)+"");
        imageLoader.get(imagePath,ImageLoader.getImageListener(imageView,R.drawable.null_image,0));


        return row;
    }



}