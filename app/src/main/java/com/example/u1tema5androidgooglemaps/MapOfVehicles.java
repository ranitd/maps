package com.example.u1tema5androidgooglemaps;

import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MiMapa extends FragmentActivity
        implements OnMapReadyCallback/*, GoogleMap.OnMapClickListener, GoogleMap.OnInfoWindowLongClickListener*/ {
  //GoogleMap mapa;
  //LatLng ubicacion;
 // ArrayList<Marker> markers1 = new ArrayList<>();
  //ArrayList<LatLng> locations;
  //List<Marker> markerList = new ArrayList<>();
  ArrayList<LatLng> locationList;
  private MarkerOptions options = new MarkerOptions();
  String data;
  /*ArrayList<MarkerData> markersArray = new ArrayList<MarkerData>();

for(int i = 0 ; i < markersArray.size() ; i++ ) {

    createMarker(markersArray.get(i).getLatitude(), markersArray.get(i).getLongitude(), markersArray.get(i).getTitle(), markersArray.get(i).getSnippet(), markersArray.get(i).getIconResID());
  }*/

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_mi_mapa);
// Obtenemos el mapa de forma asíncrona (notificará cuando esté listo)
    SupportMapFragment mapFragment = (SupportMapFragment)
            getSupportFragmentManager().findFragmentById(R.id.mapa);
    mapFragment.getMapAsync(this);
   // locationList = getIntent().getParcelableArrayListExtra("locationList");
    //Intent intent = getIntent();
   // Bundle b=intent.getExtras();

    locationList = getIntent().getParcelableArrayListExtra("locationList");
  //  locations=intent.getParcelableArrayListExtra("location");
   // locations1=intent.getCharSequenceArrayListExtra("location1");
  }

  @Override
  public void onMapReady(GoogleMap googleMap) {
   // mapa = googleMap;
   // LatLng location1=locationList.get(0);
    for (LatLng point :locationList ) {
      options.position(point);
      options.title("someTitle");
      options.snippet("someDesc");
      googleMap.addMarker(options);
      googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(point,4));
    }






   // LatLng location2=locationList.get(1);
   // mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location1.latitude,location1.longitude),4));
  //  mapa.addMarker(new MarkerOptions().position(new LatLng(location1.latitude,location1.longitude)).draggable(true));
   // mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location2.latitude,location2.longitude),15));
   // mapa.addMarker(new MarkerOptions().position(new LatLng(location2.latitude,location2.longitude)).draggable(true));
   /* for(LatLng location:locationList) {

      MarkerOptions markerOptions = new MarkerOptions().position(new LatLng(location.latitude,location.longitude)).draggable(true);
      mapa.addMarker(markerOptions);
      Marker marker = mapa.addMarker(markerOptions);
      //locationList.add(latLng);
      markerList.add(marker);

      //mapa.addMarker(new MarkerOptions().position(new LatLng(location.latitude,location.longitude)).draggable(true));
      mapa.moveCamera(CameraUpdateFactory.newLatLng(location));
      //mapa.setOnMapClickListener(this);
      }*/
    //mapa.setOnMarkerDragListener(this);
    //ubicacion = new LatLng(-18.013766, -70.255331); //Nos ubicamos en la UNJBG
    //mapa.addMarker(new MarkerOptions().position(ubicacion).title("Marcador Tacna"));
  //  mapa.moveCamera(CameraUpdateFactory.newLatLng(ubicacion));
   // mapa.setOnMapClickListener(this);

    //cuanto mantienes precionado el marcador se elimina
   /* mapa.setOnInfoWindowLongClickListener(new GoogleMap.OnInfoWindowLongClickListener() {
      @Override
      public void onInfoWindowLongClick(Marker marker) {
        marker.remove();
      }
    });*/

   // mapa.setOnInfoWindowLongClickListener(this);

  }
 /* public void moveCamera(View view) {
    mapa.moveCamera(CameraUpdateFactory.newLatLng(ubicacion));
  }*/
  /*public void addMarker(View view) {
    mapa.addMarker(new MarkerOptions()
            .position(mapa.getCameraPosition().target)
            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
            .draggable(true));
  }*/
  /*@Override public void onMapClick(LatLng puntoPulsado) {
    markers1.add(mapa.addMarker(new MarkerOptions()
            .position(puntoPulsado)
            .icon(BitmapDescriptorFactory.fromResource(R.mipmap.markermap))
            .title("Marker onMapClick")
            .snippet("Este marker es producto del evento de pulsar en el mapa")));
  }*/

  /*@Override
  public void onInfoWindowLongClick(Marker marker) {
    marker.remove();
  }*/

 /* public void eliminar(View view) {

    if(markers1 != null && markers1.size() != 0){
      markers1.get(markers1.size()-1).remove();
      markers1.remove(markers1.size()-1);
    }
  }*/
}
