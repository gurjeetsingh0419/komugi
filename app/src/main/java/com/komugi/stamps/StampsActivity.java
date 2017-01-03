package com.komugi.stamps;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.Shape;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.komugi.R;
import com.komugi.more.MoreBottomAdapter;
import com.komugi.newsAndPromo.NewsAndPromoActivity;
import com.komugi.textviewtypeface.CentuaryGothicBoldTextView;
import com.komugi.utils.Constants;
import com.komugi.vouchers.VoucherActivity;
import com.komugi.wallet.WalletActivity;

import io.github.francoiscampbell.circlelayout.CircleLayout;


/**
 * Created by iapp on 21/12/16.
 */

public class StampsActivity extends Activity implements View.OnClickListener{
    int height,width;
    LinearLayout bottomLay;
    LinearLayout header;
    ImageView stampIcon;
    ImageView stamp1,stamp2,stamp3,stamp4,stamp5,stamp6,stamp7,stamp8,stamp9,stamp10,circleBg,
            bottomMoreImg,bottomNewsImg,bottomVoucherImg,bottomWalletImg,bottomStampImg,rightStamp,leftStamp,topStamp,bottomStamp;
    MoreBottomAdapter spinnerAdapter;
    Spinner spinner;
    CentuaryGothicBoldTextView userNameTv,balanceTv;;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    CircleLayout circularLay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stamps);
        initialise();

    }

   public void initialise(){

       sharedPref = getSharedPreferences(Constants.PREFS_NAME, 0);
       editor = sharedPref.edit();

       spinner= (Spinner) findViewById(R.id.spinner_location);

       spinnerAdapter=new MoreBottomAdapter(StampsActivity.this,StampsActivity.this,Constants.spinnerList);

       spinner.setAdapter(spinnerAdapter);
       spinner.setPrompt("");

       Display display = getWindowManager().getDefaultDisplay();
       width = display.getWidth();
       height = display.getHeight();

       header=(LinearLayout)findViewById(R.id.header_lay);
       bottomLay=(LinearLayout)findViewById(R.id.bottom_lay);
       stampIcon=(ImageView)findViewById(R.id.stamp_icon);

       circularLay=(CircleLayout)findViewById(R.id.circular_lay);

       userNameTv=(CentuaryGothicBoldTextView)findViewById(R.id.left_heading_tv);
       balanceTv=(CentuaryGothicBoldTextView)findViewById(R.id.right_heading_tv);
       userNameTv.setText("Hi, "+sharedPref.getString("name","").trim());
       balanceTv.setText("CURRENT BALANCE: RM 100.00");

       stamp1=(ImageView)findViewById(R.id.stamp1);
       stamp2=(ImageView)findViewById(R.id.stamp2);
       stamp3=(ImageView)findViewById(R.id.stamp3);
       stamp4=(ImageView)findViewById(R.id.stamp4);
       stamp5=(ImageView)findViewById(R.id.stamp5);
       stamp6=(ImageView)findViewById(R.id.stamp6);
       stamp7=(ImageView)findViewById(R.id.stamp7);
       stamp8=(ImageView)findViewById(R.id.stamp8);
       stamp9=(ImageView)findViewById(R.id.stamp9);
       stamp10=(ImageView)findViewById(R.id.stamp10);
       bottomMoreImg=(ImageView)findViewById(R.id.more_img);
       bottomNewsImg=(ImageView)findViewById(R.id.news_img);
       bottomVoucherImg=(ImageView)findViewById(R.id.voucher_img);
       bottomWalletImg=(ImageView)findViewById(R.id.wallet_img);
       bottomStampImg=(ImageView)findViewById(R.id.stamp_img);
       circleBg=(ImageView)findViewById(R.id.circle_bg);
       rightStamp=(ImageView)findViewById(R.id.stamp3);
       leftStamp=(ImageView)findViewById(R.id.stamp8);
       topStamp=(ImageView)findViewById(R.id.stamp10);
       bottomStamp=(ImageView)findViewById(R.id.stamp6);

       header.getLayoutParams().height=(int)(0.15*height);

       RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
       params.setMargins(0, (int) (0.1 * height), 0, 0);
       stampIcon.setLayoutParams(params);
       stampIcon.getLayoutParams().width = width;

       spinner.getLayoutParams().height=bottomLay.getHeight();

       bottomMoreImg.setOnClickListener(this);
       bottomNewsImg.setOnClickListener(this);
       bottomVoucherImg.setOnClickListener(this);
       bottomWalletImg.setOnClickListener(this);
       bottomStampImg.setOnClickListener(this);

       circleBg.setBackground(drawBackground());

   }

    public ShapeDrawable drawBackground(){

        Shape shape = new Shape() {

            @Override
            protected void onResize(float width, float height) {


            }
            @Override
            public void draw(Canvas canvas, Paint paint) {

                Paint p = new Paint();
                p.setColor(Color.BLACK);
                DashPathEffect dashPath = new DashPathEffect(new float[]{10,15}, (float)10.0);

                p.setPathEffect(dashPath);
                p.setStyle(Paint.Style.STROKE);
                p.setStrokeWidth(4);
                //  canvas.drawCircle(300, 300, 300, p);
                final RectF oval = new RectF();

                oval.set(leftStamp.getLeft()+(leftStamp.getWidth()/2),topStamp.getTop()+(topStamp.getHeight()/2),
                        rightStamp.getRight()-(rightStamp.getWidth()/2),
                        bottomStamp.getBottom()-(bottomStamp.getHeight()/2));
                canvas.drawArc(oval, -75, 310, false, p);

            }
        };
        ShapeDrawable d = new ShapeDrawable(shape);
        return d;
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.more_img:
                spinner.performClick();
                break;

            case R.id.news_img:
                Intent news=new Intent(StampsActivity.this, NewsAndPromoActivity.class);
                startActivity(news);
                overridePendingTransition(0,0);
                StampsActivity.this.finish();
                break;

            case R.id.voucher_img:
                Intent voucher=new Intent(StampsActivity.this, VoucherActivity.class);
                startActivity(voucher);
                overridePendingTransition(0,0);
                StampsActivity.this.finish();
                break;

            case R.id.wallet_img:
                Intent wallet=new Intent(StampsActivity.this, WalletActivity.class);
                startActivity(wallet);
                overridePendingTransition(0,0);
                StampsActivity.this.finish();
                break;

            case R.id.stamp_img:

                break;
        }
    }

}
