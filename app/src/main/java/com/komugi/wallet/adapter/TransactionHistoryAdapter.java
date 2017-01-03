package com.komugi.wallet.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.komugi.R;
import com.komugi.wallet.TransactionModel.TransactionHistoryModelClass;

import java.util.ArrayList;




public class TransactionHistoryAdapter extends BaseAdapter {

    Context context;
    ArrayList<TransactionHistoryModelClass> transactionList;


    public TransactionHistoryAdapter(Context context, ArrayList<TransactionHistoryModelClass> transactionList) {
        this.context = context;
        this.transactionList=transactionList;
    }
    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public Object getItem(int position) {
        return transactionList.get(position);
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
            convert_view = inflater.inflate(R.layout.single_transaction_layout, parent, false);
            holder = new ViewHolder();

            holder.transType=(TextView) convert_view.findViewById(R.id.trans_type_text);



            convert_view.setTag(holder);
        } else {
            holder = (ViewHolder) convert_view.getTag();
        }



        return convert_view;
    }

    class ViewHolder {
        TextView transTypeText,transType,date,time,location,amnount;

    }
}