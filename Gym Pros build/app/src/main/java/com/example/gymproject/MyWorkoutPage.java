package com.example.gymproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import static com.facebook.AccessTokenManager.TAG;


public class MyWorkoutPage extends AppCompatActivity {

    private HashMap<String, Exercise> exercises;
    DataBase dataBase;
    ImageView addWorkoutBtn;
    private ProgressBar progressBar;
    final static List<Workout> workouts = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_workout_page);

        dataBase = DataBase.getInstance();
        addWorkoutBtn = findViewById(R.id.add_workout_top_btn);
        progressBar = findViewById(R.id.workout_progress_bar);

        RecyclerView recyclerView = findViewById(R.id.recycler_workout);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager( new LinearLayoutManager( this));

        progressBar.setVisibility(View.VISIBLE);
        progressBar.getIndeterminateDrawable().setColorFilter(Color.WHITE, android.graphics.PorterDuff.Mode.MULTIPLY);

        addWorkoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyWorkoutPage.this, PopupAddWorkout.class));
            }
        });



        final MyWorkoutAdapter myWorkoutAdapter = new MyWorkoutAdapter((workouts));
        dataBase.getWorkouts(workouts, myWorkoutAdapter, new progressCallback() {
            @Override
            public void onFinish() {
                progressBar.setVisibility(View.GONE);
            }
        });


        myWorkoutAdapter.setListener(new MyWorkoutAdapter.MyWorkoutListener() {
            @Override
            public void onClickListener(int position) {
                HashMap<String, Exercise> exeHashMap = workouts.get(position).getExeList();
                //removeDeletedExercises(exeList);
                Collection<Exercise> values = exeHashMap.values();
                ArrayList<Exercise> exeList = new ArrayList<Exercise>(values);
                WorkoutExercisesPage.setExercisesList(exeList);
                Intent intent = new Intent(MyWorkoutPage.this, WorkoutExercisesPage.class);
                intent.putExtra("position", position);
                startActivity(intent);

            }
            @Override
            public void onWorkoutLongClicked(int position, View view, ImageView recycleBin) {

                if (recycleBin.getVisibility() == View.GONE) {
                    recycleBin.setVisibility((View.VISIBLE));
                } else {
                    recycleBin.setVisibility(View.GONE);
                }
            }

            @Override
            public void onDeleteListener(int position) {
                String name = workouts.get(position).getName();
                workouts.remove(position);
                myWorkoutAdapter.notifyItemRemoved(position);
                dataBase.removeWorkout(name);
                }
        });

        recyclerView.setAdapter(myWorkoutAdapter);
    }


    @Override
    public void onBackPressed() {
        Intent setIntent = new Intent(MyWorkoutPage.this,MainMenu.class);
        startActivity(setIntent);
        finish();
    }

    public interface progressCallback{
        void onFinish();
    }

}
