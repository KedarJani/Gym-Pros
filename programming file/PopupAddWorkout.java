package com.example.gymproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class PopupAddWorkout extends AppCompatActivity {
    private EditText workoutName;
    private int imageId;
    private Button addWorkoutBtn;
    private RadioButton aBtn;
    private RadioButton bBtn;
    private RadioButton cBtn;
    private RadioButton dBtn;
    private RadioButton eBtn;
    DataBase dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_add_workout);

        workoutName = findViewById(R.id.new_workout_name);
        addWorkoutBtn = findViewById(R.id.add_workout_btn);

        dataBase = DataBase.getInstance();

        aBtn = findViewById(R.id.a_workout);
        bBtn = findViewById(R.id.b_workout);
        cBtn = findViewById(R.id.c_workout);
        dBtn = findViewById(R.id.d_workout);
        eBtn = findViewById(R.id.e_workout);



        aBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageId = R.drawable.a_img;
            }
        });

        bBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageId = R.drawable.b_img;
            }
        });

        cBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageId = R.drawable.c_img;
            }
        });

        dBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageId = R.drawable.d_img;
            }
        });

        eBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageId = R.drawable.e_img;
            }
        });

        addWorkoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = workoutName.getText().toString();
                Workout workout = new Workout(name, imageId);
                dataBase.addWorkout(workout);
                Intent intent = new Intent(PopupAddWorkout.this, MyWorkoutPage.class);
                startActivity(intent);
                finish();
            }
        });


        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;


        getWindow().setLayout((int)(width*.9), (int)(height*.7));
    }

    public void setFocus(ImageButton button){

    }
}
