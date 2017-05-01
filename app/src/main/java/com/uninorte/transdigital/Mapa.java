package com.uninorte.transdigital;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class Mapa extends AppCompatActivity implements OnMapReadyCallback {
    double lat = 0;
    double lngi = 0;
    String ubicacion = "";
    private static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);
        lat = Double.parseDouble(getIntent().getStringExtra("latitud"));
        lngi = Double.parseDouble(getIntent().getStringExtra("longitud"));
        ubicacion = getIntent().getStringExtra("ubicacion");
        ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapa)).getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

            } else {

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);

            }
        }
        LatLng latLng = new LatLng(lat, lngi);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 17);
        googleMap.animateCamera(cameraUpdate);
        googleMap.setMyLocationEnabled(true);
        ubicacion=googleMap.getMyLocation().toString();
        googleMap.addMarker(new MarkerOptions().position(new LatLng(lat,lngi)).title("Aqui Estoy"));
    }

    public void end(View view) {
        Intent i = new Intent();
        i.putExtra("latitud",lat+"");
        i.putExtra("longitud",lngi+"");
        i.putExtra("ubicacion",ubicacion);
        setResult(Activity.RESULT_OK,i);
        finish();
    }
}
