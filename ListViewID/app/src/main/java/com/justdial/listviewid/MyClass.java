package com.justdial.listviewid;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.widget.LinearLayout;

public class MyClass extends AsyncTask<String,Integer,Boolean> {
    private int contentLength, counter = 0;
    Activity activity;
    int calculatedProgress = 0;


    public MyClass(Activity activity) {

        this.activity = activity;

    }

    public void onAttach(Activity activity) {
        this.activity = activity;
    }

    public void onDetach() {
        activity = null;
    }

    @Override
    protected void onPreExecute() {


        if(activity!=null) {

            ((MainActivity) activity).showProgress();
        }
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        if (activity != null) {
            calculatedProgress = ((int) ((double) counter / contentLength * 10000));
            ((MainActivity) activity).updateProgress(calculatedProgress);
        }

    }

    @Override
    protected Boolean doInBackground(String... params) {
        boolean success = false;
        URL urlObject;
        HttpURLConnection connection =null;
        InputStream inputStream = null;
        FileOutputStream outputStream = null;
        File file = null;
        try {
            urlObject = new URL(params[0]);
            connection = (HttpURLConnection) urlObject.openConnection();
            inputStream =  connection.getInputStream();
            Uri uri = Uri.parse(params[0]);
            file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath() + "/" + uri.getLastPathSegment());
            outputStream = new FileOutputStream(file);
            byte[] buffer = new byte[1024];
            int read = -1;
            contentLength = connection.getContentLength();
            while((read = inputStream.read(buffer)) != -1){
                outputStream.write(buffer,0,read);
                counter = counter+read;
                publishProgress(counter);
            }
            success = true;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(connection!=null){
                connection.disconnect();
            }
            if(inputStream!=null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(outputStream!=null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return success;

    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        if(activity!=null) {
            ((MainActivity) activity).hideProgress();
        }
    }
}
