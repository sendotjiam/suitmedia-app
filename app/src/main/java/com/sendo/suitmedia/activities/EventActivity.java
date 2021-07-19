package com.sendo.suitmedia.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.sendo.suitmedia.R;
import com.sendo.suitmedia.adapters.EventAdapter;
import com.sendo.suitmedia.models.Event;
import com.sendo.suitmedia.utilities.Constant;

import java.util.ArrayList;

public class EventActivity extends AppCompatActivity {

    RecyclerView rvEvent;
    ArrayList<Event> eventList = new ArrayList<>();
    EventAdapter eventAdapter;
    Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        getSupportActionBar().setTitle("EVENT");
        initialize();

        rvEvent.setHasFixedSize(true);

        eventAdapter = new EventAdapter(this);
        eventAdapter.setListEventData(eventList);
        rvEvent.setLayoutManager(new LinearLayoutManager(this));
        rvEvent.setAdapter(eventAdapter);
    }

    private void initialize() {
        rvEvent = findViewById(R.id.rv_event);
        eventList = Event.getListEventData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        fragment = null;
        if (item.getItemId() == R.id.open_map) {
            Intent intent = new Intent(this, MapActivity.class);
            intent.putExtra(Constant.EVENT_LIST, eventList);
            startActivity(intent);
        }
        return true;
//        rvEvent.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
//        rvEvent.setAdapter(eventAdapter);
    }
}