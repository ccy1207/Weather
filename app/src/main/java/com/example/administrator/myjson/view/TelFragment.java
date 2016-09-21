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
import android.widget.TextView;

import com.example.administrator.myjson.R;
import com.example.administrator.myjson.utils.TelGSON;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class TelFragment extends Fragment {
       String url="http://apis.juhe.cn/mobile/get?phone=";
        String KEY="&key=3717e5347ec8b517a780c585362032f7";
        TextView tv;
    Button  bnt;
    EditText et;

    public TelFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view= inflater.inflate(R.layout.fragment_tel, container, false);
              tv= (TextView) view.findViewById(R.id.tel_tv);
            bnt= (Button) view.findViewById(R.id.tel_bnt);
           et= (EditText) view.findViewById(R.id.tel_number);

          bnt.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {

                  String trim = et.getText().toString().trim();

                  new MainAsync().execute(trim);

              }
          });

        return view;
    }



    class MainAsync extends AsyncTask<String,Void,String> {
        @Override
        protected String doInBackground(String... params) {


            return  getDATA(params[0]);
        }

        @Override
        protected void onPostExecute(String s) {

            gson(s);
        }
    }



    public String getDATA(  String str) {
        String s="";

        Request request=new Request.Builder().url(url+str+KEY).build();

        try {
            Response response= new OkHttpClient().newCall(request).execute();

            s=  response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;


    }


    public  void gson(String s){

        Gson gson=new Gson();
        TelGSON json = gson.fromJson(s, TelGSON.class);

       if (json.getReason().equals("Return Successd!")){

           TelGSON.ResultBean result = json.getResult();

             tv.setText( "地区: "+result.getProvince()+ result.getCity()+"\n 区号: "+result.getAreacode()+"\n运营商: "+result.getCompany()+"\n邮编: "+result.getZip());



       }else {


           tv.setText(json.getReason());
       }


    }

}
