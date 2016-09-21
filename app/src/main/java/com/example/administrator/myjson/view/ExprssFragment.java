package com.example.administrator.myjson.view;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.administrator.myjson.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExprssFragment extends Fragment {
      public EditText ed_number;
     public Button  bnt_exp;
         ListView  lv_exp;

    public ExprssFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_exprss, container, false);
         ed_number= (EditText) view.findViewById(R.id.exp_ed);
        bnt_exp= (Button) view.findViewById(R.id.exp_bnt);
        lv_exp= (ListView) view.findViewById(R.id.exp_lv);


        return view;
    }


    class ExprssAsync extends AsyncTask<Void,Void,Void>{


        @Override
        protected Void doInBackground(Void... params) {
            return null;
        }
    }

}
