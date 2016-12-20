package com.example.pustikom.myfirstmap;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;

/**
 * Created by pustikom on 20/12/16.
 */

public class StreetActivity extends AppCompatActivity implements OnMapReadyCallback{
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_street);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }
}
