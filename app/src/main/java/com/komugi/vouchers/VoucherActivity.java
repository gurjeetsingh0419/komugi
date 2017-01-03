package com.komugi.vouchers;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.komugi.R;
import com.komugi.more.MoreBottomAdapter;
import com.komugi.newsAndPromo.NewsAndPromoActivity;
import com.komugi.stamps.StampsActivity;
import com.komugi.textviewtypeface.CentuaryGothicBoldTextView;
import com.komugi.utils.Constants;
import com.komugi.vouchers.adapter.VoucherAdapter;
import com.komugi.wallet.WalletActivity;

import java.util.ArrayList;

/**
 * Created by iapp on 20/12/16.
 */

public class VoucherActivity extends Activity  implements View.OnClickListener{

    int height,width;
    LinearLayout bottomLay;
    LinearLayout header;
    ImageView voucherIcon,myVoucherBottomIcon,bottomMoreImg,bottomNewsImg,bottomVoucherImg,bottomWalletImg,bottomStampImg;;
    MoreBottomAdapter spinnerAdapter;
    Spinner spinner;
    CentuaryGothicBoldTextView userNameTv,balanceTv;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    ListView voucherListView;
    ArrayList<Integer> voucherArray=new ArrayList<>();
    VoucherAdapter adapter;
    int getHeight,getWidth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vouchers);
        initialise();
    }

    public void initialise() {

        sharedPref = getSharedPreferences(Constants.PREFS_NAME, 0);
        editor = sharedPref.edit();

        spinner= (Spinner) findViewById(R.id.spinner_location);

        spinnerAdapter=new MoreBottomAdapter(VoucherActivity.this,VoucherActivity.this,Constants.spinnerList);

        spinner.setAdapter(spinnerAdapter);
        spinner.setPrompt("");

        userNameTv=(CentuaryGothicBoldTextView)findViewById(R.id.left_heading_tv);
        balanceTv=(CentuaryGothicBoldTextView)findViewById(R.id.right_heading_tv);
        userNameTv.setText("Hi, "+sharedPref.getString("name","").trim());
        balanceTv.setText("CURRENT BALANCE: RM 100.00");

        Display display = getWindowManager().getDefaultDisplay();
        width = display.getWidth();
        height = display.getHeight();

        header = (LinearLayout) findViewById(R.id.header_lay);
        bottomLay = (LinearLayout) findViewById(R.id.bottom_lay);
        voucherIcon = (ImageView) findViewById(R.id.voucher_icon);
        myVoucherBottomIcon = (ImageView) findViewById(R.id.my_voucher_icon);
        voucherListView = (ListView) findViewById(R.id.voucher_list);

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
                myVoucherBottomIcon.setLayoutParams(param2);
                myVoucherBottomIcon.getLayoutParams().width = width;
                bottomLay.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });


        header.getLayoutParams().height = (int) (0.15 * height);

        RelativeLayout.LayoutParams param1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        param1.setMargins(0, (int) (0.1 * height), 0, 0);
        voucherIcon.setLayoutParams(param1);
        voucherIcon.getLayoutParams().width = width;


        voucherArray.add(R.drawable.voucher_1);
        voucherArray.add(R.drawable.voucher_2);
        voucherArray.add(R.drawable.voucher_1);
        voucherArray.add(R.drawable.voucher_2);


        adapter = new VoucherAdapter(VoucherActivity.this, voucherArray);
        voucherListView.setAdapter(adapter);

        voucherListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showvoucherdialog().show();
            }
        });

        spinner.getLayoutParams().height=bottomLay.getHeight();

        bottomMoreImg.setOnClickListener(this);
        bottomNewsImg.setOnClickListener(this);
        bottomVoucherImg.setOnClickListener(this);
        bottomWalletImg.setOnClickListener(this);
        bottomStampImg.setOnClickListener(this);

    }


    public Dialog showvoucherdialog()
    {
        final Dialog dialog = new Dialog(VoucherActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.voucher_dialog_layout);
        dialog.setCancelable(false);

        TextView voucherText=(TextView)dialog.findViewById(R.id.voucher_text);
        ImageView voucherImage=(ImageView) dialog.findViewById(R.id.voucher_img);
        ImageView cancel=(ImageView) dialog.findViewById(R.id.cancel);
        ImageView confirm=(ImageView) dialog.findViewById(R.id.confirm);

        voucherImage.getLayoutParams().height = (int) (0.22 * height);
        voucherImage.getLayoutParams().width = (int) (0.8 * width);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        return dialog;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.more_img:
                spinner.performClick();
                break;

            case R.id.news_img:
                Intent news=new Intent(VoucherActivity.this, NewsAndPromoActivity.class);
                startActivity(news);
                overridePendingTransition(0,0);
                VoucherActivity.this.finish();
                break;

            case R.id.voucher_img:
               /* Intent voucher=new Intent(VoucherActivity.this, VoucherActivity.class);
                startActivity(voucher);
                overridePendingTransition(0,0);
                VoucherActivity.this.finish();*/
                break;

            case R.id.wallet_img:
                Intent wallet=new Intent(VoucherActivity.this, WalletActivity.class);
                startActivity(wallet);
                overridePendingTransition(0,0);
                VoucherActivity.this.finish();
                break;

            case R.id.stamp_img:
                Intent stamp=new Intent(VoucherActivity.this, StampsActivity.class);
                startActivity(stamp);
                overridePendingTransition(0,0);
                VoucherActivity.this.finish();
                break;
        }
    }

}
