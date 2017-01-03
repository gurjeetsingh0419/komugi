package com.komugi.more;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.komugi.R;
import com.komugi.newsAndPromo.NewsAndPromoActivity;
import com.komugi.stamps.StampsActivity;
import com.komugi.textviewtypeface.CentuaryGothicBoldTextView;
import com.komugi.utils.Constants;
import com.komugi.vouchers.VoucherActivity;
import com.komugi.wallet.WalletActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by iapp on 21/12/16.
 */

public class LocationActivity extends Activity implements AdapterView.OnItemSelectedListener,View.OnClickListener {

    Spinner spinnerLocation;
    int height,width;
    LinearLayout bottomLay;
    LinearLayout header;
    ImageView locationIcon,nearByLocationIcon,bottomMoreImg,bottomNewsImg,bottomVoucherImg,bottomWalletImg,bottomStampImg;
    ListView locationListView;
    int getHeight,getWidth;
    LocationAdapter adapter;
    ArrayList<LocationModel> locationModelArrayList=new ArrayList<>();
    MoreBottomAdapter spinnerAdapter;
    Spinner spinner;
    CentuaryGothicBoldTextView userNameTv,balanceTv;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        initialise();
    }
    public void initialise(){

        sharedPref = getSharedPreferences(Constants.PREFS_NAME, 0);
        editor = sharedPref.edit();

        Display display = getWindowManager().getDefaultDisplay();
        width = display.getWidth();
        height = display.getHeight();

        spinner= (Spinner) findViewById(R.id.spinner_location);

        spinnerAdapter=new MoreBottomAdapter(LocationActivity.this,LocationActivity.this, Constants.spinnerList);

        spinner.setAdapter(spinnerAdapter);
        spinner.setPrompt("");

        userNameTv=(CentuaryGothicBoldTextView)findViewById(R.id.left_heading_tv);
        balanceTv=(CentuaryGothicBoldTextView)findViewById(R.id.right_heading_tv);
        userNameTv.setText("Hi, "+sharedPref.getString("name","").trim());
        balanceTv.setText("CURRENT BALANCE: RM 100.00");

        header = (LinearLayout) findViewById(R.id.header_lay);
        bottomLay = (LinearLayout) findViewById(R.id.bottom_lay);
        locationIcon = (ImageView) findViewById(R.id.location_icon);
        nearByLocationIcon = (ImageView) findViewById(R.id.nearby_location_icon);
        locationListView = (ListView) findViewById(R.id.location_list);
        spinnerLocation = (Spinner) findViewById(R.id.spinner_location_drop);

        bottomMoreImg=(ImageView)findViewById(R.id.more_img);
        bottomNewsImg=(ImageView)findViewById(R.id.news_img);
        bottomVoucherImg=(ImageView)findViewById(R.id.voucher_img);
        bottomWalletImg=(ImageView)findViewById(R.id.wallet_img);
        bottomStampImg=(ImageView)findViewById(R.id.stamp_img);

        ViewTreeObserver observer = bottomLay.getViewTreeObserver();

        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                // TODO Auto-generated method stub
                getHeight = bottomLay.getHeight();
                getWidth = bottomLay.getWidth();
                Log.e("CheckDim",height+"  "+getHeight+"   "+getWidth);
                FrameLayout.LayoutParams param2 = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
                param2.setMargins(0, 0, 0,(int)(getHeight * 0.6));
                nearByLocationIcon.setLayoutParams(param2);
                nearByLocationIcon.getLayoutParams().width = width;
                bottomLay.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });


        header.getLayoutParams().height = (int) (0.15 * height);

        RelativeLayout.LayoutParams param1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        param1.setMargins(0, (int) (0.1 * height), 0, 0);
        locationIcon.setLayoutParams(param1);
        locationIcon.getLayoutParams().width = width;

        spinnerLocation.setOnItemSelectedListener(this);


        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("location1");
        categories.add("location2");
        categories.add("location3");
        categories.add("location4");
        categories.add("location5");
        categories.add("location6");
        // Creating adapter for spinnerLocation
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinnerLocation
        spinnerLocation.setAdapter(dataAdapter);

        adapter=new LocationAdapter(LocationActivity.this,locationModelArrayList);
        locationListView.setAdapter(adapter);

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
                Intent news=new Intent(LocationActivity.this, NewsAndPromoActivity.class);
                startActivity(news);
                overridePendingTransition(0,0);
                LocationActivity.this.finish();
                break;

            case R.id.voucher_img:
                Intent voucher=new Intent(LocationActivity.this, VoucherActivity.class);
                startActivity(voucher);
                overridePendingTransition(0,0);
                LocationActivity.this.finish();
                break;

            case R.id.wallet_img:
                Intent wallet=new Intent(LocationActivity.this, WalletActivity.class);
                startActivity(wallet);
                overridePendingTransition(0,0);
                LocationActivity.this.finish();
                break;

            case R.id.stamp_img:
                Intent stamp=new Intent(LocationActivity.this, StampsActivity.class);
                startActivity(stamp);
                overridePendingTransition(0,0);
                LocationActivity.this.finish();
                break;
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
