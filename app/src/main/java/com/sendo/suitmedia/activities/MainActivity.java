package com.sendo.suitmedia.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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
            isPalindrome();
            saveUserName();
        }
        else etInputNama.setError("Masukkan nama");
    }

    private void isPalindrome() {
        String input = etInputNama.getText().toString().trim();
        StringBuilder inputReverse = new StringBuilder();
        boolean isPalindrome;
        for (int i =  input.length() - 1; i >= 0; --i) {
            inputReverse.append(input.charAt(i));
        }
        if (input.equals(inputReverse.toString())) {
            isPalindrome = true;
        } else isPalindrome = false;
        showIsPalindromeDialog(isPalindrome);
    }

    private void showIsPalindromeDialog(boolean isPalindrome) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Palindrome Name")
                .setMessage(isPalindrome ? "is Palindrome" : "not Palindrome")
                .setCancelable(false)
                .setPositiveButton("NEXT", (dialogInterface, i) -> {
                    startActivity(new Intent(MainActivity.this, ChooseActivity.class));
                    finish();
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

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