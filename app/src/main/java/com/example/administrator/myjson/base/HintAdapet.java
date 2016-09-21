package com.example.administrator.myjson.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.myjson.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/8/7 0007.
 */
public class HintAdapet extends RecyclerView.Adapter<HintAdapet.HintViewHodler> {

    Context mcontext;
    ArrayList<String> mlist;

      public   HintAdapet(Context c,ArrayList<String> s){
          mcontext=c;
          mlist=s;

      }


    @Override
    public HintViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.recy_hint, parent, false);

        return new HintViewHodler(view);
    }

    @Override
    public void onBindViewHolder(HintViewHodler holder, int position) {

        holder.tv.setText(mlist.get(position));



    }

    @Override
    public int getItemCount() {
        if (mlist!=null){
            return mlist.size();

        }
        return 0;
    }

    class HintViewHodler extends RecyclerView.ViewHolder{

        TextView tv;

        public HintViewHodler(View itemView) {
            super(itemView);
            tv= (TextView) itemView.findViewById(R.id.hint_tx);


        }
    }
}
