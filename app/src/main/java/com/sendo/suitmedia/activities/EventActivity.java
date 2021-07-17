package com.sendo.suitmedia.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.sendo.suitmedia.R;
import com.sendo.suitmedia.adapters.EventAdapter;
import com.sendo.suitmedia.models.Event;

import java.util.ArrayList;

public class EventActivity extends AppCompatActivity {

    RecyclerView rvEvent;
    ArrayList<Event> eventList = new ArrayList<>();
    EventAdapter eventAdapter;

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
}