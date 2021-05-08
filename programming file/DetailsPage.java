package com.example.gymproject;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

public class DetailsPage extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText editTextHeight;
    private EditText editTextWeight;
    private EditText editTextAge;
    private EditText editTextFatPercent;
    private DataBase dataBase;
    private ProgressBar progressBar;
    private static final String TAG = null;
    private Spinner spinner;
    private Button finishDetailsBtn;

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memeber_details_page);

        dataBase = DataBase.getInstance();
        editTextHeight = findViewById(R.id.height);
        editTextWeight = findViewById(R.id.weight);
        editTextAge = findViewById(R.id.age);
        editTextFatPercent = findViewById(R.id.percent_body_fat);
        progressBar = findViewById(R.id.progress_bar_details_page);
        finishDetailsBtn = findViewById(R.id.finish_details_btn);
        spinner = findViewById(R.id.spinner);
        progressBar.setVisibility(View.GONE);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Gender, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        finishDetailsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                updateDetails();

                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(DetailsPage.this, DetailsPage.this.getString(R.string.details_save), duration);
                toast.show();
            }
        });
    }


    public void updateDetails() {

        String heightCheck = editTextHeight.getText().toString().trim();
        String weightCheck = editTextWeight.getText().toString().trim();
        String ageCheck = editTextAge.getText().toString().trim();
        String fatPercentCheck = editTextFatPercent.getText().toString().trim();
        String gender = spinner.getSelectedItem().toString();
        String selectedGender = DetailsPage.this.getString(R.string.female_text);

        if(gender.equals(selectedGender)){
            gender = "Female";
        }
        else {
            gender = "Male";
        }

        if(heightCheck.isEmpty()){
            editTextHeight.setError(DetailsPage.this.getString(R.string.height_rquiered));
            editTextHeight.requestFocus();
            progressBar.setVisibility(View.GONE);
            return;
        }

        if(heightCheck.length() > 25){
            editTextHeight.setError(DetailsPage.this.getString(R.string.string_length));
            editTextHeight.requestFocus();
            progressBar.setVisibility(View.GONE);
            return;
        }

        if(weightCheck.isEmpty()){
            editTextWeight.setError(DetailsPage.this.getString(R.string.weight_rquiered));
            editTextWeight.requestFocus();
            progressBar.setVisibility(View.GONE);
            return;
        }

        if(weightCheck.length() > 25){
            editTextWeight.setError(DetailsPage.this.getString(R.string.string_length));
            editTextWeight.requestFocus();
            progressBar.setVisibility(View.GONE);
            return;
        }

        if(ageCheck.isEmpty()){
            editTextAge.setError(DetailsPage.this.getString(R.string.age_requiered));
            editTextAge.requestFocus();
            progressBar.setVisibility(View.GONE);
            return;
        }

        if(ageCheck.length() > 25){
            editTextAge.setError(DetailsPage.this.getString(R.string.string_length));
            editTextAge.requestFocus();
            progressBar.setVisibility(View.GONE);
            return;
        }

        if(fatPercentCheck.isEmpty()){
            editTextFatPercent.setError(DetailsPage.this.getString(R.string.fat_percent_rquierd));
            editTextFatPercent.requestFocus();
            progressBar.setVisibility(View.GONE);
            return;
        }

        if(fatPercentCheck.length() > 25){
            editTextFatPercent.setError(DetailsPage.this.getString(R.string.string_length));
            editTextFatPercent.requestFocus();
            progressBar.setVisibility(View.GONE);
            return;
        }

        double height = Double.parseDouble(editTextHeight.getText().toString().trim());
        double weight = Double.parseDouble(editTextWeight.getText().toString().trim());
        double age = Double.parseDouble(editTextAge.getText().toString().trim());
        double fatPercent = Double.parseDouble(editTextFatPercent.getText().toString().trim());

        MyDetails myDetails = new MyDetails(height,weight,age,fatPercent,gender);
        dataBase.MyDetails(myDetails,this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
      //  Toast.makeText(parent.getContext(),text,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}





