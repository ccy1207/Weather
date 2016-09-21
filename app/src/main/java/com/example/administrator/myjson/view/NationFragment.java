package com.example.administrator.myjson.view;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.myjson.R;
import com.example.administrator.myjson.config.WeatherInfo;
import com.example.administrator.myjson.base.WeatherAdaper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class NationFragment extends Fragment implements View.OnClickListener{


    private View view;
    TextView tv_date;
    TextView tv_tenper;
    TextView tv_fengsu;
    TextView tv_weather;
    TextView tv_city;
    EditText et_city;
    Button button;
    ArrayList<WeatherInfo> mliset;

    ListView lv_weather;
    ListView info_lv1;

    String KEY="&key=2f41785c31aaae497aeb72033bbe5f86";
    String  weaData=null;

    public NationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.weather, container, false);

        tv_weather= (TextView)view.findViewById(R.id.weather);
        tv_date= (TextView) view.findViewById(R.id.data);
        tv_tenper= (TextView) view.findViewById(R.id.temperature);
        tv_fengsu= (TextView) view.findViewById(R.id.fengsu);
        tv_city= (TextView) view.findViewById(R.id.city);
        et_city= (EditText) view.findViewById(R.id.weather_et);
        button= (Button) view.findViewById(R.id.weather_bnt);
        lv_weather= (ListView)  view.findViewById(R.id.weather_lv);
        info_lv1= (ListView)  view.findViewById(R.id.info_lv1);
        button.setOnClickListener(this);

        return view;



    }

    @Override
    public void onClick(View v) {
        mliset=new ArrayList<>();
        String city=et_city.getText().toString().trim();
        new TestAsync().execute("济南");
        Log.d("city ", "getWeather: "+city);
//        if (city.equals("")){
//
//            et_city.setHint("输入的城市为空");
//
//        }else {
//
//            new TestAsync().execute(city);
//        }
    }

    class  TestAsync extends AsyncTask<String,Void,String> {


        @Override
        protected String doInBackground(String... params) {
            StringBuilder builder=new StringBuilder();
            try {
                URL url=new URL("http://op.juhe.cn/onebox/weather/query?cityname="+params[0]+"&key=2f41785c31aaae497aeb72033bbe5f86");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.connect();

                if (connection.getResponseCode()==200){
                    InputStream is= connection.getInputStream();
                    BufferedReader reader=new BufferedReader(new InputStreamReader(is));

                    String readerLine="";

                    while ((readerLine=reader.readLine())!=null){

                        builder.append(readerLine);

                    }
                    reader.close();
                    is.close();
                    connection.disconnect();

                }






            } catch (Exception e) {
                e.printStackTrace();
            }


            return builder.toString();
        }
        @Override
        protected void onPostExecute(String s) {
            weaData=s;
            JSONObject object= null;
            try {
                object = new JSONObject(s);
                String reason    =  object.getString("reason");

                Log.d(" ", "onPostExecute: "+reInfo(reason));
                if (reInfo(reason)) {
                    JSONObject array = object.getJSONObject("result");

                    Log.d("onPostExecute", "array: " + array);
                    JSONObject array1 = array.getJSONObject("data");

                    //    Log.d("onPostExecute", "array1: "+array1);
                    JSONObject array2 = array1.getJSONObject("realtime");
                    JSONArray weather1 = array1.getJSONArray("weather");
                    //   Log.d("onPostExecute", "weather1: "+weather1);
                       getWeather(weather1);
                    JSONObject array3 = array2.getJSONObject("wind");
                    // Log.d("onPostExecute", "array3: "+array3);
                    String fusu = array3.getString("windspeed");
                    String direct = array3.getString("direct");
                    String power = array3.getString("power");

                    tv_fengsu.setText(fusu + " m/s" + " " + direct + " " + power);
                    JSONObject array4 = array2.getJSONObject("weather");
                    String data = array2.getString("time");
                    String city = array2.getString("city_name");

                    tv_date.setText(data);
                    tv_city.setText(city);
                    String weather = array4.getString("info");
                    String temperature = array4.getString("temperature");
                    tv_weather.setText(weather);
                    tv_tenper.setText(temperature + " ℃");

                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            super.onPostExecute(s);
        }



    }
    public void getWeather(   JSONArray wea  ){
        for (int i = 0; i < wea.length(); i++) {

            try {
                JSONObject object= wea.getJSONObject(i);
                String data =object.getString("date");
                String week =object.getString("week");
                String nongli =object.getString("nongli");
                JSONObject info= object.getJSONObject("info");
                JSONArray      day=  info.getJSONArray("day");
                String weather   =   day.getString(1);

                WeatherInfo  weatherinfo=new WeatherInfo(data,week,weather,nongli);
                mliset.add(weatherinfo);
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }


        lv_weather.setAdapter(new WeatherAdaper(getContext(),mliset));



    }

    public  boolean  reInfo(String s){
        boolean isRun=false;
        et_city.setText("");
        et_city.setHint(s);
        if(s.equals("successed!")){
            isRun=true;

        }


        return isRun;


    }
}
