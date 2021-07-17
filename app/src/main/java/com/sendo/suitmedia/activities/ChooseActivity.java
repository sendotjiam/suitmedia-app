package com.sendo.suitmedia.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sendo.suitmedia.R;
import com.sendo.suitmedia.utilities.Constant;

public class ChooseActivity extends AppCompatActivity {

    TextView tvName;
    String name;
    Button btnEvent, btnGuest;

    SharedPreferences sharePreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        getSupportActionBar().hide();

        initialize();
        loadUserName();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setEventName();
        setGuestName();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        clearSharedPreferencesData();
    }

    private void clearSharedPreferencesData() {
        SharedPreferences eventSharedPref = getSharedPreferences(Constant.SHARED_EVENT_NAME, MODE_PRIVATE);
        SharedPreferences.Editor eventEditor = eventSharedPref.edit();
        eventEditor.clear();
        eventEditor.apply();

        SharedPreferences guestSharedPref = getSharedPreferences(Constant.SHARED_GUEST_NAME, MODE_PRIVATE);
        SharedPreferences.Editor guestEditor = guestSharedPref.edit();
        guestEditor.clear();
        guestEditor.apply();

        SharedPreferences userSharedPref = getSharedPreferences(Constant.SHARED_USER_NAME, MODE_PRIVATE);
        SharedPreferences.Editor userEditor = userSharedPref.edit();
        userEditor.clear();
        userEditor.apply();
    }

    private void loadUserName() {
        name = loadSharedPref(Constant.SHARED_USER_NAME, Constant.USER_NAME);
        if (!name.equals("")) tvName.setText(name);
    }

    private void setEventName() {
        String eventName = loadSharedPref(Constant.SHARED_EVENT_NAME, Constant.EVENT_NAME);
        if (!eventName.equals("")) btnEvent.setText(eventName);
        else btnEvent.setText("Pilih Event");
    }

    private void setGuestName() {
        String guestName = loadSharedPref(Constant.SHARED_GUEST_NAME, Constant.GUEST_NAME);
        if (!guestName.equals("")) btnGuest.setText(guestName);
        else btnGuest.setText("Pilih Guest");
    }

    private String loadSharedPref(String sharePrefKey, String dataKey) {
        sharePreferences = getSharedPreferences(sharePrefKey, MODE_PRIVATE);
        return sharePreferences.getString(dataKey, "");
    }

    private void initialize() {
        tvName = findViewById(R.id.tv_name);
        btnEvent = findViewById(R.id.btn_choose_event);
        btnGuest = findViewById(R.id.btn_choose_guest);
    }

    public void moveToEvent(View view) {
        startActivity(new Intent(this, EventActivity.class));
    }
    public void moveToGuest(View view) {
        startActivity(new Intent(this, GuestActivity.class));
    }

}