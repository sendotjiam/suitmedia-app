package com.sendo.suitmedia.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.sendo.suitmedia.R;
import com.sendo.suitmedia.utilities.Constant;

public class MainActivity extends AppCompatActivity {

    EditText etInputNama;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        etInputNama = findViewById(R.id.et_input_name);
    }

    public void next(View view) {
        if (validate()) {
            saveUserName();
            startActivity(new Intent(this, ChooseActivity.class));
            finish();
        }
        else etInputNama.setError("Masukkan nama");
    }

    private void saveUserName() {
        sharedPreferences = getSharedPreferences(Constant.SHARED_USER_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Constant.USER_NAME, etInputNama.getText().toString());
        editor.apply();
    }

    private boolean validate() {
        return !etInputNama.getText().toString().trim().isEmpty();
    }

}