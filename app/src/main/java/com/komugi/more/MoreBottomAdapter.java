package com.komugi.more;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.komugi.LoginRegisterActivity;
import com.komugi.R;
import com.komugi.textviewtypeface.CenturyGothicTextView;
import com.komugi.utils.Constants;

import java.util.ArrayList;

/**
 * Created by iapp on 21/12/16.
 */
public class MoreBottomAdapter extends BaseAdapter {


    ArrayList<String> dataList;
    Context context;
    Activity act;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

    public MoreBottomAdapter(Activity act,Context context, ArrayList<String> dataList) {
        this.context = context;
        this.dataList = dataList;
        this.act=act;
        sharedPref = act.getSharedPreferences(Constants.PREFS_NAME, 0);
        editor = sharedPref.edit();
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
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
            convert_view = inflater.inflate(R.layout.single_more_bottom_view, parent, false);
            holder = new ViewHolder();
            holder.moreOptionTv=(CenturyGothicTextView) convert_view.findViewById(R.id.more_option_tv);

            convert_view.setTag(holder);
        } else {
            holder = (ViewHolder) convert_view.getTag();
        }

        holder.moreOptionTv.setText(dataList.get(position));
        if(position%2==0){
            holder.moreOptionTv.setBackgroundColor(context.getResources().getColor(R.color.login_reg));
        }
        else{
            holder.moreOptionTv.setBackgroundColor(context.getResources().getColor(R.color.term_con_popup_bg));
        }
        holder.moreOptionTv.setTag(position);
        holder.moreOptionTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos=(int)v.getTag();

                if(pos==0){
                    Intent it=new Intent(act, MyProfileActivity.class);
                    act.startActivity(it);
                    act.overridePendingTransition(0,0);
                    act.finish();
                }
                else if(pos==1){
                    Intent it=new Intent(act, ShareToFriend.class);
                    act.startActivity(it);
                    act.overridePendingTransition(0,0);
                    act.finish();
                }
                else if(pos==2){
                    Intent it=new Intent(act, AboutActivity.class);
                    act.startActivity(it);
                    act.overridePendingTransition(0,0);
                    act.finish();
                }
                else if(pos==3){
                    Intent it=new Intent(act, SocialMediaActivity.class);
                    act.startActivity(it);
                    act.overridePendingTransition(0,0);
                    act.finish();
                }
                else if(pos==4){
                    Intent it=new Intent(act, LocationActivity.class);
                    act.startActivity(it);
                    act.overridePendingTransition(0,0);
                    act.finish();
                }
                else if(pos==5){
                    editor.clear();
                    editor.commit();
                    Intent it=new Intent(act, LoginRegisterActivity.class);
                    act.startActivity(it);
                    act.finish();

                }
            }
        });

        return convert_view;
    }

    class ViewHolder {
        CenturyGothicTextView moreOptionTv;

    }
}
