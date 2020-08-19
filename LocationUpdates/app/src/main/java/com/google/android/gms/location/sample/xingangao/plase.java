
package com.google.android.gms.location.sample.xingangao;

import android.annotation.SuppressLint;
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


public class plase extends ActionBarActivity  {




    Button view1,view2,view3,view4,view5,view6,view7,view8,view9,view10,view11,view12,view13,view14,view15,view16,view17;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plase);

        view1 = (Button) findViewById(R.id.view1);
        view2 = (Button) findViewById(R.id.view2);
        view3=(Button) findViewById(R.id.view3);
        view4=(Button) findViewById(R.id.view4);
        view5=(Button) findViewById(R.id.view5);
        view6=(Button) findViewById(R.id.view6);
        view7 = (Button) findViewById(R.id.view7);
        view8 = (Button) findViewById(R.id.view8);
        view9= (Button) findViewById(R.id.view9);
        view10 = (Button) findViewById(R.id.view10);
        view11 = (Button) findViewById(R.id.view11);
        view12 = (Button) findViewById(R.id.view12);
        view13 = (Button) findViewById(R.id.view13);
        view14 = (Button) findViewById(R.id.view14);
        view15 = (Button) findViewById(R.id.view15);
        view16 = (Button) findViewById(R.id.view16);
        view17 = (Button) findViewById(R.id.view17);
        view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent().setClass(plase.this,view1.class));
            }
        });
        view2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent().setClass(plase.this,view2.class));
            }
        });
        view3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent().setClass(plase.this,view3.class));
            }
        });
        view4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent().setClass(plase.this,view4.class));
            }
        });
        view5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent().setClass(plase.this,view5.class));

            }
        });
        view6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent().setClass(plase.this,view6.class));

            }
        });
        view7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent().setClass(plase.this,view7.class));
            }
        });
        view8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent().setClass(plase.this,view8.class));
            }
        });
        view9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent().setClass(plase.this,view9.class));
            }
        });
        view10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent().setClass(plase.this,view10.class));
            }
        });
        view11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent().setClass(plase.this,view11.class));
            }
        });
        view12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent().setClass(plase.this,view12.class));
            }
        });
        view13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent().setClass(plase.this,view13.class));
            }
        });
        view14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent().setClass(plase.this,view14.class));
            }
        });
        view15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent().setClass(plase.this,view15.class));
            }
        });
        view16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent().setClass(plase.this,view16.class));
            }
        });
        view17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent().setClass(plase.this,view17.class));
            }
        });

        };
    }
