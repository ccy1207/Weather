package com.example.administrator.myjson.view;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myjson.R;
import com.example.administrator.myjson.base.MainAdapet;
import com.example.administrator.myjson.base.WeatherAdaper;
import com.example.administrator.myjson.config.FutureInfo;
import com.example.administrator.myjson.config.WeatherInfo;
import com.example.administrator.myjson.ui.Main2Activity;
import com.example.administrator.myjson.utils.WeatherGSON;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {
       ImageView im_wea;
    TextView tv_city;
    TextView tv_temp;
    TextView tv_date;
    TextView tv_noli;
    TextView tv_up;
    TextView tv_week;
    TextView tv_fusu;
    RecyclerView recy;
ArrayList<FutureInfo> mlist;
    String   url="http://op.juhe.cn/onebox/weather/query?cityname=济南&key=2f41785c31aaae497aeb72033bbe5f86";
    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        tv_city=  (TextView) view.findViewById(R.id.fra_cily);
        tv_date= (TextView) view.findViewById(R.id.fra_date);
        tv_temp= (TextView) view.findViewById(R.id.fra_temperature);
        tv_noli= (TextView) view.findViewById(R.id.fra_noli);
        tv_up= (TextView) view.findViewById(R.id.fra_up);
        im_wea= (ImageView) view.findViewById(R.id.fra_weather);
        tv_week= (TextView) view.findViewById(R.id.fra_week);
        tv_fusu= (TextView) view.findViewById(R.id.fra_fusu);
             new MainAsync().execute("");
        recy= (RecyclerView) view.findViewById(R.id.fra_recy);


        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

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

    private void gson(String string) {

        Gson gson=new Gson();
        WeatherGSON   weathers= gson.fromJson(string,WeatherGSON.class);

        String reason = weathers.getReason();
        if (!reason.equals("successed!")){
            Toast.makeText(getContext(),"获取数据失败",Toast.LENGTH_SHORT).show();

        }else {
            WeatherGSON.ResultBean.DataBean.RealtimeBean realtime = weathers.getResult().getData().getRealtime();
            List<WeatherGSON.ResultBean.DataBean.WeatherBean> weather1 = weathers.getResult().getData().getWeather();
                  getWeather(weather1);
            //城市名字
              tv_city.setText(realtime.getCity_name());
            //更新时间
            tv_up.setText("更新时间:"+ realtime.getTime());
            //天气
            WeatherGSON.ResultBean.DataBean.RealtimeBean.WeatherBean weather = realtime.getWeather();
            //温度
            tv_temp.setText(weather.getTemperature()+" ℃");
            //日期
            tv_date.setText(realtime.getDate());
            //农历
            tv_noli.setText(realtime.getMoon());
            //星期
            tv_week.setText("星期"+ weather1.get(0).getWeek());
            //风速
            String s = realtime.getWind().getWindspeed() + "m/s  ";
            String direct = realtime.getWind().getDirect()+" ";
            String power = realtime.getWind().getPower()+" ";

            tv_fusu.setText(direct+s+power);
        }


    }


    public void getWeather( List<WeatherGSON.ResultBean.DataBean.WeatherBean> wea  ){
         mlist=new ArrayList<>();
        for (int i = 1; i < wea.size(); i++) {

                WeatherGSON.ResultBean.DataBean.WeatherBean object= wea.get(i);

       mlist.add(new FutureInfo(Integer.parseInt(object.getInfo().getNight().get(0)) ,"星期"+ object.getWeek(),object.getInfo().getNight().get(2)+" ℃"));

        }
        //   Log.d("getWeather", "getDATA: "+mlist.get(1).mweek);


        recy.setHasFixedSize(true);
         RecyclerView.LayoutManager manager=new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
               recy.setLayoutManager(manager);


             recy.setAdapter(new MainAdapet(getContext(),mlist));



    }

}
