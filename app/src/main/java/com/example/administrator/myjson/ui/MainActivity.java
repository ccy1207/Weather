package com.example.administrator.myjson.ui;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.myjson.R;
import com.example.administrator.myjson.base.WeatherAdaper;
import com.example.administrator.myjson.utils.WeatherGSON;
import com.example.administrator.myjson.config.WeatherInfo;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    ListView mlistview;
    String  str="http://op.juhe.cn/onebox/weather/query?cityname=";
    String  KEY="&key=2f41785c31aaae497aeb72033bbe5f86";
    TextView text_city;
    TextView text_update;
    ArrayList<WeatherInfo> mlistwe;
    private List<WeatherGSON.ResultBean.DataBean.WeatherBean> weather;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text_update= (TextView) findViewById(R.id.date);
        mlistview= (ListView) findViewById(R.id.gson_main);
        text_city= (TextView) findViewById(R.id.gson_city);
    }




    public void get(View view) {
        mlistwe=new ArrayList<>();
          new WeaAsyny().execute("北京");


    }

    class WeaAsyny extends AsyncTask<String,Void,String>{
        String  string;
        @Override
        protected String doInBackground(String... params) {
            Request request = new Request.Builder().url(str+params[0]+KEY).build();
            OkHttpClient mClient=new OkHttpClient();
            try {
                Response response=mClient.newCall(request).execute();
                string=response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.d(" 北京", "doInBackground: "+string);
            return string;
        }

        @Override
        protected void onPostExecute(String s) {

            gson(s);


        }
    }

    public  void  gson(String s){

        Gson  gson=new Gson();
       WeatherGSON    mweathers= gson.fromJson(s,WeatherGSON.class);

        mweathers.getReason();
        Log.d(" reason", "doInBackground: "+  mweathers.getReason());

         text_city.setText(mweathers.getResult().getData().getRealtime().getCity_name());
        text_update.setText(mweathers.getResult().getData().getRealtime().getTime());

        weather = mweathers.getResult().getData().getWeather();
        final WeatherGSON.ResultBean.DataBean.RealtimeBean realtime = mweathers.getResult().getData().getRealtime();
        mlistwe.add(new WeatherInfo(realtime.getDate(),"星期"+realtime.getWeek(),realtime.getWeather().getInfo(),realtime.getMoon()));
        //Log.d(" list", "doInBackground: ");

        for (int i = 0; i < weather.size(); i++) {
            WeatherGSON.ResultBean.DataBean.WeatherBean list = weather.get(i);
            mlistwe.add(new WeatherInfo(list.getDate(),"星期"+list.getWeek(),list.getInfo().getNight().get(1),list.getNongli()));
            Log.d(" list", "doInBackground: "+weather.get(i).getWeek());
        }

         mlistview.setAdapter(new WeatherAdaper(this,mlistwe));

    }

}
