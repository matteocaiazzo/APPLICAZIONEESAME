package com.androstock.myweatherapp;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng napoli = new LatLng(40.8359336, 14.2487826);
        LatLng boscodicapodimonte = new LatLng(40.8715738, 14.253335);
        LatLng virgiliano = new LatLng(40.798541, 14.1791104);
        LatLng mostraoltremare = new LatLng(40.8238024, 14.1925378);
        mMap.addMarker(new MarkerOptions().position(boscodicapodimonte).title("BOSCO DI CAPODIMONTE"));
        mMap.addMarker(new MarkerOptions().position(virgiliano).title("VIRGILIANO"));
        mMap.addMarker(new MarkerOptions().position(mostraoltremare).title("MOSTRA OLTREMARE"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(napoli));
        mMap.moveCamera(CameraUpdateFactory.zoomTo(5));
    }
}
