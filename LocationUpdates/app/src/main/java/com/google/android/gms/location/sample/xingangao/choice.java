
package com.google.android.gms.location.sample.xingangao;

import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.text.DateFormat;
import java.util.Date;

public class choice extends ActionBarActivity  {


    ImageView imgLoca;

    Button button_green,button_place,button_game,button_diy;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choice);



        imgLoca= (ImageView) findViewById(R.id.imageView);

        button_game= (Button) findViewById(R.id.button_game);
        button_green= (Button) findViewById(R.id.button_green);
        button_place= (Button) findViewById(R.id.button_place);
        button_diy= (Button) findViewById(R.id.button_diy);
        imgLoca.setImageDrawable(getResources().getDrawable(R.drawable.logo));
        button_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent().setClass(choice.this,MainActivity.class));

            }
        });
        button_green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent().setClass(choice.this,traffic.class));
            }
        });
        button_place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent().setClass(choice.this,plase.class));
            }
        });
        button_diy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // startActivity(new Intent().setClass(choice.this,diy.class));
//                Toast.makeText(choice.this, "NOT READY", Toast.LENGTH_LONG).show();
                Uri uri=Uri.parse("https://m.facebook.com/sga101/video_grid/?playlist_id=305337580320871&ref=page_internal");
                Intent i=new Intent(Intent.ACTION_VIEW,uri);
                startActivity(i);
            }
        });



    }}


