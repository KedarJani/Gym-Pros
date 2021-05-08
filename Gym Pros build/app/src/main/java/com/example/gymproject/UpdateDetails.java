package com.example.gymproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

public class UpdateDetails extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText editTextHeight;
    private EditText editTextWeight;
    private EditText editTextAge;
    private EditText editTextFatPercent;
    private ProgressBar progressBar;
    private Button finishNewDetailsBtn;
    private Spinner spinner;
    private DataBase dataBase;
    private MyDetails myDetails;

    @Override
    protected void onStart() {
        super.onStart();
        myDetails = new MyDetails();
        setValues();
        dataBase.getMyDetails(myDetails,this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_details);

        editTextHeight = findViewById(R.id.new_height);
        editTextAge = findViewById(R.id.new_Age);
        editTextWeight = findViewById(R.id.new_Weight);
        editTextFatPercent = findViewById(R.id.new_Percent_body_fat);
        progressBar = findViewById(R.id.new_details_progressbar);
        finishNewDetailsBtn = findViewById(R.id.finish_new_details_btn);
        spinner = findViewById(R.id.new_details_spinner);
        dataBase = DataBase.getInstance();

        progressBar.setVisibility(View.GONE);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Gender, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        finishNewDetailsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                updateDetails();
            }

        });
}

    public void setValues(){
    editTextHeight.setText(Double.toString(myDetails.getHeight()));
    editTextWeight.setText(Double.toString(myDetails.getWeight()));
    editTextAge.setText(Double.toString(myDetails.getAge()));
    editTextFatPercent.setText(Double.toString(myDetails.getFat_percent()));
}
    public void updateDetails() {

        String heightCheck = editTextHeight.getText().toString().trim();
        String weightCheck = editTextWeight.getText().toString().trim();
        String ageCheck = editTextAge.getText().toString().trim();
        String fatPercentCheck = editTextFatPercent.getText().toString().trim();
        String gender = spinner.getSelectedItem().toString();
        String selectedGender = UpdateDetails.this.getString(R.string.female_text);

        if (gender.equals(selectedGender)) {
            gender = "Female";
        } else {
            gender = "Male";
        }

        if (heightCheck.isEmpty()) {
            editTextHeight.setError(UpdateDetails.this.getString(R.string.height_rquiered));
            editTextHeight.requestFocus();
            progressBar.setVisibility(View.GONE);
            return;
        }

        if (heightCheck.length() > 25) {
            editTextHeight.setError(UpdateDetails.this.getString(R.string.string_length));
            editTextHeight.requestFocus();
            progressBar.setVisibility(View.GONE);
            return;
        }

        if (weightCheck.isEmpty()) {
            editTextWeight.setError(UpdateDetails.this.getString(R.string.weight_rquiered));
            editTextWeight.requestFocus();
            progressBar.setVisibility(View.GONE);
            return;
        }

        if (weightCheck.length() > 25) {
            editTextWeight.setError(UpdateDetails.this.getString(R.string.string_length));
            editTextWeight.requestFocus();
            progressBar.setVisibility(View.GONE);
            return;
        }

        if (ageCheck.isEmpty()) {
            editTextAge.setError(UpdateDetails.this.getString(R.string.age_requiered));
            editTextAge.requestFocus();
            progressBar.setVisibility(View.GONE);
            return;
        }

        if (ageCheck.length() > 25) {
            editTextAge.setError(UpdateDetails.this.getString(R.string.string_length));
            editTextAge.requestFocus();
            progressBar.setVisibility(View.GONE);
            return;
        }

        if (fatPercentCheck.isEmpty()) {
            editTextFatPercent.setError(UpdateDetails.this.getString(R.string.fat_percent_rquierd));
            editTextFatPercent.requestFocus();
            progressBar.setVisibility(View.GONE);
            return;
        }

        if (fatPercentCheck.length() > 25) {
            editTextFatPercent.setError(UpdateDetails.this.getString(R.string.string_length));
            editTextFatPercent.requestFocus();
            progressBar.setVisibility(View.GONE);
            return;
        }

        double height = Double.parseDouble(editTextHeight.getText().toString().trim());
        double weight = Double.parseDouble(editTextWeight.getText().toString().trim());
        double age = Double.parseDouble(editTextAge.getText().toString().trim());
        double fatPercent = Double.parseDouble(editTextFatPercent.getText().toString().trim());

        MyDetails myDetails = new MyDetails(height, weight, age, fatPercent, gender);
        dataBase.updateMyDetails(myDetails, this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}