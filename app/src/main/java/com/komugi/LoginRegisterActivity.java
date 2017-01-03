package com.komugi;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.komugi.edittexttypeface.CenturyGothicItalicEditText;
import com.komugi.more.MoreBottomAdapter;
import com.komugi.more.MyProfileActivity;
import com.komugi.newsAndPromo.NewsAndPromoActivity;
import com.komugi.stamps.StampsActivity;
import com.komugi.textviewtypeface.CentuaryGothicBoldTextView;
import com.komugi.textviewtypeface.CenturyGothicItalicTextView;
import com.komugi.utils.Constants;
import com.komugi.vouchers.VoucherActivity;
import com.komugi.wallet.WalletActivity;
import com.komugi.webservices.JSONMethods;

import org.json.JSONObject;

import java.util.ArrayList;

public class LoginRegisterActivity extends Activity implements View.OnClickListener{

    int height,width;
    RelativeLayout registerLayout,loginLayout,subRegisterationLay1,subRegisterationLay2,subLoginLay,forgotPasswordLay;
    LinearLayout header,bottomLay,webViewLay;
    CentuaryGothicBoldTextView loginTv,currentBalanceTv;
    ImageView alreadyUserImg,registerImg,registerLoginImg,loginImg,okRegImg,closePrivacy,resetPaswrdImg,bottomMoreImg,
    bottomNewsImg,bottomVoucherImg,bottomWalletImg,bottomStampImg;
    CenturyGothicItalicEditText firstNameEDt,lastNameEDt,contactEdt,emailEdt,passwordEdt,cnfrmPasswordEdt,inviteCodeEdt,
            loginEmailEdt,loginPasswordEdt,forgotPasswordEmail;
    CenturyGothicItalicTextView termsAndCondtn,forgotPassTv;
    CheckBox termCondCheck;
    WebView webView;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    Spinner spinner;
    MoreBottomAdapter spinnerAdapter;
    ArrayList<String> spinnerList=new ArrayList<>();
    ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_register_activity);
        initialize();
    }

    public void initialize(){

        spinnerList.add("MY PROFILE");
        spinnerList.add("SHARE TO FRIEND");
        spinnerList.add("ABOUT");
        spinnerList.add("SOCIAL MEDIA");
        spinnerList.add("LOCATION");
        spinnerList.add("LOGOUT");

        Constants.spinnerList=spinnerList;


        sharedPref = getSharedPreferences(Constants.PREFS_NAME, 0);
        editor = sharedPref.edit();

        spinner= (Spinner) findViewById(R.id.spinner_location);

        spinnerAdapter=new MoreBottomAdapter(LoginRegisterActivity.this,LoginRegisterActivity.this,spinnerList);

        spinner.setAdapter(spinnerAdapter);
        spinner.setPrompt("");

        Display display = getWindowManager().getDefaultDisplay();
        width = display.getWidth();
        height = display.getHeight();

        header=(LinearLayout)findViewById(R.id.header_lay);
        bottomLay=(LinearLayout)findViewById(R.id.bottom_lay);
        webViewLay=(LinearLayout)findViewById(R.id.webview_container);

        registerLayout=(RelativeLayout)findViewById(R.id.register_lay);
        loginLayout=(RelativeLayout)findViewById(R.id.login_lay);
        subRegisterationLay1=(RelativeLayout)findViewById(R.id.register_lay1);
        subRegisterationLay2=(RelativeLayout)findViewById(R.id.register_lay2);
        subLoginLay=(RelativeLayout)findViewById(R.id.loged_in_layout);
        forgotPasswordLay=(RelativeLayout)findViewById(R.id.forgot_password_layout);

        header.getLayoutParams().height=(int)(0.15*height);

        termCondCheck=(CheckBox)findViewById(R.id.term_cond_check_box);

        loginTv=(CentuaryGothicBoldTextView)findViewById(R.id.left_heading_tv);
        currentBalanceTv=(CentuaryGothicBoldTextView)findViewById(R.id.right_heading_tv);

        firstNameEDt=(CenturyGothicItalicEditText)findViewById(R.id.first_name_edt);
        lastNameEDt=(CenturyGothicItalicEditText)findViewById(R.id.last_name_edt);
        contactEdt=(CenturyGothicItalicEditText)findViewById(R.id.contact_no_edt);
        emailEdt=(CenturyGothicItalicEditText)findViewById(R.id.email_address_edt);
        passwordEdt=(CenturyGothicItalicEditText)findViewById(R.id.password_edt);
        cnfrmPasswordEdt=(CenturyGothicItalicEditText)findViewById(R.id.cnfrm_password_edt);
        inviteCodeEdt=(CenturyGothicItalicEditText)findViewById(R.id.invit_code_edt);
        loginEmailEdt=(CenturyGothicItalicEditText)findViewById(R.id.email_address_login_edt);
        loginPasswordEdt=(CenturyGothicItalicEditText)findViewById(R.id.password_login_edt);
        forgotPasswordEmail=(CenturyGothicItalicEditText)findViewById(R.id.forgot_email_address_edt);

        termsAndCondtn=(CenturyGothicItalicTextView)findViewById(R.id.term_cond_tv);
        forgotPassTv=(CenturyGothicItalicTextView)findViewById(R.id.forgot_pass__tv);

        webView=(WebView)findViewById(R.id.webView);
        webView.loadUrl("file:///android_asset/privacy.html");

        alreadyUserImg=(ImageView)findViewById(R.id.already_user_img);
        registerImg=(ImageView)findViewById(R.id.register_img);
        registerLoginImg=(ImageView)findViewById(R.id.register_login_img);
        loginImg=(ImageView)findViewById(R.id.login_img);
        okRegImg=(ImageView)findViewById(R.id.ok_reg_img);
        closePrivacy=(ImageView)findViewById(R.id.cross_iv);
        resetPaswrdImg=(ImageView)findViewById(R.id.reset_pass_img);
        bottomMoreImg=(ImageView)findViewById(R.id.more_img);
        bottomNewsImg=(ImageView)findViewById(R.id.news_img);
        bottomVoucherImg=(ImageView)findViewById(R.id.voucher_img);
        bottomWalletImg=(ImageView)findViewById(R.id.wallet_img);
        bottomStampImg=(ImageView)findViewById(R.id.stamp_img);

        loginTv.setOnClickListener(this);
        alreadyUserImg.setOnClickListener(this);
        registerImg.setOnClickListener(this);
        registerLoginImg.setOnClickListener(this);
        loginImg.setOnClickListener(this);
        okRegImg.setOnClickListener(this);
        termsAndCondtn.setOnClickListener(this);
        closePrivacy.setOnClickListener(this);
        forgotPassTv.setOnClickListener(this);
        resetPaswrdImg.setOnClickListener(this);
        bottomMoreImg.setOnClickListener(this);
        bottomNewsImg.setOnClickListener(this);
        bottomVoucherImg.setOnClickListener(this);
        bottomWalletImg.setOnClickListener(this);
        bottomStampImg.setOnClickListener(this);

        spinner.getLayoutParams().height=bottomLay.getHeight();

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.news_img:
                Intent news=new Intent(LoginRegisterActivity.this, NewsAndPromoActivity.class);
                startActivity(news);
                overridePendingTransition(0,0);
                LoginRegisterActivity.this.finish();
                break;

            case R.id.voucher_img:
                Intent voucher=new Intent(LoginRegisterActivity.this, VoucherActivity.class);
                startActivity(voucher);
                overridePendingTransition(0,0);
                LoginRegisterActivity.this.finish();
                break;

            case R.id.wallet_img:
                Intent wallet=new Intent(LoginRegisterActivity.this, WalletActivity.class);
                startActivity(wallet);
                overridePendingTransition(0,0);
                LoginRegisterActivity.this.finish();
                break;

            case R.id.stamp_img:
                Intent stamp=new Intent(LoginRegisterActivity.this, StampsActivity.class);
                startActivity(stamp);
                overridePendingTransition(0,0);
                LoginRegisterActivity.this.finish();
                break;

            case R.id.left_heading_tv:
                registerLayout.setVisibility(View.VISIBLE);
                loginTv.setEnabled(false);
                loginTv.setAlpha(0.8f);
                break;

            case R.id.already_user_img:
                registerLayout.setVisibility(View.GONE);
                loginLayout.setVisibility(View.VISIBLE);
                break;

            case R.id.register_img:
                checkRegister();
                break;

            case R.id.register_login_img:
                registerLayout.setVisibility(View.VISIBLE);
                loginLayout.setVisibility(View.GONE);
                break;

            case R.id.login_img:
                checkLogin();
                break;

            case R.id.ok_reg_img:
                Intent it=new Intent(LoginRegisterActivity.this, MyProfileActivity.class);
                startActivity(it);
                overridePendingTransition(0,0);
                LoginRegisterActivity.this.finish();
                break;

            case R.id.term_cond_tv:
                webViewLay.setVisibility(View.VISIBLE);
                break;

            case R.id.cross_iv:
                webViewLay.setVisibility(View.GONE);
                break;

            case R.id.forgot_pass__tv:
                subLoginLay.setVisibility(View.GONE);
                forgotPasswordLay.setVisibility(View.VISIBLE);
                break;

            case R.id.reset_pass_img:
            if(TextUtils.isEmpty(forgotPasswordEmail.getText().toString().trim())){
                    forgotPasswordEmail.setError("Enter Email Address");
           }
            else if(!Constants.isValidEmail(forgotPasswordEmail.getText().toString().trim())){
                   forgotPasswordEmail.setError("Enter Valid Email");
           }
            else {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(forgotPasswordEmail.getWindowToken(), 0);
                if (Constants.isNetworkAvailable(LoginRegisterActivity.this)) {
                    new ForgotPassTask("" + forgotPasswordEmail.getText().toString().trim()).execute();
                } else {
                    Constants.alertDialog(LoginRegisterActivity.this, "Internet connection is down.");
                }
            }
                break;

            case R.id.more_img:
                spinner.performClick();
                break;


        }
    }

    public void checkRegister(){
        if(TextUtils.isEmpty(firstNameEDt.getText().toString().trim())){
            firstNameEDt.setError("Enter First Name");
        }
        else if(TextUtils.isEmpty(lastNameEDt.getText().toString().trim())){
            lastNameEDt.setError("Enter Last Name");
        }
        else if(TextUtils.isEmpty(contactEdt.getText().toString().trim())){
            contactEdt.setError("Enter Contact Number");
        }
        else if(TextUtils.isEmpty(emailEdt.getText().toString().trim())){
            emailEdt.setError("Enter Email Address");
        }
        else if(!Constants.isValidEmail(emailEdt.getText().toString().trim())){
            emailEdt.setError("Enter Valid Email");
        }
        else if(TextUtils.isEmpty(passwordEdt.getText().toString().trim())){
            passwordEdt.setError("Enter Password");
        }
        else if(passwordEdt.getText().toString().trim().length()<6){
            passwordEdt.setError("Minimum Password length should be 6 characters");
        }
        else if(TextUtils.isEmpty(cnfrmPasswordEdt.getText().toString().trim())){
            cnfrmPasswordEdt.setError("Enter Confirm Password");
        }
        else if(!passwordEdt.getText().toString().trim().equals(cnfrmPasswordEdt.getText().toString().trim())){
            cnfrmPasswordEdt.setError("Password and Confirm password must be same");
        }
        else if(!termCondCheck.isChecked()){
            Toast.makeText(LoginRegisterActivity.this,"Please Agree terms and Conditions",Toast.LENGTH_SHORT).show();
        }
        else{
           if(Constants.isNetworkAvailable(LoginRegisterActivity.this)) {
               new RegisterTask(emailEdt.getText().toString().trim(), passwordEdt.getText().toString(), firstNameEDt.getText().toString().trim(), lastNameEDt.getText().toString().trim(), contactEdt.getText().toString().trim(), "1").execute();
           }else{
               Constants.alertDialog(LoginRegisterActivity.this,"Internet connection is down.");
           }
        }
    }

    public void checkLogin(){

         if(TextUtils.isEmpty(loginEmailEdt.getText().toString().trim())){
             loginEmailEdt.setError("Enter Email Address");
        }
         else if(!Constants.isValidEmail(loginEmailEdt.getText().toString().trim())){
             loginEmailEdt.setError("Enter Valid Email");
        }
        else if(TextUtils.isEmpty(loginPasswordEdt.getText().toString().trim())){
             loginPasswordEdt.setError("Enter Password");
        }
        else{
             //navigate to news and promo screen
            String pass=""+loginPasswordEdt.getText().toString().trim();
            if(Constants.isNetworkAvailable(LoginRegisterActivity.this)){
                  new LoginTask(loginEmailEdt.getText().toString().trim(),pass,"1").execute();
            }else{
                Constants.alertDialog(LoginRegisterActivity.this,"Internet connection is down.");
            }
         }
    }

    @Override
    public void onBackPressed() {

        if(webViewLay.getVisibility()==View.VISIBLE){
            webViewLay.setVisibility(View.GONE);
        }
        else if(forgotPasswordLay.getVisibility()==View.VISIBLE){
            forgotPasswordLay.setVisibility(View.GONE);
            subLoginLay.setVisibility(View.VISIBLE);
        }
       else if(registerLayout.getVisibility()==View.VISIBLE){
            if(subRegisterationLay2.getVisibility()==View.VISIBLE){
                subRegisterationLay1.setVisibility(View.VISIBLE);
                subRegisterationLay2.setVisibility(View.GONE);
            }
            else{
                registerLayout.setVisibility(View.GONE);
                loginTv.setEnabled(true);
                loginTv.setAlpha(1f);
            }
        }
        else if(loginLayout.getVisibility()==View.VISIBLE){
            loginLayout.setVisibility(View.GONE);
            registerLayout.setVisibility(View.VISIBLE);
        }
        else{
            LoginRegisterActivity.this.finish();
        }
    }

    public class RegisterTask extends AsyncTask<String, Integer, String> {

        String message, success,error;
        JSONObject jsonObject = null;
        JSONMethods jsonMethod;
        String email,password,firstName,lastName,contactNumber,deviceToken;
        public RegisterTask(String email, String password,String firstName,String lastName,String contactNumber,String deviceToken) {
            this.email = email;
            this.password = password;
            this.firstName = firstName;
            this.lastName = lastName;
            this.contactNumber = contactNumber;
            this.deviceToken = deviceToken;
        }

        @Override
        protected void onPreExecute() {
                dialog = new ProgressDialog(LoginRegisterActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setMessage("Loading...");
                dialog.setCancelable(false);
                dialog.show();
        }

        @Override
        protected String doInBackground(String... arg0) {
            jsonMethod = new JSONMethods();
            try {
                String response = jsonMethod.register(firstName,lastName,password,email,contactNumber,deviceToken,Constants.DeviceType,"1");
                if (Constants.checkString(response)) {
                   jsonObject = new JSONObject(response);
                    if (jsonObject instanceof JSONObject) {
                        System.out.println("register response    " + response);

                        error=jsonObject.optString("error");


                        if(error.toString().trim().equalsIgnoreCase("0")){
                            editor.putString("user_id",""+jsonObject.optString("user_id"));
                            editor.commit();
                        }
                         if(jsonObject.has("Message")){
                             message = jsonObject.optString("Message");
                         }

                    } else {
                       success = "0";
                       message = "Internal Server Error";
                    }
                } else {
                    success = "0";
                    message = "Internal Server Error";
                }

            } catch (Exception e) {
                e.printStackTrace();
                success = "0";
                message = "Internal Server Error";

            }
            return null;
        }

        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            try{
                if(dialog!=null){
                    dialog.dismiss();
                }
            }catch(Exception e){

            }
            if(error.toString().trim().equalsIgnoreCase("0")) {
                editor.putString("name",firstNameEDt.getText().toString().trim()+" "+lastNameEDt.getText().toString().trim());
                editor.putString("contact",contactEdt.getText().toString().trim());
                editor.putString("email",emailEdt.getText().toString().trim());
                editor.putString("password",""+passwordEdt.getText().toString().trim());
                editor.putString("gender","");
                editor.putString("birthday","");
                editor.commit();
                subRegisterationLay1.setVisibility(View.GONE);
                subRegisterationLay2.setVisibility(View.VISIBLE);
            }else{
               Constants.alertDialog(LoginRegisterActivity.this,""+message);
            }
            }
        }

    public class LoginTask extends AsyncTask<String, Integer, String> {

        String message, success,error;
        JSONObject jsonObject = null;
        JSONMethods jsonMethod;
        String username,loginPassword,deviceToken;
        public LoginTask(String username, String loginPassword,String deviceToken) {
            this.username = username;
            this.loginPassword = loginPassword;
            this.deviceToken = deviceToken;
        }

        @Override
        protected void onPreExecute() {
                dialog = new ProgressDialog(LoginRegisterActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setMessage("Loading...");
                dialog.setCancelable(false);
                dialog.show();
        }

        @Override
        protected String doInBackground(String... arg0) {
            jsonMethod = new JSONMethods();
            try {
                String response = jsonMethod.login(username,loginPassword,deviceToken,Constants.DeviceType);
                if (Constants.checkString(response)) {
                    jsonObject = new JSONObject(response);
                    if (jsonObject instanceof JSONObject) {
                        System.out.println("login response    " + response);

                        error=jsonObject.optString("error");


                        if(error.toString().trim().equalsIgnoreCase("0")){
                            JSONObject jsonObject1=jsonObject.getJSONObject("UserInfo");
                            editor.putString("user_id",""+jsonObject1.optString("UserId"));
                            editor.putString("email",""+jsonObject1.optString("Email"));
                            editor.putString("name",""+jsonObject1.optString("Name"));
                            editor.putString("password",""+loginPassword);
                            editor.commit();
                        }
                        if(jsonObject.has("Message")){
                            message = jsonObject.optString("Message");
                        }

                    } else {
                        success = "0";
                        message = "Internal Server Error";
                    }
                } else {
                    success = "0";
                    message = "Internal Server Error";
                }

            } catch (Exception e) {
                e.printStackTrace();
                success = "0";
                message = "Internal Server Error";

            }
            return null;
        }

        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            try{
                if(dialog!=null){
                    dialog.dismiss();
                }
            }catch(Exception e){

            }
            if(error.toString().trim().equalsIgnoreCase("0")) {
                Intent i=new Intent(LoginRegisterActivity.this, NewsAndPromoActivity.class);
                startActivity(i);
                finish();
            }else{
                Constants.alertDialog(LoginRegisterActivity.this,""+message);
            }
        }
    }


    public class ForgotPassTask extends AsyncTask<String, Integer, String> {

        String message, success,error;
        JSONObject jsonObject = null;
        JSONMethods jsonMethod;
        String username;
        public ForgotPassTask(String username) {
            this.username = username;


        }

        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(LoginRegisterActivity.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setMessage("Loading...");
            dialog.setCancelable(false);
            dialog.show();
        }

        @Override
        protected String doInBackground(String... arg0) {
            jsonMethod = new JSONMethods();
            try {
                String response = jsonMethod.forgotPass(username);
                if (Constants.checkString(response)) {
                    jsonObject = new JSONObject(response);
                    if (jsonObject instanceof JSONObject) {
                        System.out.println("forgot response    " + response);

                        error=jsonObject.optString("error");

                        if(jsonObject.has("Message")){
                            message = jsonObject.optString("Message");
                        }

                    } else {
                        success = "0";
                        message = "Internal Server Error";
                    }
                } else {
                    success = "0";
                    message = "Internal Server Error";
                }

            } catch (Exception e) {
                e.printStackTrace();
                success = "0";
                message = "Internal Server Error";

            }
            return null;
        }

        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            try{
                if(dialog!=null){
                    dialog.dismiss();
                }
            }catch(Exception e){

            }
            if(error.toString().trim().equalsIgnoreCase("0")) {
                Toast.makeText(LoginRegisterActivity.this,"Password Reset Link has been sent to your Email Address",Toast.LENGTH_LONG).show();
            }else{
                Constants.alertDialog(LoginRegisterActivity.this,""+message);
            }
        }
    }
}