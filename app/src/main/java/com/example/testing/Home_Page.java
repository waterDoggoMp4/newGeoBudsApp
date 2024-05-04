package com.example.testing;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Home_Page extends AppCompatActivity implements OnMapReadyCallback{

    private GoogleMap gMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home_page);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        ImageButton post = (ImageButton) findViewById(R.id.PostButton);

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home_Page.this, Post_Page.class);
                startActivity(intent);
            }
        });

        ImageButton chat = (ImageButton) findViewById(R.id.ChatButton);

        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home_Page.this, Chat_Page.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        Log.d("Map", "onMapReady: Map is ready");

        LatLng markerLocation = new LatLng(0, 0);
        MarkerOptions markerOptions = new MarkerOptions()
                .position(markerLocation)
                .title("Post_1")
                .snippet("I'm here in the ocean");

        googleMap.addMarker(markerOptions);
    }

}