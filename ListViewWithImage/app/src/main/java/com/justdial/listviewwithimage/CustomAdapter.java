package com.justdial.listviewwithimage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.AsyncTask;


class CustomAdapter extends ArrayAdapter{
    public CustomAdapter(Context context, String[] foods) {
        super(context,R.layout.custom_row, foods);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.custom_row,parent,false);
        String stringFoodItem = (String) getItem(position);
        TextView textView = (TextView) customView.findViewById(R.id.imageText);
        ImageView imageView = (ImageView) customView.findViewById(R.id.imageView);
        textView.setText(stringFoodItem);
        imageView.setImageResource(R.drawable.images);
        return customView;

    }
}
class MyTask extends AsyncTask<Void,Void,Void>{
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... params) {
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
