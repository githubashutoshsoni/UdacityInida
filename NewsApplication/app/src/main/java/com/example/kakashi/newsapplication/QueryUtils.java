package com.example.kakashi.newsapplication;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import android.text.TextUtils;

import java.util.List;

/**
 * Helper methods related to requesting and receiving earthquake data from USGS.
 */
public final class QueryUtils {

    /** Tag for the log messages */
    private static final String LOG_TAG = QueryUtils.class.getSimpleName();


    private QueryUtils() {
    }


    public static List<News> fetchNewsData(String requestUrl) {
        Log.i("News", "fetchNewsData");
        // Create URL object
        URL url = createUrl(requestUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        // Extract relevant fields from the JSON response and create a list of {@link Earthquake}s
        List<News> news = extractFeatureFromJson(jsonResponse);

        // Return the list of {@link Earthquake}s
        return news;
    }

    /**
     * Returns new URL object from the given string URL.
     */
    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problem building the URL ", e);
        }
        return url;
    }

    /**
     * Make an HTTP request to the given URL and return a String as the response.
     */
    private static String makeHttpRequest(URL url) throws IOException {
        Log.i("Earthquake", "makeHttpRequest");
        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
        if (urlConnection.getResponseCode() == urlConnection.HTTP_OK) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the earthquake JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // Closing the input stream could throw an IOException, which is why
                // the makeHttpRequest(URL url) method signature specifies than an IOException
                // could be thrown.
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    /**
     * Convert the {@link InputStream} into a String which contains the
     * whole JSON response from the server.
     */
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        Log.i("Earthquake", "String Builder");
        return output.toString();
    }

    private static List<News> extractFeatureFromJson(String newsJSON) {
        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(newsJSON)) {
            return null;
        }

        // Create an empty ArrayList that we can start adding Networks to
        List<News> news = new ArrayList<>();

         try {

            JSONObject baseJsonResponse = new JSONObject(newsJSON);
            JSONObject jsonResponse= baseJsonResponse.getJSONObject("response");
            // Extract the JSONArray associated with the key called "features",
            // which represents a list of features (or Networks).
            JSONArray newsResultsArray = jsonResponse.getJSONArray("results");
             String authorName= "";
             String date="";
             StringBuilder authors= new StringBuilder();

             // For each earthquake in the newsResultsArray, create an {@link Earthquake} object
            for (int i = 0; i < newsResultsArray.length(); i++) {

                // Get a single earthquake at position i within the list of Networks
                JSONObject currentNews = newsResultsArray.getJSONObject(i);
                JSONArray tagsJSONArray= currentNews.getJSONArray("tags");

                for(int j=0;j<tagsJSONArray.length();j++)
                {
                    JSONObject tagsObject= tagsJSONArray.getJSONObject(j);
                    authorName = tagsObject.getString("webTitle");
                    authors.append(authorName+"    ");
                }

                date=currentNews.getString("webPublicationDate");
                authorName= authors.toString();

                String title = currentNews.getString("webTitle");
                String sectionName = currentNews.getString("sectionName");
                String URL = currentNews.getString("webUrl");
                //emptying authors for the next iteration.
                authors= new StringBuilder();

                News singleNew = new News(title, URL, sectionName,authorName,date);

                news.add(singleNew);
            }

        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the  JSON results", e);
        }

        // Return the list of Networks
        return news ;
    }

}