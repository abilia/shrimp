package com.abilia.shrimp.io;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class GetGreetingTask extends AsyncTask<Void, Void, String> {
    private static final String TAG = "GetGreetingTask";

    @Override
    protected String doInBackground(Void... voids) {
        try {
            return getDataFromServer("http://www.my-abilia.se/shrimp/persons.json");
        } catch (IOException e) {
            Log.e(TAG, "Couldn't get greetings", e);
        }
        return null;
    }

    public static String getDataFromServer(String url) throws IOException {
        URL jsonUrl = new URL(url);
        URLConnection dc = jsonUrl.openConnection();

        dc.setConnectTimeout(5000);
        dc.setReadTimeout(5000);

        BufferedReader inputStream = new BufferedReader(new InputStreamReader(
                dc.getInputStream()));

        String line;
        StringBuilder builder = new StringBuilder();
        while((line = inputStream.readLine()) != null) {
            builder.append(line);
        }
        return builder.toString();
    }
}
