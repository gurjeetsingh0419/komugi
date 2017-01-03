package com.komugi.more;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.komugi.LoginRegisterActivity;
import com.komugi.R;
import com.komugi.newsAndPromo.NewsAndPromoActivity;
import com.komugi.stamps.StampsActivity;
import com.komugi.textviewtypeface.CentuaryGothicBoldTextView;
import com.komugi.utils.Constants;
import com.komugi.vouchers.VoucherActivity;
import com.komugi.wallet.WalletActivity;
import com.komugi.webservices.JSONMethods;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by iapp on 21/12/16.
 */
public class AboutActivity extends Activity implements View.OnClickListener {

    int height,width;
    LinearLayout header,bottomLay;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    ImageView aboutHeaderImg,bottomMoreImg,bottomNewsImg,bottomVoucherImg,bottomWalletImg,bottomStampImg;;
    MoreBottomAdapter spinnerAdapter;
    Spinner spinner;
    CentuaryGothicBoldTextView userNameTv,balanceTv;
    ImageView contactUs,termsUse,feedback,privacyPolicy;
    ProgressDialog dialog;
    String idData,title,description;
    TextView termsPrivacyText;
    LinearLayout aboutLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_activity);
        initializeView();
    }

    public void initializeView(){

        sharedPref = getSharedPreferences(Constants.PREFS_NAME, 0);
        editor = sharedPref.edit();

        spinner= (Spinner) findViewById(R.id.spinner_location);

        spinnerAdapter=new MoreBottomAdapter(AboutActivity.this,AboutActivity.this,Constants.spinnerList);

        spinner.setAdapter(spinnerAdapter);
        spinner.setPrompt("");

        Display display = getWindowManager().getDefaultDisplay();
        width = display.getWidth();
        height = display.getHeight();

        header=(LinearLayout)findViewById(R.id.header_lay);
        header.getLayoutParams().height=(int)(0.15*height);

        bottomLay=(LinearLayout)findViewById(R.id.bottom_lay);

        userNameTv=(CentuaryGothicBoldTextView)findViewById(R.id.left_heading_tv);
        balanceTv=(CentuaryGothicBoldTextView)findViewById(R.id.right_heading_tv);
        userNameTv.setText("Hi, "+sharedPref.getString("name","").trim());
        balanceTv.setText("CURRENT BALANCE: RM 100.00");

        aboutHeaderImg=(ImageView)findViewById(R.id.about_header_img);
        bottomMoreImg=(ImageView)findViewById(R.id.more_img);
        bottomNewsImg=(ImageView)findViewById(R.id.news_img);
        bottomVoucherImg=(ImageView)findViewById(R.id.voucher_img);
        bottomWalletImg=(ImageView)findViewById(R.id.wallet_img);
        bottomStampImg=(ImageView)findViewById(R.id.stamp_img);

        contactUs=(ImageView)findViewById(R.id.contact_us);
        feedback=(ImageView)findViewById(R.id.feedback);
        termsUse=(ImageView)findViewById(R.id.terms_use);
        privacyPolicy=(ImageView)findViewById(R.id.privacy_policy);


        aboutLayout=(LinearLayout)findViewById(R.id.about_lay);
        termsPrivacyText=(TextView) findViewById(R.id.terms_privacy_text);

        RelativeLayout.LayoutParams param2 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
        param2.setMargins(0,(int) (0.1 * height), 0,0);
        aboutHeaderImg.setLayoutParams(param2);

        spinner.getLayoutParams().height=bottomLay.getHeight();

        aboutLayout.setVisibility(View.VISIBLE);
        termsPrivacyText.setVisibility(View.GONE);

        bottomMoreImg.setOnClickListener(this);
        bottomNewsImg.setOnClickListener(this);
        bottomVoucherImg.setOnClickListener(this);
        bottomWalletImg.setOnClickListener(this);
        bottomStampImg.setOnClickListener(this);
        contactUs.setOnClickListener(this);
        feedback.setOnClickListener(this);
        termsUse.setOnClickListener(this);
        privacyPolicy.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.more_img:
                spinner.performClick();
                break;

            case R.id.news_img:
                Intent news=new Intent(AboutActivity.this, NewsAndPromoActivity.class);
                startActivity(news);
                overridePendingTransition(0,0);
                AboutActivity.this.finish();
                break;

            case R.id.voucher_img:
                Intent voucher=new Intent(AboutActivity.this, VoucherActivity.class);
                startActivity(voucher);
                overridePendingTransition(0,0);
                AboutActivity.this.finish();
                break;

            case R.id.wallet_img:
                Intent wallet=new Intent(AboutActivity.this, WalletActivity.class);
                startActivity(wallet);
                overridePendingTransition(0,0);
                AboutActivity.this.finish();
                break;

            case R.id.stamp_img:
                Intent stamp=new Intent(AboutActivity.this, StampsActivity.class);
                startActivity(stamp);
                overridePendingTransition(0,0);
                AboutActivity.this.finish();
                break;
            case R.id.terms_use:
                aboutHeaderImg.setImageResource(R.drawable.terms_of_use_header);
                if (Constants.isNetworkAvailable(AboutActivity.this)) {
                    new TermsAndPrivacyTask("1").execute();
                } else {
                    Constants.alertDialog(AboutActivity.this, "Internet connection is down.");
                }
                break;
            case R.id.privacy_policy:
                aboutHeaderImg.setImageResource(R.drawable.privacy_policy_header);
                if (Constants.isNetworkAvailable(AboutActivity.this)) {
                    new TermsAndPrivacyTask("2").execute();
                } else {
                    Constants.alertDialog(AboutActivity.this, "Internet connection is down.");
                }
                break;
        }
    }
    public class TermsAndPrivacyTask extends AsyncTask<String, Integer, String> {

        String message, success,error;
        JSONObject jsonObject = null;
        JSONMethods jsonMethod;
        String id;
        public TermsAndPrivacyTask(String id) {
            this.id = id;
        }

        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(AboutActivity.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setMessage("Loading...");
            dialog.setCancelable(false);
            dialog.show();
        }

        @Override
        protected String doInBackground(String... arg0) {
            jsonMethod = new JSONMethods();
            try {
                String response = jsonMethod.termsAndPrivacy(id);
                if (Constants.checkString(response)) {
                    jsonObject = new JSONObject(response);
                    if (jsonObject instanceof JSONObject) {
                        System.out.println("TermsAndPrivacyTask response    " + response);

                        error=jsonObject.optString("error");
                        JSONArray array=jsonObject.getJSONArray("PageDetails");
                        JSONObject jsonObject1=array.getJSONObject(0);
                        idData=jsonObject1.optString("id");
                        description=jsonObject1.optString("description");
                        title=jsonObject1.optString("title");

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
                 aboutLayout.setVisibility(View.GONE);
                 termsPrivacyText.setVisibility(View.VISIBLE);
                 termsPrivacyText.setText(""+description);

            }else{
                Constants.alertDialog(AboutActivity.this,""+message);
            }
        }
    }
}
