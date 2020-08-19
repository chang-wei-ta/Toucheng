
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


public class traffic extends ActionBarActivity  {




    Button button,button2,button3;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.traffic);

        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);

        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Uri uri=Uri.parse("https://m.facebook.com/sga101/photos/?tab=album&album_id=1120118528163178&__xts__%5B0%5D=33.%7B%22logging_data%22%3A%7B%22event_type%22%3A%22tapped_open_page_album%22%2C%22impression_info%22%3A%22eyJmIjp7InBhZ2VfaWQiOiI4NTE3Mjg5NTE2Njg4MDUiLCJpdGVtX2NvdW50IjoiMCJ9fQ%22%2C%22surface%22%3A%22mobile_page_photos_tab%22%2C%22interacted_story_type%22%3A%22148947852156832%22%2C%22session_id%22%3A%2255f8395ed8776f9cf996fb5afb15cdf7%22%7D%7D&ref=bookmarks&mt_nav=1&_rdr");
                Intent i=new Intent(Intent.ACTION_VIEW,uri);
                startActivity(i);

            }






        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri=Uri.parse("https://www.facebook.com/sga101/?__tn__=kC-R&eid=ARDWFvtdQq2KrO7n2d6S149B74RuAHW4eaBEsNSsV1IX1rL1211yI6hwzJYV1WL1EqGXyFaiCzCiPPST&hc_ref=ART0p5aF6-qaObflvgsCPKQPxtgAETl6GQiUJNu1X1SHvEqg3PcBUggxHL02ioGoVgE&fref=nf");
                Intent i=new Intent(Intent.ACTION_VIEW,uri);
                startActivity(i);
            }
        }
        );

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri=Uri.parse("http://www.gostation.com.tw/index.php/staff/95-131%E7%B7%9A.html");
                Intent i=new Intent(Intent.ACTION_VIEW,uri);
                startActivity(i);
            }
        });

    }
}