package com.komugi.more;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.komugi.R;
import com.komugi.newsAndPromo.NewsAndPromoActivity;
import com.komugi.stamps.StampsActivity;
import com.komugi.textviewtypeface.CentuaryGothicBoldTextView;
import com.komugi.utils.Constants;
import com.komugi.vouchers.VoucherActivity;
import com.komugi.wallet.WalletActivity;

/**
 * Created by iapp on 21/12/16.
 */
public class SocialMediaActivity  extends Activity implements View.OnClickListener {

    int height,width;
    LinearLayout header,bottomLay;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    ImageView aboutHeaderImg,bottomMoreImg,bottomNewsImg,bottomVoucherImg,bottomWalletImg,bottomStampImg;;
    MoreBottomAdapter spinnerAdapter;
    Spinner spinner;
    CentuaryGothicBoldTextView userNameTv,balanceTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.socila_media_activity);
        initializeView();
    }

    public void initializeView(){

        sharedPref = getSharedPreferences(Constants.PREFS_NAME, 0);
        editor = sharedPref.edit();

        spinner= (Spinner) findViewById(R.id.spinner_location);

        spinnerAdapter=new MoreBottomAdapter(SocialMediaActivity.this,SocialMediaActivity.this,Constants.spinnerList);

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

        aboutHeaderImg=(ImageView)findViewById(R.id.social_header_img);
        bottomMoreImg=(ImageView)findViewById(R.id.more_img);
        bottomNewsImg=(ImageView)findViewById(R.id.news_img);
        bottomVoucherImg=(ImageView)findViewById(R.id.voucher_img);
        bottomWalletImg=(ImageView)findViewById(R.id.wallet_img);
        bottomStampImg=(ImageView)findViewById(R.id.stamp_img);

        RelativeLayout.LayoutParams param2 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
        param2.setMargins(0,(int) (0.1 * height), 0,0);
        aboutHeaderImg.setLayoutParams(param2);

        spinner.getLayoutParams().height=bottomLay.getHeight();

        bottomMoreImg.setOnClickListener(this);
        bottomNewsImg.setOnClickListener(this);
        bottomVoucherImg.setOnClickListener(this);
        bottomWalletImg.setOnClickListener(this);
        bottomStampImg.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.more_img:
                spinner.performClick();
                break;

            case R.id.news_img:
                Intent news=new Intent(SocialMediaActivity.this, NewsAndPromoActivity.class);
                startActivity(news);
                overridePendingTransition(0,0);
                SocialMediaActivity.this.finish();
                break;

            case R.id.voucher_img:
                Intent voucher=new Intent(SocialMediaActivity.this, VoucherActivity.class);
                startActivity(voucher);
                overridePendingTransition(0,0);
                SocialMediaActivity.this.finish();
                break;

            case R.id.wallet_img:
                Intent wallet=new Intent(SocialMediaActivity.this, WalletActivity.class);
                startActivity(wallet);
                overridePendingTransition(0,0);
                SocialMediaActivity.this.finish();
                break;

            case R.id.stamp_img:
                Intent stamp=new Intent(SocialMediaActivity.this, StampsActivity.class);
                startActivity(stamp);
                overridePendingTransition(0,0);
                SocialMediaActivity.this.finish();
                break;
        }
    }
}
