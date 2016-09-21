package com.example.administrator.myjson.ui;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myjson.R;
import com.example.administrator.myjson.base.WeatherAdaper;
import com.example.administrator.myjson.config.WeatherInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class WeatherActivity extends AppCompatActivity {


   ArrayList<String>  alist;

    TextView tv_data;
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


    private StringBuilder  str=new StringBuilder();
      String  weaData=null;
    String  TAG="WeatherActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather);
           tv_weather= (TextView) findViewById(R.id.weather);
           tv_data= (TextView) findViewById(R.id.data);
           tv_tenper= (TextView) findViewById(R.id.temperature);
           tv_fengsu= (TextView) findViewById(R.id.fengsu);
           tv_city= (TextView) findViewById(R.id.city);
          et_city= (EditText) findViewById(R.id.weather_et);
        lv_weather= (ListView) findViewById(R.id.weather_lv);
        info_lv1= (ListView) findViewById(R.id.info_lv1);
        button= (Button) findViewById(R.id.bnt_weather);


      lv_weather.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          @Override
          public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                   if (weaData!=null){
                       alist=new ArrayList<>();
                              weaData(weaData,position);

                   }


          }
      });

     }
    public  void  weaData(String data,int position){
        try {
            JSONArray weathers= new JSONObject(data).getJSONObject("result").getJSONObject("data").getJSONArray("weather");
              JSONObject  object= (JSONObject) weathers.get(position);
            Log.d(TAG, "weaData: "+  object);

              alist.add(object.getString("date"));
              alist.add("星期"+object.getString("week"));
              alist.add("农历 "+object.getString("nongli"));
              JSONObject info=object.getJSONObject("info");
            JSONArray dawn=null;
               try {
                    dawn =info.getJSONArray("dawn");
               }catch(JSONException e) {
                   e.printStackTrace();
               }

            if (dawn==null){
                     dawn =info.getJSONArray("day");

            }
                 JSONArray night =info.getJSONArray("night");
                  alist.add("白天:"+dawn.getString(1)+" "+dawn.getString(2)+"℃ "+dawn.getString(3));
                  alist.add("晚上:"+night.getString(1)+" "+night.getString(2)+"℃ "+night.getString(3));




        } catch (JSONException e) {
            e.printStackTrace();
        }
       // isweathers=true;
        ArrayAdapter adapter= new ArrayAdapter(this,R.layout.info_lv,R.id.info_tv,alist);
        info_lv1.setAdapter(adapter);
    }

    public void getWeather(View view) {
        mliset=new ArrayList<>();
         String city=et_city.getText().toString().trim();
    //    Log.d("city ", "getWeather: "+city);
        if (city.equals("")){

            et_city.setHint("输入的城市为空");

        }else {

            new TestAsync().execute(city);
        }
    }

    class  TestAsync extends AsyncTask<String,Void,String>{


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

                     tv_data.setText(data);
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

    class  TeAsync extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... params) {
            StringBuilder builder=new StringBuilder();
            try {
                URL url=new URL("http://cloud.bmob.cn/0906a62b462a3082/getMemberBySex?");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.connect();
                String temp="sex="+params[0];
                Log.d("MyAsync", "temp: "+temp);


                DataOutputStream output=new DataOutputStream(connection.getOutputStream());
                output.writeBytes(temp);
                Log.d("MyAsync", "output: "+output);
                output.flush();
                output.close();

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


                }else {
                    Toast.makeText(WeatherActivity.this,"连接失败",Toast.LENGTH_SHORT).show();
                }



            } catch (Exception e) {
                e.printStackTrace();
            }


            return builder.toString();
        }

        @Override
        protected void onPostExecute(String s) {

            try {
                JSONObject object=new JSONObject(s);
                JSONArray  array= object.getJSONArray("list");
            //    Log.d("onPostExecute", "array: "+array);
                for (int i = 0; i < array.length(); i++) {
                    JSONObject ject=array.getJSONObject(i);
                    Log.d("onPostExecute", "ject: "+ject);


                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            tv_weather.setText(s);
        }
    }

    class  MyAsync extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... params) {
            StringBuilder builder=new StringBuilder();
                try {
                URL url=new URL("http://op.juhe.cn/onebox/weather/query?cityname=");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.connect();
                  String temp=params[0]+KEY;
                Log.d("MyAsync", "temp: "+temp);


                DataOutputStream output=new DataOutputStream(connection.getOutputStream());
                output.writeBytes(temp);
                Log.d("MyAsync", "output: "+output);
                output.flush();
                output.close();

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

                }else {
                    Toast.makeText(WeatherActivity.this,"连接失败",Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


            return builder.toString();
        }

        @Override
        protected void onPostExecute(String s) {

            tv_weather.setText(s);


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
                   //   Log.d("day" +weather , "getWeather");
              WeatherInfo  weatherinfo=new WeatherInfo(data,week,weather,nongli);
          mliset.add(weatherinfo);
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
        //     isweathers=false;

        lv_weather.setAdapter(new WeatherAdaper(this,mliset));



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
