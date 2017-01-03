package com.komugi.webservices;

import android.util.Log;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



public class JSONMethods {

    ArrayList<NameValuePair> nameValuePairs;
    JSONParser jsonparser = new JSONParser();

    public String register(String first_name, String last_name,String pass,String email,String contact_no,String devicetoken,String devicetype,String terms) {
        nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("first_name", first_name));
        nameValuePairs.add(new BasicNameValuePair("last_name", last_name));
        nameValuePairs.add(new BasicNameValuePair("pass", pass));
        nameValuePairs.add(new BasicNameValuePair("email", email));
        nameValuePairs.add(new BasicNameValuePair("contact_no", contact_no));
        nameValuePairs.add(new BasicNameValuePair("devicetoken", devicetoken));
        nameValuePairs.add(new BasicNameValuePair("devicetype", devicetype));
        nameValuePairs.add(new BasicNameValuePair("terms", terms));
        return jsonparser.getJSONFromUrl(URLs.register, nameValuePairs);
    }
    public String login(String email,String password,String devicetoken,String devicetype) {
        nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("email", email));
        nameValuePairs.add(new BasicNameValuePair("password", password));
        nameValuePairs.add(new BasicNameValuePair("devicetoken", devicetoken));
        nameValuePairs.add(new BasicNameValuePair("devicetype", devicetype));
        Log.e("login",nameValuePairs.toString());
        return jsonparser.getJSONFromUrl(URLs.login, nameValuePairs);
    }

    public String changePass(String user_id,String password) {
        nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("user_id", user_id));
        nameValuePairs.add(new BasicNameValuePair("password", password));
        Log.e("login",nameValuePairs.toString());
        return jsonparser.getJSONFromUrl(URLs.changePassword, nameValuePairs);
    }

    public String forgotPass(String email) {
        nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("email", email));
        Log.e("login",nameValuePairs.toString());
        return jsonparser.getJSONFromUrl(URLs.forgotPassword, nameValuePairs);
    }
    public String termsAndPrivacy(String id) {
        nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("id", id));
        Log.e("login",nameValuePairs.toString());
        return jsonparser.getJSONFromUrl(URLs.termsAndPrivacy, nameValuePairs);
    }
}
