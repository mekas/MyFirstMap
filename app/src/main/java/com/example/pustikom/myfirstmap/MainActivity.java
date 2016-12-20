package com.example.pustikom.myfirstmap;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback{

    private GoogleMap m_map;
    private boolean mapReady=false;
    private static LatLng seattle = new LatLng(47.6204,-122.2391);
    private static LatLng newYork = new LatLng(40.7127,74.0059);
    private static LatLng dubline =new LatLng(53.3478,6.2597);
    private static CameraPosition NEWYORK_VIEW = CameraPosition.builder().target(newYork).zoom(21).bearing(0).tilt(45).build();
    private static CameraPosition SEATTLE_VIEW = CameraPosition.builder().target(seattle).zoom(17).bearing(0).tilt(45).build();
    private static CameraPosition DUBLINE_VIEW = CameraPosition.builder().target(dubline).zoom(17).bearing(90).tilt(45).build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnMap = (Button) findViewById(R.id.btnMap);
        Button btnHybrid = (Button) findViewById(R.id.btnHybrid);
        Button btnSatellite = (Button) findViewById(R.id.btnSatellite);
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        /*
         * Set all listeners
         */
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mapReady)
                    m_map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            }
        });
        btnHybrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mapReady)
                    m_map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
            }
        });
        btnSatellite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mapReady)
                    m_map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapReady=true;
        m_map=googleMap;
        LatLng currentPos = new LatLng(-6.194247,106.878189);
        CameraPosition target=CameraPosition.builder().target(currentPos).zoom(14).build();
        m_map.moveCamera(CameraUpdateFactory.newCameraPosition(target));

        m_map.animateCamera(CameraUpdateFactory.newCameraPosition(SEATTLE_VIEW),7000,new InvokeMap(NEWYORK_VIEW));
        MarkerOptions defaultMarker = new MarkerOptions().position(currentPos).title("UNJ");
        m_map.moveCamera(CameraUpdateFactory.newCameraPosition(target));
        m_map.addMarker(defaultMarker);
        m_map.addPolyline(new PolylineOptions().geodesic(true)
                .add(currentPos)
                .add(seattle)
                .add(newYork)
                .add(dubline)
                .add(currentPos)
        );
        //m_map.animateCamera(CameraUpdateFactory.newCameraPosition(DUBLINE_VIEW),5000,new InvokeMap(DUBLINE_VIEW));
    }

    private class InvokeMap implements GoogleMap.CancelableCallback{

        public CameraPosition target;

        public InvokeMap(CameraPosition target){
            this.target=target;
        }

        @Override
        public void onFinish() {
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //Do something after 4 s
                    m_map.animateCamera(CameraUpdateFactory.newCameraPosition(target),5000,null);
                }
            }, 4000);

        }

        @Override
        public void onCancel() {

        }
    }
}
