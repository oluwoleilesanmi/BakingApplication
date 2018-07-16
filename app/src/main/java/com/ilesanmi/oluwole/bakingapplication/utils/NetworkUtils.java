package com.ilesanmi.oluwole.bakingapplication.utils;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by abayomi on 19/03/2018.
 */

public class NetworkUtils {

    private static final String TAG = NetworkUtils.class.getSimpleName();
    public static final String BASE_URL = "http://go.udacity.com/";
    public static final String FEED = "android-baking-app-json";
    private static final String URL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";

    public static URL buildUrlForRemoteJsonFile() throws MalformedURLException {
        Uri builtUri = Uri.parse(URL);
        URL url = new URL(builtUri.toString());
        return url;
    }


    public static String getResponseFromHttpUrl() throws IOException {

        HttpURLConnection urlConnection = (HttpURLConnection) buildUrlForRemoteJsonFile().openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
