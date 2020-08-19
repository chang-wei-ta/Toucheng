/**
 * Copyright 2014 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.android.gms.location.sample.locationupdates;

import android.content.DialogInterface;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
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

public class MainActivity extends AppCompatActivity implements
        ConnectionCallbacks, OnConnectionFailedListener, LocationListener {

    //用於Log 時設定的 TAG 除錯用
    protected static final String TAG = "location-updates-sample";

    //update 時間參數設定
    public static final long UPDATE_INTERVAL_IN_MILLISECONDS = 10000;

    //update 參數
    public static final long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS =
            UPDATE_INTERVAL_IN_MILLISECONDS / 2;

    // Bundle儲存時所需要的Key
    protected final static String REQUESTING_LOCATION_UPDATES_KEY = "requesting-location-updates-key";
    protected final static String LOCATION_KEY = "location-key";
    protected final static String LAST_UPDATED_TIME_STRING_KEY = "last-updated-time-string-key";

    protected GoogleApiClient mGoogleApiClient;
    protected LocationRequest mLocationRequest;
    //現在Location
    protected Location mCurrentLocation;

    // UI Widgets.
    protected TextView mLastUpdateTimeTextView;
    protected TextView mLatitudeTextView;
    protected TextView mLongitudeTextView;
    protected  TextView showarea;
    protected ImageView imgLoca;

    // Labels.
    protected String mLatitudeLabel;
    protected String mLongitudeLabel;
    protected String mLastUpdateTimeLabel;

    //是否有請求locationupdates
    protected Boolean mRequestingLocationUpdates;

    //紀錄update 時間
    protected String mLastUpdateTime;
    //題目答案
    String[] A1 = {"茄冬", "榕樹", "台灣欒樹"};
    String[] A2={"世界上最大的雨林是亞馬遜雨林（Amazon rainforest）","雨林擁有低層次的生物歧異度","雨林都位在於(熱帶地區)"};
    String[] A3={"又稱軟埤","屬於人造湖","四周為大礁溪山麓"};
    String[] A4={"珍珠芭樂和紅心土芭樂","泰國芭樂和紅心土芭樂","珍珠芭樂和泰國芭樂"};


    // 伯朗咖啡城堡
    float LaA1 = (float) 24.884664;
    float LoA1 = (float) 121.838964;
    // 伯朗咖啡城堡2
    float LaA2 = (float) 24.883385;
    float LoA2 = (float) 121.836027;



    // 九號咖啡館
    float LaA3 = (float) 24.878130;
    float LoA3 = (float) 121.841488;

    /************鄰近**********************/
    // 第一海景民宿
    float LaA4 = (float) 24.879074;
    float LoA4 = (float) 121.842029;
    //  海灘風情民宿
    float LaA5 = (float) 24.879450;
    float LoA5 = (float) 121.842340;
    // 真情非凡行館
    float LaA6 = (float) 24.879525;
    float LoA6 = (float) 121.841502;
    /************鄰近**********************/


    //  外澳遊客中心
    float LaA7 = (float) 24.878048;
    float LoA7 = (float) 121.841489;
    //新港澳遊客中心
    float LaA8 = (float) 24.883778;
    float LoA8 = (float) 121.845822;


    //  飛行傘基地
    float LaA9 = (float) 24.885106;
    float LoA9 = (float) 121.842068;

    //  蘭陽博物館
    float LaA10 = (float) 24.868655;
    float LoA10 = (float) 121.832777;

    //  北關海鮮城
    float LaA11 = (float) 24.905163;
    float LoA11 = (float) 121.869566;
    //  梗枋漁港
    float LaA12 = (float) 24.905737;
    float LoA12 = (float) 121.871214;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);


        mLatitudeTextView = (TextView) findViewById(R.id.latitude_text);
        mLongitudeTextView = (TextView) findViewById(R.id.longitude_text);
        mLastUpdateTimeTextView = (TextView) findViewById(R.id.last_update_time_text);
        showarea= (TextView) findViewById(R.id.textView);
        imgLoca= (ImageView) findViewById(R.id.imageView);


        // Set labels.
        mLatitudeLabel = getResources().getString(R.string.latitude_label);
        mLongitudeLabel = getResources().getString(R.string.longitude_label);
        mLastUpdateTimeLabel = getResources().getString(R.string.last_update_time_label);


        mRequestingLocationUpdates = true;
        mLastUpdateTime = "";

        // 抓取 Bundle所儲存的資訊
        updateValuesFromBundle(savedInstanceState);

        //建立googleapiclient 開始API 請求
        buildGoogleApiClient();

    }


    //抓取 Bundle所儲存的資訊
    private void updateValuesFromBundle(Bundle savedInstanceState) {
        Log.i(TAG, "Updating values from bundle");
        if (savedInstanceState != null) {

            if (savedInstanceState.keySet().contains(REQUESTING_LOCATION_UPDATES_KEY)) {
                mRequestingLocationUpdates = savedInstanceState.getBoolean(
                        REQUESTING_LOCATION_UPDATES_KEY);
            }

            if (savedInstanceState.keySet().contains(LOCATION_KEY)) {

                mCurrentLocation = savedInstanceState.getParcelable(LOCATION_KEY);
            }

            if (savedInstanceState.keySet().contains(LAST_UPDATED_TIME_STRING_KEY)) {
                mLastUpdateTime = savedInstanceState.getString(LAST_UPDATED_TIME_STRING_KEY);
            }
            updateUI();
        }
    }


    //建立 GoogleApiClient method 來 Request GoogleAPI
    protected synchronized void buildGoogleApiClient() {
        Log.i(TAG, "Building GoogleApiClient");
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        createLocationRequest();
    }

    protected void createLocationRequest() {
        mLocationRequest = new LocationRequest();

        //setFastestInterval 在update速率上比較快
        //另外一個選項 setInterval(long) 就比較慢
        //詳請參閱Google LocationRequest 官方文件
        //至於為什麼兩個都要呢？
        mLocationRequest.setInterval(UPDATE_INTERVAL_IN_MILLISECONDS);
        mLocationRequest.setFastestInterval(FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS);

        //官方文件
        //PRIORITY_HIGH_ACCURACY 是用GPS
        //PRIORITY_BALANCED_POWER_ACCURACY 則是用 WIFI & Cell tower positioning

        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }


    protected void startLocationUpdates() {
        //Call API
        LocationServices.FusedLocationApi.requestLocationUpdates(
                mGoogleApiClient, mLocationRequest, this);
    }

    //取得經緯度與時間資訊，並且更新UI的數值
    private void updateUI() {
        if (mCurrentLocation != null) {
            mLatitudeTextView.setText(String.format("%s: %f", mLatitudeLabel,
                    mCurrentLocation.getLatitude()));
            mLongitudeTextView.setText(String.format("%s: %f", mLongitudeLabel,
                    mCurrentLocation.getLongitude()));
            mLastUpdateTimeTextView.setText(String.format("%s: %s", mLastUpdateTimeLabel,
                    mLastUpdateTime));




//                                    Toast.makeText(MainActivity.this, "答錯囉~", Toast.LENGTH_SHORT).show();
            //判斷現在位置，做出判斷結果
            if(   Math.pow((mCurrentLocation.getLatitude()-LaA1),2 ) +Math.pow((mCurrentLocation.getLongitude()-LoA1),2 ) < Math.pow(0.0002,2)       )
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("即將抵達伯朗咖啡城堡")
                        .setMessage("是否要觀看詳細資訊")
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();
                Toast.makeText(MainActivity.this,"即將抵達伯朗咖啡城堡",Toast.LENGTH_LONG).show();
            }
            else  if(    Math.pow((mCurrentLocation.getLatitude()-LaA2),2 ) +Math.pow((mCurrentLocation.getLongitude()-LoA2),2 ) < Math.pow(0.0002,2)       )
            {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("即將抵達伯朗咖啡城堡2")
                        .setMessage("是否要觀看詳細資訊")
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();
                Toast.makeText(MainActivity.this,"即將抵達伯朗咖啡城堡2",Toast.LENGTH_LONG).show();
            }
            else  if(    Math.pow((mCurrentLocation.getLatitude()-LaA3),2 ) +Math.pow((mCurrentLocation.getLongitude()-LoA3),2 ) < Math.pow(0.0002,2)       )
            {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("即將抵達九號咖啡館")
                        .setMessage("是否要觀看詳細資訊")
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();
                Toast.makeText(MainActivity.this,"即將抵達九號咖啡館",Toast.LENGTH_LONG).show();
            }
            else  if(    Math.pow((mCurrentLocation.getLatitude()-LaA4),2 ) +Math.pow((mCurrentLocation.getLongitude()-LoA4),2 ) < Math.pow(0.0002,2)       )
            {
                final String[] Item = {"第一海景民宿","海灘風情民宿","真情非凡行館"};
                final String[] single = {""};
                final int[] num = {};
                single[0] = Item[0];
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("是否要觀看詳細資訊")
                        .setSingleChoiceItems(Item, 0, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                single[0] = Item[which];
                                num[0] = which;
                            }
                        })
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Toast.makeText(MainActivity.this,"選了"+single[0],Toast.LENGTH_LONG).show();
                                switch (num[0])
                                {
                                    case 1:
                                        break;
                                    case 2:
                                        break;
                                    case 3:
                                        break;
                                }
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();

            }
            else  if(    Math.pow((mCurrentLocation.getLatitude()-LaA5),2 ) +Math.pow((mCurrentLocation.getLongitude()-LoA5),2 ) < Math.pow(0.0002,2)       )
            {
                final String[] Item = {"第一海景民宿","海灘風情民宿","真情非凡行館"};
                final String[] single = {""};
                final int[] num = {};
                single[0] = Item[0];
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("是否要觀看詳細資訊")
                        .setSingleChoiceItems(Item, 0, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                single[0] = Item[which];
                                num[0] =which
                            }
                        })
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MainActivity.this,"選了"+single[0],Toast.LENGTH_LONG).show();
                                switch (num[0])
                                {
                                    case 1:
                                        break;
                                    case 2:
                                        break;
                                    case 3:
                                        break;
                                }
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();
            }
            else  if(    Math.pow((mCurrentLocation.getLatitude()-LaA6),2 ) +Math.pow((mCurrentLocation.getLongitude()-LoA6),2 ) < Math.pow(0.0002,2)       )
            {
                final String[] Item = {"第一海景民宿","海灘風情民宿","真情非凡行館"};
                final String[] single = {""};
                final int[] num = {};
                single[0] = Item[0];
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("是否要觀看詳細資訊")
                        .setSingleChoiceItems(Item, 0, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                single[0] = Item[which];
                                num[0] = which;
                            }
                        })
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MainActivity.this,"選了"+single[0],Toast.LENGTH_LONG).show();
                                switch (num[0])
                                {
                                    case 1:
                                        break;
                                    case 2:
                                        break;
                                    case 3:
                                        break;
                                }
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();
            }
            else  if(    Math.pow((mCurrentLocation.getLatitude()-LaA7),2 ) +Math.pow((mCurrentLocation.getLongitude()-LoA7),2 ) < Math.pow(0.0002,2)       )
            {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("即將抵達外澳遊客中心")
                        .setMessage("是否要觀看詳細資訊")
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();
                Toast.makeText(MainActivity.this,"即將抵達外澳遊客中心",Toast.LENGTH_LONG).show();
            }
            else  if(    Math.pow((mCurrentLocation.getLatitude()-LaA8),2 ) +Math.pow((mCurrentLocation.getLongitude()-LoA8),2 ) < Math.pow(0.0002,2)       )
            {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("即將抵達新港澳遊客中心")
                        .setMessage("是否要觀看詳細資訊")
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();
                Toast.makeText(MainActivity.this,"即將抵達新港澳遊客中心",Toast.LENGTH_LONG).show();
            }
            else  if(    Math.pow((mCurrentLocation.getLatitude()-LaA9),2 ) +Math.pow((mCurrentLocation.getLongitude()-LoA9),2 ) < Math.pow(0.0002,2)       )
            {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("即將抵達飛行傘基地")
                        .setMessage("是否要觀看詳細資訊")
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();
                Toast.makeText(MainActivity.this,"即將抵達飛行傘基地",Toast.LENGTH_LONG).show();
            }
            else  if(    Math.pow((mCurrentLocation.getLatitude()-LaA10),2 ) +Math.pow((mCurrentLocation.getLongitude()-LoA10),2 ) < Math.pow(0.0002,2)       )
            {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("即將抵達蘭陽博物館")
                        .setMessage("是否要觀看詳細資訊")
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();
                Toast.makeText(MainActivity.this,"即將抵達蘭陽博物館",Toast.LENGTH_LONG).show();
            }
            else  if(    Math.pow((mCurrentLocation.getLatitude()-LaA11),2 ) +Math.pow((mCurrentLocation.getLongitude()-LoA11),2 ) < Math.pow(0.0002,2)       )
            {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("即將抵達北關海鮮城")
                        .setMessage("是否要觀看詳細資訊")
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();
                Toast.makeText(MainActivity.this,"即將抵達北關海鮮城",Toast.LENGTH_LONG).show();
            }
            else  if(    Math.pow((mCurrentLocation.getLatitude()-LaA12),2 ) +Math.pow((mCurrentLocation.getLongitude()-LoA12),2 ) < Math.pow(0.0002,2)       )
            {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("即將抵達梗枋漁港")
                        .setMessage("是否要觀看詳細資訊")
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();
                Toast.makeText(MainActivity.this,"即將抵達梗枋漁港",Toast.LENGTH_LONG).show();
            }


        }
    }




protected void stopLocationUpdates() {
        // 停止呼叫api
        LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        }

@Override
protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
        }

@Override
public void onResume() {
        super.onResume();
        if (mGoogleApiClient.isConnected() && mRequestingLocationUpdates) {
        startLocationUpdates();
        }
        }

@Override
protected void onPause() {
        super.onPause();
        if (mGoogleApiClient.isConnected()) {
        stopLocationUpdates();
        }
        }

@Override
protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
        }

@Override
public void onConnected(Bundle connectionHint) {
        Log.i(TAG, "Connected to GoogleApiClient");

        //假如初始位置沒有被API呼叫過，會先抓此程式lastlocation
        //這段程式碼主要是防止activity 剛開啟抓的location為null時所要處理的
        if (mCurrentLocation == null) {
        mCurrentLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        mLastUpdateTime = DateFormat.getTimeInstance().format(new Date());
        updateUI();
        }
        if (mRequestingLocationUpdates) {
        startLocationUpdates();
        }
        }


@Override
public void onLocationChanged(Location location) {
        //如Location有所改變，更新UI值
        mCurrentLocation = location;
        mLastUpdateTime = DateFormat.getTimeInstance().format(new Date());
        updateUI();
        Toast.makeText(this, getResources().getString(R.string.location_updated_message),
        Toast.LENGTH_SHORT).show();
        }

@Override
public void onConnectionSuspended(int cause) {
        //如果有因為一些原因與google api 斷線，再呼叫一次連線
        Log.i(TAG, "Connection suspended");
        mGoogleApiClient.connect();
        }

@Override
public void onConnectionFailed(ConnectionResult result) {
        //Fail 時除錯用
        Log.i(TAG, "Connection failed: ConnectionResult.getErrorCode() = " + result.getErrorCode());
        }


    /*
     * Bundle  主要用來儲存全域變數
     */
public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putBoolean(REQUESTING_LOCATION_UPDATES_KEY, mRequestingLocationUpdates);
        savedInstanceState.putParcelable(LOCATION_KEY, mCurrentLocation);
        savedInstanceState.putString(LAST_UPDATED_TIME_STRING_KEY, mLastUpdateTime);
        super.onSaveInstanceState(savedInstanceState);
        }
        }
