package com.example.administrator.myjson.ui;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.administrator.myjson.R;
import com.example.administrator.myjson.view.HintFragment;
import com.example.administrator.myjson.view.JokeFragmen;
import com.example.administrator.myjson.view.MainFragment;
import com.example.administrator.myjson.view.NationFragment;
import com.example.administrator.myjson.view.TelFragment;

public class Main2Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
         DrawerLayout  braw;
   ActionBarDrawerToggle mToggle;
        NavigationView  mnavi;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
         braw= (DrawerLayout) findViewById(R.id.draw_lay);
          mToggle= new ActionBarDrawerToggle(this,braw,R.string.open,R.string.close);
          braw.addDrawerListener(mToggle);

        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
          mnavi= (NavigationView) findViewById(R.id.nav_view);
        mnavi.setNavigationItemSelectedListener(this);

        getSupportFragmentManager().beginTransaction().replace(R.id.test5,new MainFragment()).commit();

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return      mToggle.onOptionsItemSelected(item)||super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Toast.makeText(this,"点击了"+item.getTitle() ,Toast.LENGTH_SHORT).show();

        switch ( item.getItemId()){


            case  R.id.nav_camera:
              //  Toast.makeText(this,"点击了"+item.getTitle() ,Toast.LENGTH_SHORT).show();
                getSupportFragmentManager().beginTransaction().replace(R.id.test5,new NationFragment()).commit();
                break;
            case R.id.nav_joke:
                  getSupportFragmentManager().beginTransaction().replace(R.id.test5,new JokeFragmen()).commit();
                break;
            case R.id.nav_future :
                getSupportFragmentManager().beginTransaction().replace(R.id.test5,new MainFragment()).commit();
                break;
            case  R.id.nav_hint:
                getSupportFragmentManager().beginTransaction().replace(R.id.test5,new HintFragment()).commit();
                break;
            case  R.id.nav_tel:
                getSupportFragmentManager().beginTransaction().replace(R.id.test5,new TelFragment()).commit();
                break;
        }


        braw.closeDrawer(GravityCompat.START);


        return true;
    }

    @Override
    public void onBackPressed() {
        if (braw.isDrawerOpen(GravityCompat.START)){
            braw.closeDrawer(GravityCompat.START);

        }else {
            super.onBackPressed();

        }



    }
}
