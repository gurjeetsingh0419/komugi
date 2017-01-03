package com.komugi.newsAndPromo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.komugi.R;

import com.komugi.more.LocationActivity;
import com.komugi.more.MoreBottomAdapter;
import com.komugi.stamps.StampsActivity;
import com.komugi.textviewtypeface.CentuaryGothicBoldTextView;
import com.komugi.utils.Constants;
import com.komugi.vouchers.VoucherActivity;
import com.komugi.wallet.WalletActivity;

import java.util.ArrayList;

/**
 * Created by user on 12/21/2016.
 */

public class NewsAndPromoActivity extends Activity implements View.OnClickListener,ViewPager.OnPageChangeListener{

    ViewPager viewPager;
    ArrayList<Integer> images=new ArrayList<>();
    int height,width;
    LinearLayout bottomLay;
    LinearLayout header;
    ImageView newsTab,voucherTab,walletTab,stampTab,moreTab;
    private int dotsCount;
    private ImageView[] dots;
    private LinearLayout pager_indicator;
    CustomPagerAdapter mAdapter;
    MoreBottomAdapter spinnerAdapter;
    Spinner spinner;
    CentuaryGothicBoldTextView userNameTv,balanceTv;;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_promo);
        initialise();
    }

    public void initialise(){
        sharedPref = getSharedPreferences(Constants.PREFS_NAME, 0);
        editor = sharedPref.edit();

        spinner= (Spinner) findViewById(R.id.spinner_location);

        spinnerAdapter=new MoreBottomAdapter(NewsAndPromoActivity.this,NewsAndPromoActivity.this,Constants.spinnerList);

        spinner.setAdapter(spinnerAdapter);
        spinner.setPrompt("");

        Display display = getWindowManager().getDefaultDisplay();
        width = display.getWidth();
        height = display.getHeight();

        header=(LinearLayout)findViewById(R.id.header_lay);
        bottomLay=(LinearLayout)findViewById(R.id.bottom_lay);
        pager_indicator=(LinearLayout)findViewById(R.id.indicator_lay);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        //bottom tabs
        newsTab = (ImageView) findViewById(R.id.news_img);
        voucherTab = (ImageView) findViewById(R.id.voucher_img);
        walletTab = (ImageView) findViewById(R.id.wallet_img);
        stampTab = (ImageView) findViewById(R.id.stamp_img);
        moreTab = (ImageView) findViewById(R.id.more_img);

        userNameTv=(CentuaryGothicBoldTextView)findViewById(R.id.left_heading_tv);
        balanceTv=(CentuaryGothicBoldTextView)findViewById(R.id.right_heading_tv);
        userNameTv.setText("Hi, "+sharedPref.getString("name","").trim());
        balanceTv.setText("CURRENT BALANCE: RM 100.00");

        images.add(R.drawable.apps_background);
        images.add(R.drawable.apps_background);
        images.add(R.drawable.apps_background);
        images.add(R.drawable.apps_background);
        images.add(R.drawable.apps_background);

        header.getLayoutParams().height=(int)(0.15*height);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, (int) (0.1 * height), 0, 0);
        viewPager.setLayoutParams(params);
        viewPager.getLayoutParams().width = width;

        spinner.getLayoutParams().height=bottomLay.getHeight();

        newsTab.setOnClickListener(this);
        voucherTab.setOnClickListener(this);
        walletTab.setOnClickListener(this);
        stampTab.setOnClickListener(this);
        moreTab.setOnClickListener(this);

        mAdapter=new CustomPagerAdapter(this);
        viewPager.setAdapter(mAdapter);
        viewPager.setCurrentItem(0);
        viewPager.setOnPageChangeListener(this);
        setUiPageViewController();
    }
    private void setUiPageViewController() {

        dotsCount = mAdapter.getCount();
        dots = new ImageView[dotsCount];

        for (int i = 0; i < dotsCount; i++) {
            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselecteditem_dot));

           LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(4, 0, 4, 0);

            pager_indicator.addView(dots[i], params);
            }

        dots[0].setImageDrawable(getResources().getDrawable(R.drawable.selecteditem_dot));
        }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.news_img:

                break;
            case R.id.voucher_img:
                Intent stampIntent=new Intent(NewsAndPromoActivity.this,VoucherActivity.class);
                startActivity(stampIntent);
                overridePendingTransition(0,0);
                finish();
                break;
            case R.id.wallet_img:
                Intent walletIntent=new Intent(NewsAndPromoActivity.this,WalletActivity.class);
                startActivity(walletIntent);
                overridePendingTransition(0,0);
                finish();
                break;
            case R.id.stamp_img:
                Intent stapIntent=new Intent(NewsAndPromoActivity.this,StampsActivity.class);
                startActivity(stapIntent);
                overridePendingTransition(0,0);
                finish();
                break;
            case R.id.more_img:
                spinner.performClick();
                break;
        }
    }
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        for (int i = 0; i < dotsCount; i++) {
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselecteditem_dot));
        }

        dots[position].setImageDrawable(getResources().getDrawable(R.drawable.selecteditem_dot));


    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public class CustomPagerAdapter extends PagerAdapter {

        private Context mContext;

        public CustomPagerAdapter(Context context) {
            mContext = context;
        }

        @Override
        public Object instantiateItem(ViewGroup collection, int position) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.single_view_news, collection, false);
            ImageView imageView=(ImageView)layout.findViewById(R.id.promo_img);
            imageView.setImageResource(images.get(position));
            collection.addView(layout);
            return layout;
        }

        @Override
        public void destroyItem(ViewGroup collection, int position, Object view) {
            collection.removeView((View) view);
        }

        @Override
        public int getCount() {
            return images.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mContext.getString(images.get(position));
        }

    }
}
