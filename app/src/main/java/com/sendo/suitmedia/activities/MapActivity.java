package com.sendo.suitmedia.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.sendo.suitmedia.R;
import com.sendo.suitmedia.adapters.EventAdapter;
import com.sendo.suitmedia.models.Event;
import com.sendo.suitmedia.models.Location;
import com.sendo.suitmedia.utilities.Constant;

import java.util.ArrayList;
import java.util.List;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap map;

    LatLng jakarta;
    LatLng bekasi;
    LatLng tangerang;
    LatLng southJakarta;
    private ArrayList<Location> locationList = new ArrayList<>();

    RecyclerView rvEvent;
    EventAdapter eventAdapter;
    List<Event> eventList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        getSupportActionBar().setTitle("MAP EVENT");

        eventList = (List<Event>) getIntent().getSerializableExtra(Constant.EVENT_LIST);
        storeLocationListData();

        rvEvent = findViewById(R.id.rv_event);

        setUpAdapter();

        setUpFragment();
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map = googleMap;
        for (int i = 0; i < locationList.size(); i++) {
            Location location = locationList.get(i);
            MarkerOptions markerOptions = new MarkerOptions()
                    .position(location.getLatLng())
                    .title(location.getName())
                    .draggable(false);
            map.addMarker(markerOptions);
            map.moveCamera(CameraUpdateFactory.zoomTo(10.0f));
            map.moveCamera(CameraUpdateFactory.newLatLng(location.getLatLng()));
        }
    }

    private void setUpFragment() {
        SupportMapFragment supportMapFragment = SupportMapFragment.newInstance();
        supportMapFragment.getMapAsync(this);
        getSupportFragmentManager().beginTransaction().add(R.id.frame, supportMapFragment).commit();
    }

    private void setUpAdapter() {
        eventAdapter = new EventAdapter(this);
        eventAdapter.setListEventData((ArrayList<Event>) eventList);

        rvEvent.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvEvent.setAdapter(eventAdapter);
    }

    private void storeLocationListData() {
        jakarta = new LatLng(-6.200000, 106.816666);
        bekasi = new LatLng(-6.241586, 106.992416);
        tangerang = new LatLng(-6.178306, 106.631889);
        southJakarta = new LatLng(-6.378306, 106.814095);

        locationList.add(new Location(jakarta, "Jakarta"));
        locationList.add(new Location(bekasi, "Bekasi"));
        locationList.add(new Location(tangerang, "Tangerang"));
        locationList.add(new Location(southJakarta, "South Jakarta"));
    }
}