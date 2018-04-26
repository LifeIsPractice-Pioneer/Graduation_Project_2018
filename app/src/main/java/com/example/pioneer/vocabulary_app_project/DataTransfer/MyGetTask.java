package com.example.pioneer.vocabulary_app_project.DataTransfer;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MyGetTask extends AsyncTask {

    @Override
    protected Object doInBackground(Object[] objects) {
        String username = objects[0].toString();
        String userpass = objects[1].toString();
        String path = objects[2].toString();
        try {
            URL url = new URL(path + "?account=" + username + "&password=" + userpass);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            if (connection.getResponseCode() == 200) {
                InputStream is = connection.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String s = br.readLine();
                return s;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
