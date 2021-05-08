package com.example.gymproject;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.List;


public class DataBase {

    private FirebaseAuth mAuth;
    private static DataBase instance;

    private DataBase() {
        mAuth = FirebaseAuth.getInstance();
    }

    public static DataBase getInstance() {

        if (instance == null) {
            instance = new DataBase();
        }
        return instance;
    }

    public void registerUserToDatabase(final String firstNameRegister, final String lastNameRegister, final String emailRegister, final String passRegister, final String rePassRegister, final RegisterPage page) {

        this.mAuth.createUserWithEmailAndPassword(emailRegister, passRegister)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            User user = new User(firstNameRegister, lastNameRegister, emailRegister, passRegister, rePassRegister);
                            DatabaseReference r = FirebaseDatabase.getInstance().getReference("Users");
                            r.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                page.progressBar.setVisibility(View.GONE);
                                                Toast.makeText(page, page.getString(R.string.successful_register), Toast.LENGTH_LONG).show();
                                                Intent intent = new Intent(page, DetailsPage.class);
                                                page.startActivity(intent);
                                            }
                                        }
                                    });
                        } else {
                            page.progressBar.setVisibility(View.GONE);
                            Toast.makeText(page, page.getString(R.string.register_failed), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    public void signInUser(final String email, final String password, final MainActivity page) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(page, MainMenu.class);
                            page.startActivity(intent);
                            page.finish();
                            //FirebaseUser user = mAuth.getCurrentUser();
                            // updateUI(user);
                        } else {
                            page.progressBar.setVisibility(View.GONE);
                            Toast success = Toast.makeText(page, page.getString(R.string.user_name_and_pass_wrong), Toast.LENGTH_LONG);
                            success.show();
                            // updateUI(null);
                        }


                    }

                });
    }


    public void addWorkout(Workout workout) {
        //   Workout workout = new Workout(workoutName,imgId);
        FirebaseUser userId = this.mAuth.getCurrentUser();
        updateUI(userId);
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users/" + userId.getUid() + "/Workouts");
        ref.child(workout.getName()).setValue(workout).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {

                }
            }
        });
    }

    public void addExercise(HashMap<String,Exercise> exercises, String workout) {
        FirebaseUser userId = this.mAuth.getCurrentUser();
        updateUI(userId);
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users/" + userId.getUid() + "/Workouts/" + workout + "/exeList");
        ref.setValue(exercises).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {

                }
            }
        });
    }

    public void MyDetails(MyDetails myDetails, final DetailsPage page) {
        FirebaseUser userId = this.mAuth.getCurrentUser();

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users/" + userId.getUid());
        ref.child("MyDetails").setValue(myDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Intent intent = new Intent(page, MainMenu.class);
                    page.startActivity(intent);
                }
            }
        });
    }


    public void updateMyDetails(MyDetails myDetails, final UpdateDetails page) {
        FirebaseUser userId = this.mAuth.getCurrentUser();

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users/" + userId.getUid());
        ref.child("MyDetails").setValue(myDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(page, page.getString(R.string.details_save), duration);
                    toast.show();
                    Intent intent = new Intent(page, MainMenu.class);
                    page.startActivity(intent);
                }
            }
        });
    }


    public void passwordResetMail(String emailAddress, final ForgotPasswordPage page) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        // String emailAddress = mAuth.getCurrentUser().getEmail();

        auth.sendPasswordResetEmail(emailAddress)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(page, MainActivity.class);
                            page.startActivity(intent);
                        } else {
                            Intent intent = new Intent(page, MainActivity.class);
                            page.startActivity(intent);
                        }
                    }
                });
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            user.getUid();

        }

    }

    public void removeWorkout(String workoutName) {
        FirebaseUser userId = mAuth.getCurrentUser();

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

        ref.child("Users/" + userId.getUid() + "/Workouts/" + workoutName).removeValue();

    }

    public void removeExe(String workoutName, String exeName) {
        FirebaseUser userId = mAuth.getCurrentUser();

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

        ref.child("Users/" + userId.getUid() + "/Workouts/" + workoutName + "/exeList/" + exeName).removeValue();

    }



    public void getWorkouts (final List<Workout> workouts, final MyWorkoutAdapter myWorkoutAdapter, final MyWorkoutPage.progressCallback callback){
        FirebaseUser userId = mAuth.getCurrentUser();
        workouts.clear();

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();


        ref.child("Users/" + userId.getUid() + "/Workouts").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Workout workout = dataSnapshot1.getValue(Workout.class);
                    workouts.add(workout);
                }
                synchronized (myWorkoutAdapter) {
                    myWorkoutAdapter.notifyDataSetChanged();
                }
                callback.onFinish();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void getMyDetails ( final MyDetails myDetails, final UpdateDetails updateDetails){

        FirebaseUser userId = mAuth.getCurrentUser();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

        ref.child("Users/" + userId.getUid() + "/MyDetails").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                MyDetails myDetails1 = dataSnapshot.getValue(MyDetails.class);
                myDetails.setAge(myDetails1.getAge());
                myDetails.setHeight(myDetails1.getHeight());
                myDetails.setWeight(myDetails1.getWeight());
                myDetails.setFat_percent(myDetails1.getFat_percent());
                myDetails.setAge(myDetails1.getAge());
                updateDetails.setValues();
                Log.d("New details:", Double.toString(myDetails.getAge()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void updateExe(String workoutName, Exercise exercise){
        FirebaseUser userId = this.mAuth.getCurrentUser();
        updateUI(userId);
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(
                "Users/" + userId.getUid() + "/Workouts/" + workoutName + "/exeList/" + exercise.getName());
        ref.setValue(exercise);


    }
}









