package com.example.pustikom.myfirstmap;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.OnStreetViewPanoramaReadyCallback;
import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.StreetViewPanoramaFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;

/**
 * Created by pustikom on 20/12/16.
 */

public class StreetActivity extends AppCompatActivity implements OnStreetViewPanoramaReadyCallback{
    private StreetViewPanoramaFragment streetViewPanoramaFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_street);
        streetViewPanoramaFragment = (StreetViewPanoramaFragment) getFragmentManager().findFragmentById(R.id.streetviewpanorama);
        streetViewPanoramaFragment.getStreetViewPanoramaAsync(this);
    }

    @Override
    public void onStreetViewPanoramaReady(StreetViewPanorama streetViewPanorama) {
        streetViewPanorama.setPosition(new LatLng(36.0579667,-112.1430996));
        StreetViewPanoramaCamera streetViewPanoramaCamera = StreetViewPanoramaCamera.builder().bearing(180).build();
        streetViewPanorama.animateTo(streetViewPanoramaCamera,1000);
    }
}
