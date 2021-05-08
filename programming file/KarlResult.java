package com.example.gymproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class KarlResult extends AppCompatActivity {
    TextView bmrValue,tdeeValue,bmiValue,bmiDetail,tryingToView;
    double bmi=0.0,uWeight=0.0;
    int bmr=0,tdee=0,inCalories=0;
    String ttmsg="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_karl_result);


        bmrValue = (TextView) findViewById(R.id.bmrValue);
        tdeeValue = (TextView) findViewById(R.id.tdeeValue);
        bmiValue = (TextView) findViewById(R.id.bmiValue);
        bmiDetail = (TextView) findViewById(R.id.bmiDetail);
        tryingToView = (TextView) findViewById(R.id.tryingToView);

        // get data from intent
        Intent intentExtras = getIntent();
        Bundle extrasBundle = intentExtras.getExtras();
        if(extrasBundle != null){

            bmr = (int)extrasBundle.getDouble("bmr");
            tdee = (int)extrasBundle.getDouble("tdee");
            bmi = extrasBundle.getDouble("bmi");
            uWeight = Math.round(extrasBundle.getDouble("weight")*100.0)/100.0;
            ttmsg = extrasBundle.getString("tt");


        }

        String msg;
        if (bmi < 18.5) {
            msg = "Under Weight";
        } else if (bmi < 24.9) {
            msg =  "Normal Weight";
        } else if (bmi < 29.9) {
            msg = "Over Weight";
        } else if (bmi < 40) {
            msg = "Obese";
        } else {
            msg = "an inhuman";
            bmi=0.0;
        }

        String ttmsg_new;

        if (ttmsg.equals("Loose weight")){
            inCalories = tdee - 500;
            if(inCalories<bmr) inCalories=bmr;
            ttmsg_new  = "To loose weight you should limit your intake to " + inCalories + " calories";
        }else if (ttmsg.equals("Gain weight")){
            inCalories = tdee + 500;
            ttmsg_new = "To gain weight you should increase your intake to " + inCalories + " calories";
        }else if (ttmsg.equals("Maintain weight")){
            inCalories=tdee;
            ttmsg_new = "To maintain weight your intake should be " + inCalories + " calories";
        } else
            ttmsg_new = "Something went wrong";

        // populate results
        bmrValue.setText(""+bmr);
        tdeeValue.setText(""+tdee);
        bmiValue.setText(""+bmi);
        bmiDetail.setText(msg);
        tryingToView.setText(ttmsg_new);
    }
}
