package com.example.administrator.myjson.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.myjson.R;
import com.example.administrator.myjson.config.FutureInfo;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/8/7 0007.
 */
public class MainAdapet extends RecyclerView.Adapter<MainAdapet.MyAdapet> {
   Context mContext;
    ArrayList<FutureInfo> mlist;

    int[] imgs={R.drawable.icon_qing,R.drawable.icon_dayu ,R.drawable.icon_duoyun,R.drawable.icon_leizhen,R.drawable.icon_leizhen,R.drawable.icon_leizhen,R.drawable.icon_leizhen};

    public MainAdapet(Context c,ArrayList<FutureInfo> list){
      mContext=c;
        mlist=list;

    }

    @Override
    public MyAdapet onCreateViewHolder(ViewGroup parent, int viewType) {
          View view= LayoutInflater.from(mContext).inflate(R.layout.main_recy,parent,false);

        return new MyAdapet(view);
    }

    @Override
    public void onBindViewHolder(MyAdapet holder, int position) {
        FutureInfo info = mlist.get(position);
        holder.img.setImageResource(imgs[position]);
        holder.temperature.setText(info.mTemperature);
        holder.week.setText(info.mweek);


    }

    @Override
    public int getItemCount() {

        if (mlist!=null){
            return mlist.size();

        }
        return 0;
    }

    class MyAdapet extends RecyclerView.ViewHolder{
         TextView week;
         TextView temperature;
         ImageView img;

        public MyAdapet(View itemView) {
            super(itemView);
            week= (TextView) itemView.findViewById(R.id.recy_week);
            temperature= (TextView) itemView.findViewById(R.id.recy_temperature);
            img= (ImageView) itemView.findViewById(R.id.recy_weather);


        }
    }
}
