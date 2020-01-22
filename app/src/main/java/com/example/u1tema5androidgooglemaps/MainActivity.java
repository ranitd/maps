package com.example.u1tema5androidgooglemaps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

  public void mimapa(View view) {
    startActivity(new Intent(this,MiMapa.class));
  }

  public void PolyLine(View view) {
    startActivity(new Intent(this,MiPolyLine.class));
  }
}
