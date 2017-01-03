package com.komugi.more;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.codetroopers.betterpickers.calendardatepicker.CalendarDatePickerDialogFragment;
import com.codetroopers.betterpickers.calendardatepicker.MonthAdapter;
import com.komugi.LoginRegisterActivity;
import com.komugi.R;
import com.komugi.edittexttypeface.CenturyGothicEditText;
import com.komugi.newsAndPromo.NewsAndPromoActivity;
import com.komugi.stamps.StampsActivity;
import com.komugi.textviewtypeface.CentuaryGothicBoldTextView;
import com.komugi.textviewtypeface.CenturyGothicTextView;
import com.komugi.utils.Constants;
import com.komugi.vouchers.VoucherActivity;
import com.komugi.wallet.WalletActivity;
import com.komugi.webservices.JSONMethods;

import org.joda.time.DateTime;
import org.json.JSONObject;

/**
 * Created by iapp on 20/12/16.
 */
public class MyProfileActivity extends ActionBarActivity implements View.OnClickListener,CalendarDatePickerDialogFragment.OnDateSetListener{

    int height,width;
    LinearLayout header,bottomLay;
    ImageView myProfileImg,addBirthdayImg,changePasswordImg,changePasswordheaderImg,savePasswordImg,cancelPasswordImg,
            bottomMoreImg,bottomNewsImg,bottomVoucherImg,bottomWalletImg,bottomStampImg;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    CenturyGothicTextView nameTv,contactNoTv,emailTv,birthdayTv,genderTv,passwordTv;
    CentuaryGothicBoldTextView userNameTv,balanceTv;
    CenturyGothicEditText oldPassEdt,newPassEdt,retypeNewPassEDt;
    RelativeLayout profileLayout,changePasswordLayout;
    MoreBottomAdapter spinnerAdapter;
    Spinner spinner;
    String userID="",loggedPassword="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_profile_activity);
        initializeView();
    }

    public void initializeView(){

        sharedPref = getSharedPreferences(Constants.PREFS_NAME, 0);
        editor = sharedPref.edit();


        spinner= (Spinner) findViewById(R.id.spinner_location);

        spinnerAdapter=new MoreBottomAdapter(MyProfileActivity.this,MyProfileActivity.this,Constants.spinnerList);

        spinner.setAdapter(spinnerAdapter);
        spinner.setPrompt("");

        Display display = getWindowManager().getDefaultDisplay();
        width = display.getWidth();
        height = display.getHeight();

        header=(LinearLayout)findViewById(R.id.header_lay);
        header.getLayoutParams().height=(int)(0.15*height);

        bottomLay=(LinearLayout)findViewById(R.id.bottom_lay);

        profileLayout=(RelativeLayout)findViewById(R.id.profile_lay);
        changePasswordLayout=(RelativeLayout)findViewById(R.id.change_password_lay);

        myProfileImg=(ImageView)findViewById(R.id.profile_header_img);
        changePasswordheaderImg=(ImageView)findViewById(R.id.chng_pass_header_img);

        RelativeLayout.LayoutParams param2 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
        param2.setMargins(0,(int) (0.1 * height), 0,0);
        myProfileImg.setLayoutParams(param2);
        changePasswordheaderImg.setLayoutParams(param2);

        nameTv=(CenturyGothicTextView)findViewById(R.id.user_name);
        contactNoTv=(CenturyGothicTextView)findViewById(R.id.contactno_tv);
        emailTv=(CenturyGothicTextView)findViewById(R.id.email_tv);
        birthdayTv=(CenturyGothicTextView)findViewById(R.id.birhday_tv);
        genderTv=(CenturyGothicTextView)findViewById(R.id.gender_tv);
        passwordTv=(CenturyGothicTextView)findViewById(R.id.pass_tv);

        userNameTv=(CentuaryGothicBoldTextView)findViewById(R.id.left_heading_tv);
        balanceTv=(CentuaryGothicBoldTextView)findViewById(R.id.right_heading_tv);

        oldPassEdt=(CenturyGothicEditText)findViewById(R.id.old_pass_edt);
        newPassEdt=(CenturyGothicEditText)findViewById(R.id.new_pass_edt);
        retypeNewPassEDt=(CenturyGothicEditText)findViewById(R.id.retype_pass_edt);

        addBirthdayImg=(ImageView)findViewById(R.id.add_birthday_img);
        changePasswordImg=(ImageView)findViewById(R.id.change_pass_img);
        savePasswordImg=(ImageView)findViewById(R.id.save_pass_img);
        cancelPasswordImg=(ImageView)findViewById(R.id.cancel_pass_img);
        bottomMoreImg=(ImageView)findViewById(R.id.more_img);
        bottomNewsImg=(ImageView)findViewById(R.id.news_img);
        bottomVoucherImg=(ImageView)findViewById(R.id.voucher_img);
        bottomWalletImg=(ImageView)findViewById(R.id.wallet_img);
        bottomStampImg=(ImageView)findViewById(R.id.stamp_img);

        nameTv.setText(sharedPref.getString("name","").trim());
        contactNoTv.setText(sharedPref.getString("contact","").trim());
        emailTv.setText(sharedPref.getString("email","").trim());
        birthdayTv.setText(sharedPref.getString("birthday","").trim());
        genderTv.setText(sharedPref.getString("gender","").trim());
        passwordTv.setText(sharedPref.getString("password","").trim());
        userNameTv.setText("Hi, "+sharedPref.getString("name","").trim());
        balanceTv.setText("CURRENT BALANCE: RM 100.00");

        spinner.getLayoutParams().height=bottomLay.getHeight();

        bottomMoreImg.setOnClickListener(this);
        bottomNewsImg.setOnClickListener(this);
        bottomVoucherImg.setOnClickListener(this);
        bottomWalletImg.setOnClickListener(this);
        bottomStampImg.setOnClickListener(this);
        addBirthdayImg.setOnClickListener(this);
        changePasswordImg.setOnClickListener(this);
        savePasswordImg.setOnClickListener(this);
        cancelPasswordImg.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.more_img:
                spinner.performClick();
                break;

            case R.id.news_img:
                Intent news=new Intent(MyProfileActivity.this, NewsAndPromoActivity.class);
                startActivity(news);
                overridePendingTransition(0,0);
                MyProfileActivity.this.finish();
                break;

            case R.id.voucher_img:
                Intent voucher=new Intent(MyProfileActivity.this, VoucherActivity.class);
                startActivity(voucher);
                overridePendingTransition(0,0);
                MyProfileActivity.this.finish();
                break;

            case R.id.wallet_img:
                Intent wallet=new Intent(MyProfileActivity.this, WalletActivity.class);
                startActivity(wallet);
                overridePendingTransition(0,0);
                MyProfileActivity.this.finish();
                break;

            case R.id.stamp_img:
                Intent stamp=new Intent(MyProfileActivity.this, StampsActivity.class);
                startActivity(stamp);
                overridePendingTransition(0,0);
                MyProfileActivity.this.finish();
                break;

            case R.id.add_birthday_img:
                DateTime now = DateTime.now();
                MonthAdapter.CalendarDay maxDate = new MonthAdapter.CalendarDay(now.getYear(), now.getMonthOfYear()-1, now.getDayOfMonth());
                CalendarDatePickerDialogFragment cdp = new CalendarDatePickerDialogFragment()
                        .setDoneText("Done")
                        .setCancelText("Cancel")
                        .setDateRange(null,maxDate)
                        .setThemeCustom(R.style.MyCustomBetterPickersDialogs)
                        .setOnDateSetListener(MyProfileActivity.this);

                cdp.show(getSupportFragmentManager(), Constants.FRAG_TAG_DATE_PICKER);
                break;

            case R.id.change_pass_img:
                profileLayout.setVisibility(View.GONE);
                changePasswordLayout.setVisibility(View.VISIBLE);
                oldPassEdt.setText("");
                newPassEdt.setText("");
                retypeNewPassEDt.setText("");
                break;

            case R.id.save_pass_img:
                userID = sharedPref.getString("user_id", "");
                loggedPassword = sharedPref.getString("password", "");
                Log.e("user and pass",userID +"  "+loggedPassword);
                checkPasswordChange();
                break;

            case R.id.cancel_pass_img:
                profileLayout.setVisibility(View.VISIBLE);
                changePasswordLayout.setVisibility(View.GONE);
                break;
        }
    }

    public void checkPasswordChange(){

        if(TextUtils.isEmpty(oldPassEdt.getText().toString().trim())){
            oldPassEdt.setError("Enter Old Password");
        } else if(!oldPassEdt.getText().toString().trim().equals(loggedPassword)){
            oldPassEdt.setError("Old Password is not correct");
        }
        else if(TextUtils.isEmpty(newPassEdt.getText().toString().trim())){
            newPassEdt.setError("Enter New Password");
        }
        else if(newPassEdt.getText().toString().trim().length()<6){
            newPassEdt.setError("Minimum Password length should be 6 characters");
        }
        else if(TextUtils.isEmpty(retypeNewPassEDt.getText().toString().trim())){
            retypeNewPassEDt.setError("Retype New Password");
        }
        else if(!newPassEdt.getText().toString().trim().equals(retypeNewPassEDt.getText().toString().trim())){
            retypeNewPassEDt.setError("New Password and Retype Password must be same");
        }
        else{
            if(Constants.isNetworkAvailable(MyProfileActivity.this)){
                new ChangePassTask(userID,""+newPassEdt.getText().toString().trim()).execute();
            }else{
                Constants.alertDialog(MyProfileActivity.this,"Internet connection is down.");
            }
        }
    }

    @Override
    public void onDateSet(CalendarDatePickerDialogFragment dialog, int year, int monthOfYear, int dayOfMonth) {

        if((monthOfYear+1)==1){
            birthdayTv.setText("Jan"+" "+dayOfMonth+", "+year);
        }else if((monthOfYear+1)==2){
            birthdayTv.setText("Feb"+" "+dayOfMonth+", "+year);
        }else if((monthOfYear+1)==3){
            birthdayTv.setText("Mar"+" "+dayOfMonth+", "+year);
        }else if((monthOfYear+1)==4){
            birthdayTv.setText("Apr"+" "+dayOfMonth+", "+year);
        }else if((monthOfYear+1)==5){
            birthdayTv.setText("May"+" "+dayOfMonth+", "+year);
        }else if((monthOfYear+1)==6){
            birthdayTv.setText("Jun"+" "+dayOfMonth+", "+year);
        }else if((monthOfYear+1)==7){
            birthdayTv.setText("Jul"+" "+dayOfMonth+", "+year);
        }else if((monthOfYear+1)==8){
            birthdayTv.setText("Aug"+" "+dayOfMonth+", "+year);
        }else if((monthOfYear+1)==9){
            birthdayTv.setText("Sep"+" "+dayOfMonth+", "+year);
        }else if((monthOfYear+1)==10){
            birthdayTv.setText("Oct"+" "+dayOfMonth+", "+year);
        }else if((monthOfYear+1)==11){
            birthdayTv.setText("Nov"+" "+dayOfMonth+", "+year);
        }else if((monthOfYear+1)==12){
            birthdayTv.setText("Dec"+" "+dayOfMonth+", "+year);
        }
    }

    @Override
    public void onBackPressed() {
        if(changePasswordLayout.getVisibility()==View.VISIBLE){
            changePasswordLayout.setVisibility(View.GONE);
            profileLayout.setVisibility(View.VISIBLE);
        }
        else{
            MyProfileActivity.this.finish();
        }
    }

    public class ChangePassTask extends AsyncTask<String, Integer, String> {

        String message, success,error;
        JSONObject jsonObject = null;
        JSONMethods jsonMethod;
        String userid,newPass;
        ProgressDialog dialog;

        public ChangePassTask(String userid,String newPass) {
            this.userid = userid;
            this.newPass = newPass;
        }

        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(MyProfileActivity.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setMessage("Loading...");
            dialog.setCancelable(false);
            dialog.show();
        }

        @Override
        protected String doInBackground(String... arg0) {
            jsonMethod = new JSONMethods();
            try {
                String response = jsonMethod.changePass(userid,newPass);
                if (Constants.checkString(response)) {
                    jsonObject = new JSONObject(response);
                    if (jsonObject instanceof JSONObject) {
                        System.out.println("login response    " + response);

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
                editor.putString("password",""+newPass);
                editor.commit();
                Toast.makeText(MyProfileActivity.this,"Password Change Successfully",Toast.LENGTH_SHORT).show();
                profileLayout.setVisibility(View.VISIBLE);
                changePasswordLayout.setVisibility(View.GONE);
                passwordTv.setText(sharedPref.getString("password","").trim());
            }else{
                Constants.alertDialog(MyProfileActivity.this,""+message);
            }
        }
    }
}
