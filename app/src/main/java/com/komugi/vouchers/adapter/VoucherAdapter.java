package com.komugi.vouchers.adapter;

import android.app.Activity;
import android.content.Context;
import android.provider.SyncStateContract;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.komugi.R;
import com.komugi.utils.Constants;
import com.komugi.vouchers.models.VoucherModel;
import com.komugi.wallet.TransactionModel.TransactionHistoryModelClass;
import com.komugi.wallet.adapter.TransactionHistoryAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by iapp on 20/12/16.
 */

public class VoucherAdapter extends BaseAdapter {

    Context context;
    ArrayList<Integer> voucherList;


    public VoucherAdapter(Context context, ArrayList<Integer> voucherList) {
        this.context = context;
        this.voucherList=voucherList;
    }
    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public Object getItem(int position) {
        return voucherList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convert_view, ViewGroup parent) {
        ViewHolder holder;
        if (convert_view == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convert_view = inflater.inflate(R.layout.single_voucher_view, parent, false);
            holder = new ViewHolder();

            holder.voucherImage=(ImageView) convert_view.findViewById(R.id.voucher_img);

            convert_view.setTag(holder);
        } else {
            holder = (ViewHolder) convert_view.getTag();
        }
        Display display= Constants.getDeviceDimension(context);
        int height=display.getHeight();
        int width=display.getWidth();

        holder.voucherImage.getLayoutParams().height=(int)(0.22 * height);

        //String thumbImage=voucherList.get(position).toString();

        /*if (Constants.checkString(thumbImage)) {
            Picasso.with(context).load(thumbImage.trim().replaceAll(" ", "%20"))
                    .error(R.drawable.no_media)
                    .placeholder(R.drawable.no_media)
                    //.resize((int)(0.2 * width),(int)(0.22 * width))
                    .into(holder.voucherImage);

        }else{*/
            holder.voucherImage.setImageResource(voucherList.get(position));

      //  }

        return convert_view;
    }

    class ViewHolder {
        ImageView voucherImage;

    }
}