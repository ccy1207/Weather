package com.example.administrator.myjson.view;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.administrator.myjson.utils.JokeGSON;
import com.example.administrator.myjson.config.JokeInfo;
import com.example.administrator.myjson.R;
import com.example.administrator.myjson.base.JokeAdapet;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class JokeFragmen extends Fragment {

    String url="http://japi.juhe.cn/joke/content/list.from";
       String KEY="?key=075d233be5b94bc02d8e197a0328c9e0";
      String SET="&page=2&pagesize=10&sort=desc&time=";
     ListView joke_lv;
    Button joke_bnt;
    ArrayList<JokeInfo> mlist;

    public JokeFragmen() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_joke, container, false);

             joke_lv= (ListView) view.findViewById(R.id.joke_lv);
        mlist=new ArrayList<>();
        long aLong = System.currentTimeMillis();

        int i= (int) (aLong/1000);
        new JokeAsync().execute(i+"");

        return view;

    }

   //异步下载数据
    class JokeAsync extends AsyncTask<String,Void,String>{


        @Override
        protected String doInBackground(String... params) {
            String s=null;
            Request request=new Request.Builder().url(url+KEY+SET+params[0]).build();
            try {
                Response   response=new OkHttpClient().newCall(request).execute();
                      s=  response.body().string() ;
            } catch (IOException e) {
                e.printStackTrace();
            }

            return s;
        }

        @Override
        protected void onPostExecute(String s) {

       //     Log.d("笑话大全", "onPostExecute: "+s);
         gson(s);

        }
    }
    //数据处理
     public  void gson(String s){
         Gson gson=new Gson();

         JokeGSON jokes=gson.fromJson(s,JokeGSON.class);
        // Log.d("笑话大全", "onPostExecute: "+jokes);


              if (jokes.getResult().equals("")){
                  Toast.makeText(getContext(),"获取数据失败",Toast.LENGTH_SHORT).show();
              }else {
            //    Log.d("笑话大全", "onPostExecute: "+jokes.getResult());
              List<JokeGSON.ResultBean.DataBean> data = jokes.getResult().getData();
            // Log.d("笑话大全", "onPostExecute: "+data);
              for (int i = 0; i < data.size(); i++) {
                  JokeGSON.ResultBean.DataBean dataBean = data.get(i);
            //      Log.d("笑话大全"+dataBean.getUpdatetime(), "onPostExecute: "+dataBean.getContent());

                  mlist.add(new JokeInfo(dataBean.getUpdatetime(),dataBean.getContent())) ;


              }





         joke_lv.setAdapter(new JokeAdapet(getContext(),mlist));

     }}

    //下拉更新
    class  MyTouchListenter implements View.OnTouchListener {


        @Override
        public boolean onTouch(View v, MotionEvent event) {
            return false;
        }
    }

}
