package com.example.gymproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class Settings extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button logoffBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        logoffBtn = findViewById(R.id.logoff_btn);

        logoffBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(Settings.this,MainActivity.class);
                startActivity(intent);
            }
        });

        Spinner spinner1 = findViewById(R.id.spinner1);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.language, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);
        spinner1.setOnItemSelectedListener(this);

        String language_app = spinner1.getSelectedItem().toString();
        if(language_app!=null) {
            if (language_app.equals("Hebrew")) {
                //something..

            } else if (language_app.equals("English")) {
                //something...
            }
        }

        Spinner spinner2 = findViewById(R.id.spinner2);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.Weight, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(this);

        String weight_app = spinner2.getSelectedItem().toString();
        if(weight_app.equals("Kg")){
            //something..
        }
        else if(weight_app.equals("Lb")){
            //something...
        }



    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
