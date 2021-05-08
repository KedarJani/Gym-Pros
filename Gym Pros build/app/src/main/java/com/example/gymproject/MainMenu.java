package com.example.gymproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class MainMenu extends AppCompatActivity {

    private LinearLayout myWorkoutBtn;
    private Button logoffBtn;
    LinearLayout weeklyActivityBtn;
    LinearLayout updateDetailsBtn;
    LinearLayout preferencesBtn;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);


//
////        RecyclerView recyclerView = findViewById(R.id.recycler);
////        recyclerView.setHasFixedSize(true);
////
////        recyclerView.setLayoutManager( new GridLayoutManager( this, 2));
////
////
////        List<CardMainMenu> cardMainMenuList = new ArrayList<>();
////
////        cardMainMenuList.add(new CardMainMenu("My Workout" , R.drawable.my_workouts_icon));
////        cardMainMenuList.add(new CardMainMenu("Weekly Activates" , R.drawable.weekly_activitys_icon));
////        cardMainMenuList.add(new CardMainMenu("Update My Details" , R.drawable.update_details_icon));
////        cardMainMenuList.add(new CardMainMenu("Preferences" , R.drawable.preferences_icon));
////
////
////        CardMainMenuAdapter CardMainMenuAdapter = new  CardMainMenuAdapter((cardMainMenuList));
////
////        recyclerView.setAdapter(CardMainMenuAdapter);




        weeklyActivityBtn = findViewById(R.id.weekly_activates_btn);

        weeklyActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainMenu.this, KarlMain.class);
                startActivity(intent);
            }
        });

        myWorkoutBtn = findViewById(R.id.my_workout_btn);

        myWorkoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, MyWorkoutPage.class );
                startActivity(intent);
            }
        });

        imageView = findViewById(R.id.steps);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, stepcounter.class );
                startActivity(intent);
            }
        });


        updateDetailsBtn = findViewById(R.id.update_details_btn);

        updateDetailsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, UpdateDetails.class);
                startActivity(intent);
            }
        });

        preferencesBtn = findViewById(R.id.preferences_btn);

        preferencesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, Settings.class);
                startActivity(intent);
            }
        });

    }

}
