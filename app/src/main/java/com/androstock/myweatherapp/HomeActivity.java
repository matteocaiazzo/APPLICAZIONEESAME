package com.androstock.myweatherapp;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button luoghi = (Button) this.<View>findViewById(R.id.b_luoghi);
        luoghi.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v){
                                          Intent i=new Intent(HomeActivity.this,LuoghiActivity.class);
                                          startActivity(i);
                                      }
                                  }
        );


        Button meteo = (Button) this.<View>findViewById(R.id.b_contapassi);
        meteo.setOnClickListener(new View.OnClickListener() {
                                     @Override
                                     public void onClick(View v){
                                         Intent i=new Intent(HomeActivity.this,ContapassiActivity.class);
                                         startActivity(i);
                                     }
                                 }
        );


        Button maps = (Button) this.<View>findViewById(R.id.b_maps);
        maps.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v){
                                        Intent i=new Intent(HomeActivity.this,MapsActivity.class);
                                        startActivity(i);
                                    }
                                }
        );


        Button bpm = (Button) this.<View>findViewById(R.id.b_bpm);
        bpm.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View v){
                                       Intent i=new Intent(HomeActivity.this,BpmActivity.class);
                                       startActivity(i);
                                   }
                               }
        );







    }
}



