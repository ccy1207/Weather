package com.example.administrator.myjson.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.myjson.R;
import com.example.administrator.myjson.config.WeatherInfo;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/8/2.
 */
public class WeatherAdaper extends BaseAdapter {
    Context mContext;
    ArrayList<WeatherInfo> mlist;

    public   WeatherAdaper( Context context,ArrayList<WeatherInfo> list ){
        mContext=context;
        mlist=list;

    }


    @Override
    public int getCount() {
        return mlist.size();
    }

    @Override
    public Object getItem(int position) {
        return mlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHodler mhodler=null;
        if (convertView==null){
            convertView= LayoutInflater.from(mContext).inflate(R.layout.weather_lv,null);
            mhodler=new ViewHodler();
            mhodler.tv_data= (TextView) convertView.findViewById(R.id.lv_data);
            mhodler.tv_weather= (TextView) convertView.findViewById(R.id.lv_weather);
            mhodler.tv_week= (TextView) convertView.findViewById(R.id.lv_week);
            mhodler.tv_noli= (TextView) convertView.findViewById(R.id.lv_noli);
            convertView.setTag(mhodler);


        }else {

         mhodler= (ViewHodler) convertView.getTag();
        }
        mhodler.tv_data.setText(mlist.get(position).data);
        mhodler.tv_week.setText(mlist.get(position).week);
        mhodler.tv_weather.setText(mlist.get(position).weather);
        mhodler.tv_noli.setText(mlist.get(position).noli);


        return convertView;
    }

    class ViewHodler{
        TextView tv_noli;
        TextView tv_data;
        TextView tv_weather;
        TextView tv_week;




    }


}
