package com.example.gymproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ExercisesPage extends AppCompatActivity {

    private Workout workout;
    private Button addBtn;
    private DataBase dataBase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises_page);
        dataBase = DataBase.getInstance();
        addBtn = findViewById(R.id.add_workout_top_btn);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        final int position = intent.getIntExtra("position",0);
        workout = MyWorkoutPage.workouts.get(position);


        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        final List<Exercise> exercises = new ArrayList<>();

        exercises.add(new Exercise(ExercisesPage.this.getString(R.string.breeding_dumbbells_lying), R.drawable.breeding_dumbbells_lying));
        exercises.add(new Exercise(ExercisesPage.this.getString(R.string.climbs_the_trunk), R.drawable.climbs_the_trunk));
        exercises.add(new Exercise(ExercisesPage.this.getString(R.string.concentrated_bending_arms), R.drawable.concentrated_bending_arms));
        exercises.add(new Exercise(ExercisesPage.this.getString(R.string.curl_with_a_dumbbell), R.drawable.curl_with_a_dumbbell));
        exercises.add(new Exercise(ExercisesPage.this.getString(R.string.curls_on_the_bench), R.drawable.curls_on_the_bench));
        exercises.add(new Exercise(ExercisesPage.this.getString(R.string.dips), R.drawable.dips));
        exercises.add(new Exercise(ExercisesPage.this.getString(R.string.dumbbell_bench_press), R.drawable.dumbbell_bench_press));
        exercises.add(new Exercise(ExercisesPage.this.getString(R.string.bent_over_dumbbell_rows), R.drawable.end_dumbbell));
        exercises.add(new Exercise(ExercisesPage.this.getString(R.string.lat_pulldowens), R.drawable.end_of_the_upper_block_in_front_of_him));
        exercises.add(new Exercise(ExercisesPage.this.getString(R.string.lat_pulldowens_block_the_neck), R.drawable.end_of_the_upper_block_the_neck));
        exercises.add(new Exercise(ExercisesPage.this.getString(R.string.extension_arms_with_a_dumbbell), R.drawable.extension_arms_with_a_dumbbell));
        exercises.add(new Exercise(ExercisesPage.this.getString(R.string.tricep_push_down), R.drawable.extension_of_hands));
        exercises.add(new Exercise(ExercisesPage.this.getString(R.string.flexion_simulator_leg), R.drawable.flexion_simulator_leg));
        exercises.add(new Exercise(ExercisesPage.this.getString(R.string.bicep_free), R.drawable.inclined_leg));
        exercises.add(new Exercise(ExercisesPage.this.getString(R.string.leg_extension), R.drawable.leg_extension));
        exercises.add(new Exercise(ExercisesPage.this.getString(R.string.lunges_with_dumbbells), R.drawable.lunges_with_dumbbells));
        exercises.add(new Exercise(ExercisesPage.this.getString(R.string.middle_cable_fly), R.drawable.middle_cable_fly));
        exercises.add(new Exercise(ExercisesPage.this.getString(R.string.bench_press), R.drawable.press_of_a_bar));
        exercises.add(new Exercise(ExercisesPage.this.getString(R.string.pull_ups), R.drawable.pulling_up_wide_grip_hand));
        exercises.add(new Exercise(ExercisesPage.this.getString(R.string.pull_on_deltoids), R.drawable.pull_on_deltoids));
        exercises.add(new Exercise(ExercisesPage.this.getString(R.string.reduction_of_arms), R.drawable.reduction_of_arms));
        exercises.add(new Exercise(ExercisesPage.this.getString(R.string.run), R.drawable.run));
        exercises.add(new Exercise(ExercisesPage.this.getString(R.string.seated_chests), R.drawable.seated_chests));
        exercises.add(new Exercise(ExercisesPage.this.getString(R.string.standing_calf_raises), R.drawable.standing_calf_raises));
        exercises.add(new Exercise(ExercisesPage.this.getString(R.string.straightening_simulator_wheel), R.drawable.straightening_simulator_wheel));


        final int finalPosition = position;
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (Exercise ex : exercises) {
                    if (ex.isSelected()) {

                        workout.getExeList().put(ex.getName(),ex);
                    }
                    dataBase.addExercise(workout.getExeList(), workout.getName());
                }

                HashMap<String, Exercise> exeHashMap = workout.getExeList();
                Collection<Exercise> values = exeHashMap.values();
                ArrayList<Exercise> exeList = new ArrayList<Exercise>(values);
                WorkoutExercisesPage.setExercisesList(exeList);

                Intent intent = new Intent(ExercisesPage.this, WorkoutExercisesPage.class);
                intent.putExtra("position", finalPosition);
                startActivity(intent);
//                finish();
            }
        });


        ExerciseAdapter exerciseAdapter = new ExerciseAdapter((exercises));

        exerciseAdapter.setLisener(new ExerciseAdapter.ExercisesListener() {
            @Override
            public void onCheckBoxListener(int position) {
                exercises.get(position).setSelected(!exercises.get(position).isSelected());
            }
        });
        recyclerView.setAdapter(exerciseAdapter);

    }
}
