package com.sendo.suitmedia.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.sendo.suitmedia.R;
import com.sendo.suitmedia.adapters.EventAdapter;
import com.sendo.suitmedia.adapters.GuestAdapter;
import com.sendo.suitmedia.api.ApiClient;
import com.sendo.suitmedia.models.Guest;
import com.sendo.suitmedia.services.GuestApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class GuestActivity extends AppCompatActivity {

    RecyclerView rvGuest;
    TextView tvError;
    GuestAdapter guestAdapter;
    List<Guest> guestList = new ArrayList<>();
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest);
        getSupportActionBar().setTitle("GUEST");

        tvError = findViewById(R.id.tv_error);
        rvGuest = findViewById(R.id.rv_guest);
        rvGuest.setHasFixedSize(true);

        setUpProgressDialog();

        guestAdapter = new GuestAdapter(this);
        guestAdapter.setListGuestData(guestList);
        rvGuest.setLayoutManager(new GridLayoutManager(this, 2));
        rvGuest.setAdapter(guestAdapter);
        fetchApiData();
    }

    private void fetchApiData() {
        Retrofit retrofit = ApiClient.getRetrofit();
        GuestApiService guestApiService = retrofit.create(GuestApiService.class);
        Call<List<Guest>> call = guestApiService.getGuest();
        call.enqueue(new Callback<List<Guest>>() {
            @Override
            public void onResponse(Call<List<Guest>> call, Response<List<Guest>> response) {
                if (response.isSuccessful()) {
                    guestList = response.body();
                    for (int i = 0; i < guestList.size(); i++) {
                        guestList.get(i).setGuestImg(i+1);
                    }
                    guestAdapter.setListGuestData(guestList);
                }
            }
            @Override
            public void onFailure(Call<List<Guest>> call, Throwable t) {
                Toast.makeText(GuestActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setUpProgressDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(
                android.R.color.transparent
        );
        Runnable progressDialogRunnable = () -> progressDialog.cancel();
        Handler progresDialogHandler = new Handler();
        progresDialogHandler.postDelayed(progressDialogRunnable, 5000);
    }
}