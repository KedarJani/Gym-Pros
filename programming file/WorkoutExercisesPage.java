package com.example.gymproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.inputmethodservice.Keyboard;
import android.os.Bundle;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class WorkoutExercisesPage extends AppCompatActivity {

    private static List<Exercise> exercises;
    private ImageView addExeBtn;
    private String workoutName;
    private DataBase dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_exercises_page);

        dataBase = DataBase.getInstance();

        Intent intent = getIntent();

        final int position = intent.getIntExtra("position",0);
        workoutName = MyWorkoutPage.workouts.get(position).getName();


        TextView workoutNameTextView = findViewById(R.id.workout_name_header);

        workoutNameTextView.setText(workoutName);


        addExeBtn = findViewById(R.id.add_workout_exe_btn);

        final RecyclerView recyclerView = findViewById(R.id.recycler_workout_exercise);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager( new LinearLayoutManager( this));

        addExeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WorkoutExercisesPage.this, ExercisesPage.class);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });

        final WorkoutExerciseAdapter workoutExerciseAdapter = new WorkoutExerciseAdapter((exercises));

        workoutExerciseAdapter.setListener(new WorkoutExerciseAdapter.WorkoutExerciseListener() {


            @Override
            public void onCollapseClickListener(LinearLayout layout, ImageView arrowDown, ImageView arrowUp) {
                layout.setVisibility(View.GONE);
                arrowDown.setVisibility(View.VISIBLE);
                arrowUp.setVisibility(View.GONE);
            }

            @Override
            public void onExpendClickListener(LinearLayout layout, ImageView arrowDown, ImageView arrowUp) {

                layout.setVisibility(View.VISIBLE);
                arrowDown.setVisibility(View.GONE);
                arrowUp.setVisibility(View.VISIBLE);

            }

            @Override
            public void onWorkoutExerciseLongClicked(int position, View view, ImageView recycleBin) {

                if(recycleBin.getVisibility() == View.GONE){
                    recycleBin.setVisibility((View.VISIBLE));
                }
                else{
                    recycleBin.setVisibility(View.GONE);
                }
            }

            @Override
            public void onDeleteListener(int position) {
                dataBase.removeExe(workoutName, exercises.get(position).getName());
                exercises.remove(position);
                workoutExerciseAdapter.notifyItemRemoved(position);
                            }

            @Override
            public void onClickListener(ImageView img) {
                if (img.getVisibility() == View.VISIBLE)
                    img.setVisibility(View.GONE);
            }

            @Override
            public void onEditListener(Button editBtn, Button updateBtn, EditText weight, EditText reps, EditText sets) {
                editBtn.setVisibility(View.GONE);
                updateBtn.setVisibility(View.VISIBLE);
                weight.setInputType(InputType.TYPE_CLASS_NUMBER);
                reps.setInputType(InputType.TYPE_CLASS_NUMBER);
                sets.setInputType(InputType.TYPE_CLASS_NUMBER);
            }

            @Override
            public void onUpdateListener(Button editBtn, Button updateBtn, EditText weight, EditText reps, EditText sets, Exercise exercise) {
                updateBtn.setVisibility(View.GONE);
                editBtn.setVisibility(View.VISIBLE);
                weight.setInputType(InputType.TYPE_NULL);
                reps.setInputType(InputType.TYPE_NULL);
                sets.setInputType(InputType.TYPE_NULL);
                closeKeyboard();

                exercise.setWeight(Integer.parseInt(weight.getText().toString()));
                exercise.setReps(Integer.parseInt(reps.getText().toString()));
                exercise.setSets(Integer.parseInt(sets.getText().toString()));

                dataBase.updateExe(workoutName,exercise);


                Toast.makeText(WorkoutExercisesPage.this, WorkoutExercisesPage.this.getString(R.string.details_save), Toast.LENGTH_SHORT).show();

            }
        });



        recyclerView.setAdapter(workoutExerciseAdapter);

    }

    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static void setExercisesList(List<Exercise> exerciseList){
        exercises = new ArrayList<>(exerciseList);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (keyCode == KeyEvent.KEYCODE_BACK ) {
            Intent intent = new Intent(WorkoutExercisesPage.this, MyWorkoutPage.class);
            startActivity(intent);
            finish();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}
