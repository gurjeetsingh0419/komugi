package com.komugi.wallet;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import com.komugi.R;
import com.komugi.more.MoreBottomAdapter;
import com.komugi.newsAndPromo.NewsAndPromoActivity;
import com.komugi.stamps.StampsActivity;
import com.komugi.textviewtypeface.CentuaryGothicBoldTextView;
import com.komugi.textviewtypeface.CenturyGothicTextView;
import com.komugi.utils.Constants;
import com.komugi.vouchers.VoucherActivity;
import com.komugi.wallet.TransactionModel.TransactionHistoryModelClass;
import com.komugi.wallet.adapter.TransactionHistoryAdapter;

import java.util.ArrayList;

public class WalletActivity extends Activity implements View.OnClickListener{

    ImageView payButton,reloadButton,transactionHistoryButton,walletIcon,reloadBack,cashBack,molPayBack,transactionBack;
    int height,width;
    LinearLayout bottomLay;
    LinearLayout header;
    ScrollView walletHomeScroll;
    RelativeLayout walletHomeLay,reloadLay,transactionHistoryLay,cashLay,molPayLay;
    ImageView cashBtn,molPayBtn,cashRm20Btn,cashRm50Btn,cashRm100Btn,molpayRm20Btn,molpayRm50Btn,molpayRm100Btn,bottomMoreImg,bottomNewsImg,bottomVoucherImg,bottomWalletImg,bottomStampImg;;
    MoreBottomAdapter spinnerAdapter;
    Spinner spinner;
    CentuaryGothicBoldTextView userNameTv,balanceTv;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    CenturyGothicTextView reloadText,molPayText,transactionText,cashText;
    ListView transactionListView;
    TransactionHistoryAdapter transactionHistoryAdapter;
    ArrayList<TransactionHistoryModelClass> transArryList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);
        initialiseData();

    }

    public void initialiseData(){
        Display display = getWindowManager().getDefaultDisplay();
        width = display.getWidth();
        height = display.getHeight();

        sharedPref = getSharedPreferences(Constants.PREFS_NAME, 0);
        editor = sharedPref.edit();

        spinner= (Spinner) findViewById(R.id.spinner_location);

        spinnerAdapter=new MoreBottomAdapter(WalletActivity.this,WalletActivity.this,Constants.spinnerList);

        spinner.setAdapter(spinnerAdapter);
        spinner.setPrompt("");

        userNameTv=(CentuaryGothicBoldTextView)findViewById(R.id.left_heading_tv);
        balanceTv=(CentuaryGothicBoldTextView)findViewById(R.id.right_heading_tv);
        userNameTv.setText("Hi, "+sharedPref.getString("name","").trim());
        balanceTv.setText("CURRENT BALANCE: RM 100.00");

        header=(LinearLayout)findViewById(R.id.header_lay);
        bottomLay=(LinearLayout)findViewById(R.id.bottom_lay);

        payButton=(ImageView)findViewById(R.id.pay_button);
        reloadButton=(ImageView)findViewById(R.id.reload_button);
        transactionHistoryButton=(ImageView)findViewById(R.id.transaction_history_btn);
        walletIcon=(ImageView)findViewById(R.id.wallet_icon);
        reloadBack=(ImageView)findViewById(R.id.reload_back_btn);
        cashBack=(ImageView)findViewById(R.id.cash_back_btn);
        molPayBack=(ImageView)findViewById(R.id.molpay_back_btn);
        cashBtn=(ImageView)findViewById(R.id.cash_btn);
        molPayBtn=(ImageView)findViewById(R.id.molpay_btn);
        cashRm20Btn=(ImageView)findViewById(R.id.cash_rm_20);
        cashRm50Btn=(ImageView)findViewById(R.id.cash_rm_50);
        cashRm100Btn=(ImageView)findViewById(R.id.cash_rm_100);
        molpayRm20Btn=(ImageView)findViewById(R.id.molpay_rm_20);
        molpayRm50Btn=(ImageView)findViewById(R.id.molpay_rm_50);
        molpayRm100Btn=(ImageView)findViewById(R.id.molpay_rm_100);
        transactionBack=(ImageView) findViewById(R.id.transaction_back_btn);
        bottomMoreImg=(ImageView)findViewById(R.id.more_img);
        bottomNewsImg=(ImageView)findViewById(R.id.news_img);
        bottomVoucherImg=(ImageView)findViewById(R.id.voucher_img);
        bottomWalletImg=(ImageView)findViewById(R.id.wallet_img);
        bottomStampImg=(ImageView)findViewById(R.id.stamp_img);

        reloadText=(CenturyGothicTextView) findViewById(R.id.reload_text);
        molPayText=(CenturyGothicTextView) findViewById(R.id.molpay_text);
        transactionText=(CenturyGothicTextView) findViewById(R.id.transaction_text);
        cashText=(CenturyGothicTextView) findViewById(R.id.cash_text);

        transactionListView=(ListView) findViewById(R.id.transaction_list);

        walletHomeScroll=(ScrollView) findViewById(R.id.scroll);
        walletHomeLay=(RelativeLayout) findViewById(R.id.wallet_home_lay);
        reloadLay=(RelativeLayout) findViewById(R.id.reload_layout);
        cashLay=(RelativeLayout) findViewById(R.id.cash_layout);
        molPayLay=(RelativeLayout) findViewById(R.id.molpay_layout);
        transactionHistoryLay=(RelativeLayout) findViewById(R.id.transaction_layout);


        header.getLayoutParams().height=(int)(0.15*height);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, (int) (0.1 * height), 0, 0);
        walletIcon.setLayoutParams(params);
        walletIcon.getLayoutParams().width = width;

        spinner.getLayoutParams().height=bottomLay.getHeight();

        reloadLay.setVisibility(View.GONE);
        walletHomeLay.setVisibility(View.VISIBLE);
        walletHomeScroll.setVisibility(View.VISIBLE);
        cashLay.setVisibility(View.GONE);
        molPayLay.setVisibility(View.GONE);
        transactionHistoryLay.setVisibility(View.GONE);


        payButton.setOnClickListener(this);
        reloadButton.setOnClickListener(this);
        transactionHistoryButton.setOnClickListener(this);
        reloadBack.setOnClickListener(this);
        cashBack.setOnClickListener(this);
        molPayBack.setOnClickListener(this);
        cashBtn.setOnClickListener(this);
        molPayBtn.setOnClickListener(this);
        transactionBack.setOnClickListener(this);
        bottomMoreImg.setOnClickListener(this);
        bottomNewsImg.setOnClickListener(this);
        bottomVoucherImg.setOnClickListener(this);
        bottomWalletImg.setOnClickListener(this);
        bottomStampImg.setOnClickListener(this);

    }



    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.pay_button:

                break;
            case R.id.reload_button:
                reloadLay.setVisibility(View.VISIBLE);
                walletHomeLay.setVisibility(View.GONE);
                walletHomeScroll.setVisibility(View.GONE);
                cashLay.setVisibility(View.GONE);
                molPayLay.setVisibility(View.GONE);
                transactionHistoryLay.setVisibility(View.GONE);
                break;
            case R.id.transaction_history_btn:
                reloadLay.setVisibility(View.GONE);
                walletHomeLay.setVisibility(View.GONE);
                walletHomeScroll.setVisibility(View.GONE);
                cashLay.setVisibility(View.GONE);
                molPayLay.setVisibility(View.GONE);
                transactionHistoryLay.setVisibility(View.VISIBLE);

                transactionHistoryAdapter=new TransactionHistoryAdapter(WalletActivity.this,transArryList);
                transactionListView.setAdapter(transactionHistoryAdapter);
                break;
            case R.id.cash_btn:
                reloadLay.setVisibility(View.GONE);
                walletHomeLay.setVisibility(View.GONE);
                walletHomeScroll.setVisibility(View.GONE);
                cashLay.setVisibility(View.VISIBLE);
                molPayLay.setVisibility(View.GONE);
                transactionHistoryLay.setVisibility(View.GONE);
                break;
            case R.id.molpay_btn:
                reloadLay.setVisibility(View.GONE);
                walletHomeLay.setVisibility(View.GONE);
                walletHomeScroll.setVisibility(View.GONE);
                cashLay.setVisibility(View.GONE);
                molPayLay.setVisibility(View.VISIBLE);
                transactionHistoryLay.setVisibility(View.GONE);
                break;
            case R.id.cash_back_btn:
                reloadLay.setVisibility(View.VISIBLE);
                walletHomeLay.setVisibility(View.GONE);
                walletHomeScroll.setVisibility(View.GONE);
                cashLay.setVisibility(View.GONE);
                molPayLay.setVisibility(View.GONE);
                transactionHistoryLay.setVisibility(View.GONE);
                break;
            case R.id.molpay_back_btn:
                reloadLay.setVisibility(View.VISIBLE);
                walletHomeLay.setVisibility(View.GONE);
                walletHomeScroll.setVisibility(View.GONE);
                cashLay.setVisibility(View.GONE);
                molPayLay.setVisibility(View.GONE);
                transactionHistoryLay.setVisibility(View.GONE);
                break;
            case R.id.reload_back_btn:
                reloadLay.setVisibility(View.GONE);
                walletHomeLay.setVisibility(View.VISIBLE);
                walletHomeScroll.setVisibility(View.VISIBLE);
                cashLay.setVisibility(View.GONE);
                molPayLay.setVisibility(View.GONE);
                transactionHistoryLay.setVisibility(View.GONE);
                break;
            case R.id.transaction_back_btn:
                reloadLay.setVisibility(View.GONE);
                walletHomeLay.setVisibility(View.VISIBLE);
                walletHomeScroll.setVisibility(View.VISIBLE);
                cashLay.setVisibility(View.GONE);
                molPayLay.setVisibility(View.GONE);
                transactionHistoryLay.setVisibility(View.GONE);
                break;

            case R.id.more_img:
                spinner.performClick();
                break;

            case R.id.news_img:
                Intent news=new Intent(WalletActivity.this, NewsAndPromoActivity.class);
                startActivity(news);
                overridePendingTransition(0,0);
                WalletActivity.this.finish();
                break;

            case R.id.voucher_img:
                Intent voucher=new Intent(WalletActivity.this, VoucherActivity.class);
                startActivity(voucher);
                overridePendingTransition(0,0);
                WalletActivity.this.finish();
                break;

            case R.id.wallet_img:
             /*   Intent wallet=new Intent(WalletActivity.this, WalletActivity.class);
                startActivity(wallet);
                overridePendingTransition(0,0);
                WalletActivity.this.finish();*/
                break;

            case R.id.stamp_img:
                Intent stamp=new Intent(WalletActivity.this, StampsActivity.class);
                startActivity(stamp);
                overridePendingTransition(0,0);
                WalletActivity.this.finish();
                break;

        }
    }
}
