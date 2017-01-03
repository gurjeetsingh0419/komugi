package com.komugi.webservices;


import android.util.Log;



import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Created by iApp on 10/7/2014.
 */
public class JSONParser {

    static InputStream is = null;
    static JSONObject jobj = null;
    static String json = "";
    int serverResponseCode;

    public JSONParser() {

    }

    public String getJSONFromUrl(String url, ArrayList<NameValuePair> nameValuePairs) {

        try {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);
            try {
                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            }

            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            is = httpEntity.getContent();


        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {

            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "UTF-8"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            json = sb.toString();
            Log.e("json data", json);
        } catch (Exception e) {
        }


        // return JSON String
        return json;

    }

    public String getJSONFromUrl2(String url, JSONObject nameValuePairs) {

        try {

            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);

            System.out.println("json      " + nameValuePairs.toString());
            httpPost.setEntity(new StringEntity(nameValuePairs.toString(), "UTF-8"));

            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            is = httpEntity.getContent();

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


        try {

            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "UTF-8"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            json = sb.toString();
            Log.e("json data", json);
        } catch (Exception e) {
        }


        // return JSON String
        return json;

    }

}
