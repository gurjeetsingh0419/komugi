package com.komugi.more;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.komugi.R;
import com.komugi.more.LocationModel;

import java.util.ArrayList;

/**
 * Created by iapp on 21/12/16.
 */

public class LocationAdapter extends BaseAdapter {

    Context context;
    ArrayList<LocationModel> locationList;


    public LocationAdapter(Context context, ArrayList<LocationModel> locationList) {
        this.context = context;
        this.locationList=locationList;
    }
    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public Object getItem(int position) {
        return locationList.get(position);
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
            convert_view = inflater.inflate(R.layout.single_view_location, parent, false);
            holder = new ViewHolder();

            holder.komugiName=(TextView) convert_view.findViewById(R.id.name);



            convert_view.setTag(holder);
        } else {
            holder = (ViewHolder) convert_view.getTag();
        }



        return convert_view;
    }

    class ViewHolder {
        TextView komugiName;

    }
}
