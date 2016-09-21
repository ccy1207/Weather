package com.example.administrator.myjson.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.myjson.config.JokeInfo;
import com.example.administrator.myjson.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/8/4.
 */
public class JokeAdapet extends BaseAdapter {

    Context mContext;
    ArrayList<JokeInfo> mlist;

    public   JokeAdapet( Context context,ArrayList<JokeInfo> list ){
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
            convertView= LayoutInflater.from(mContext).inflate(R.layout.joke_lv,null);
            mhodler=new ViewHodler();
            mhodler.tv_date= (TextView) convertView.findViewById(R.id.joke_date);
            mhodler.tv_content= (TextView) convertView.findViewById(R.id.joke_content);

            convertView.setTag(mhodler);


        }else {

            mhodler= (ViewHodler) convertView.getTag();
        }
        mhodler.tv_date.setText(mlist.get(position).mdate);
        mhodler.tv_content.setText(mlist.get(position).mcontent);



        return convertView;
    }

    class ViewHodler{

        TextView tv_date;
        TextView tv_content;





    }

}
