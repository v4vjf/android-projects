package com.justdial.asynctask;

import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class MyClass extends AsyncTask<Void,String,Void> {
    private ArrayAdapter<String> adapter;
    private int count=0;
    @Override
    protected void onPreExecute() {
        setProgressBarVisibility(true);
        adapter = (ArrayAdapter<String>)listView.getAdapter();
    }

    @Override
    protected Void doInBackground(Void... params) {
        for(String item:names){
            publishProgress(item);

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        return null;
    }


    @Override
    protected void onProgressUpdate(String... values) {
        adapter.add(values[0]);
        count++;
        setProgress((int)(((double)count/names.length)*10000));
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        Toast.makeText(MainActivity.this,"Download completed",Toast.LENGTH_SHORT).show();
        setProgressBarVisibility(false);
    }
}