package com.example.VehiclesMap;

import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapOfVehicles extends FragmentActivity
        implements OnMapReadyCallback {
  ArrayList<LatLng> locationList;
  private MarkerOptions options = new MarkerOptions();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_map_of_vehicles);
    SupportMapFragment mapFragment = (SupportMapFragment)
            getSupportFragmentManager().findFragmentById(R.id.mapa);
    mapFragment.getMapAsync(this);

    locationList = getIntent().getParcelableArrayListExtra("locationList");

  }

  @Override
  public void onMapReady(GoogleMap googleMap) {
    for (LatLng point : locationList) {
      options.position(point);
      options.title("someTitle");
      options.snippet("someDesc");
      googleMap.addMarker(options);
      googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(point, 7));
    }

  }
}
