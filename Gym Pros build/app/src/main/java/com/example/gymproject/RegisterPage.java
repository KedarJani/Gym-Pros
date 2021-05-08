package com.example.gymproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.core.view.GravityCompat;

import java.util.Locale;



public class RegisterPage extends MainActivity {

    //  EditText pass;
    //   EditText repPass;
    DataBase dataBase;
    ProgressBar progressBar;

    private EditText editTextfirstNameRegister;
    private EditText editTextlastNameRegister;
    private EditText editTextEmailRegister;
    private EditText editTextPassRegister;
    private EditText editTextRepPasswordRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        dataBase = DataBase.getInstance();


        editTextfirstNameRegister = findViewById(R.id.first_name_register);
        editTextlastNameRegister = findViewById(R.id.last_name_register);
        editTextEmailRegister = findViewById(R.id.email_register);
        editTextPassRegister = findViewById(R.id.pass_register);
        editTextRepPasswordRegister = findViewById(R.id.rep_pass_register);


        Button registerBtnEnd = findViewById(R.id.reg_btn_end);
        progressBar = findViewById(R.id.progress_bar_reg_btn);
        progressBar.setVisibility(View.GONE);

        lngCheckRegPage();


        registerBtnEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

        Button clickHereBtn = findViewById(R.id.clickHereDetails_btn);

        clickHereBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterPage.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }


    public void lngCheckRegPage() {
        String lng = Locale.getDefault().getDisplayLanguage();

        if (lng.equals("English")) {
               editTextPassRegister.setGravity(GravityCompat.START);
               editTextRepPasswordRegister.setGravity(GravityCompat.START);
        } else {
            editTextPassRegister.setGravity(GravityCompat.END);
            editTextRepPasswordRegister.setGravity(GravityCompat.END);
        }
    }

    public void registerUser() {
        final String email = editTextEmailRegister.getText().toString().trim();
        final String pass = editTextPassRegister.getText().toString().trim();
        final String firstName = editTextfirstNameRegister.getText().toString().trim();
        final String lastName = editTextlastNameRegister.getText().toString().trim();
        final String rePass = editTextRepPasswordRegister.getText().toString().trim();

            progressBar.setVisibility(View.VISIBLE);

        if (firstName.isEmpty()) {
            editTextfirstNameRegister.setError(RegisterPage.this.getString(R.string.first_name_required));
            editTextfirstNameRegister.requestFocus();
            progressBar.setVisibility(View.GONE);
            return;
        }

        if(firstName.length() > 25){
            editTextfirstNameRegister.setError(RegisterPage.this.getString(R.string.string_length));
            editTextfirstNameRegister.requestFocus();
            progressBar.setVisibility(View.GONE);
            return;
        }


        if (lastName.isEmpty()) {
            editTextlastNameRegister.setError(RegisterPage.this.getString(R.string.last_name_required));
            editTextlastNameRegister.requestFocus();
            progressBar.setVisibility(View.GONE);
            return;
        }

        if(lastName.length() > 25){
            editTextlastNameRegister.setError(RegisterPage.this.getString(R.string.string_length));
            editTextlastNameRegister.requestFocus();
            progressBar.setVisibility(View.GONE);
            return;
        }


        if (email.isEmpty()) {
            editTextEmailRegister.setError(RegisterPage.this.getString(R.string.email_required));
            editTextEmailRegister.requestFocus();
            progressBar.setVisibility(View.GONE);
            return;
        }


        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmailRegister.setError(RegisterPage.this.getString(R.string.valid_email));
            editTextEmailRegister.requestFocus();
            progressBar.setVisibility(View.GONE);
            return;
        }
        if (pass.length() < 6 ) {
            editTextPassRegister.setError(RegisterPage.this.getString(R.string.password_length));
            editTextPassRegister.requestFocus();
            progressBar.setVisibility(View.GONE);
            return;
        }

        if (pass.length() > 25 ) {
            editTextPassRegister.setError(RegisterPage.this.getString(R.string.string_length));
            editTextPassRegister.requestFocus();
            progressBar.setVisibility(View.GONE);
            return;
        }

        if (rePass.length() < 6) {
            editTextRepPasswordRegister.setError(RegisterPage.this.getString(R.string.password_length));
            editTextRepPasswordRegister.requestFocus();
            progressBar.setVisibility(View.GONE);
            return;
        }

        if (rePass.length() > 25 ) {
            editTextPassRegister.setError(RegisterPage.this.getString(R.string.string_length));
            editTextPassRegister.requestFocus();
            progressBar.setVisibility(View.GONE);
            return;
        }

        if (!rePass.equals(pass)) {
            editTextRepPasswordRegister.setError(RegisterPage.this.getString(R.string.pass_not_match));
            editTextRepPasswordRegister.requestFocus();
            progressBar.setVisibility(View.GONE);
            return;
        }

        dataBase.registerUserToDatabase(firstName, lastName, email, pass, rePass, this);
    }
}
