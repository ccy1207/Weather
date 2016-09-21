package com.example.administrator.myjson.view;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.AdapterDataObserver;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.administrator.myjson.R;
import com.example.administrator.myjson.base.HintAdapet;
import com.example.administrator.myjson.ui.Main2Activity;
import com.example.administrator.myjson.utils.WeatherGSON;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class HintFragment extends Fragment {
      RecyclerView recy_hint;
    String   url="http://op.juhe.cn/onebox/weather/query?cityname=济南&key=2f41785c31aaae497aeb72033bbe5f86";

      ArrayList<String> mlist;



    public HintFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hint, container, false);
         recy_hint= (RecyclerView) view.findViewById(R.id.hint_recy);
          RecyclerView.LayoutManager  manager=new LinearLayoutManager(getContext());
           recy_hint.setLayoutManager(manager);

       new MainAsync().execute("");
        return view;



    }
    class MainAsync extends AsyncTask<String,Void,String> {
       @Override
        protected String doInBackground(String... params) {


            return  getDATA();
        }

        @Override
        protected void onPostExecute(String s) {

            gson(s);
        }
    }



    public String getDATA() {
        String s="";

        Request request=new Request.Builder().url(url).build();

        try {
            Response response= new OkHttpClient().newCall(request).execute();
            Log.d("response", "getDATA: "+response);
            s=  response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;


    }


    public  void gson(String s){

        Gson gson=new Gson();
        WeatherGSON weathers= gson.fromJson(s,WeatherGSON.class);

        String reason = weathers.getReason();
        if (!reason.equals("successed!")){
            Toast.makeText(getContext(),"获取数据失败",Toast.LENGTH_SHORT).show();

        }else {
            WeatherGSON.ResultBean.DataBean.LifeBean.InfoBean info = weathers.getResult().getData().getLife().getInfo();
            mlist=new ArrayList<>();

            mlist.add("穿衣: \n"+  info.getChuanyi().get(0)+"\n"+info.getChuanyi().get(1));
            mlist.add("空调: \n"+  info.getKongtiao().get(0)+"\n" +info.getKongtiao().get(1));
            mlist.add("感冒: \n"+  info.getGanmao().get(0)+"\n" +info.getGanmao().get(1));
            mlist.add("运动: \n"+  info.getYundong().get(0)+"\n" +info.getYundong().get(1));
            mlist.add("污染: \n"+  info.getWuran().get(0)+"\n" +info.getWuran().get(1));
            mlist.add("紫外线: \n"+    info.getZiwaixian().get(0)+"\n" +info.getZiwaixian().get(1));

            recy_hint.setAdapter(new HintAdapet(getContext(),mlist));
        }


    }


}
