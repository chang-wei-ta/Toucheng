
package com.google.android.gms.location.sample.xingangao;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ImageView;

public class view8 extends ActionBarActivity  {


    protected ImageView imgLoca;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view8);



        imgLoca= (ImageView) findViewById(R.id.imageView);
        imgLoca.setImageDrawable(getResources().getDrawable(R.drawable.cpv8));


    }}


