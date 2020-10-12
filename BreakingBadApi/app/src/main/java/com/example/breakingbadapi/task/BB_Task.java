package com.example.breakingbadapi.task;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import com.example.breakingbadapi.task.contract.TaskListener;
import com.example.breakingbadapi.data.BB_Person;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BB_Task extends AsyncTask<Object, Object, Object>  {

    private TaskListener taskListener;
    private List<BB_Person> values;
    private List<Bitmap> bitmaps;
    String url;

    public BB_Task(TaskListener taskListener, String url) {
        this.taskListener = taskListener;
        this.url = url;
    }

    @Override
    protected Object doInBackground(Object... objects) {
        String result = "";

        try {
            InputStream is = new URL(url).openStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            result = rd.readLine();

            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            values = Arrays.asList(mapper.readValue(result, BB_Person[].class));

            bitmapLoader();

        } catch (Exception ex) {
            Log.d("breakinbadapi", "BB_Task: " + ex.getMessage());
        }

        return null;
    }

    private void bitmapLoader(){
        bitmaps = new ArrayList<Bitmap>();

        for (BB_Person bb_person : values) {
            try {
                InputStream is = new URL(bb_person.getImg()).openStream();
                bitmaps.add(BitmapFactory.decodeStream(is));
            } catch (Exception ex) {
                Log.d("breakinbadapi", "BB_Task bitmapLoader: " + ex.getMessage());
            }
        }
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        taskListener.provideContent(values, bitmaps);
    }
}
