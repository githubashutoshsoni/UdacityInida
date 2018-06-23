package com.example.android.quizapplication;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    Button submitButton;
    CheckBox robertCheckBox;
    CheckBox madridCheckBox;
    CheckBox diameterOfEarthCheckbox;
    CheckBox bangladeshYearPartitionedCheckBox;
    CheckBox pakistanPartitionCheckBOx;
    CheckBox bangladeshCheckBox;
    CheckBox rodrigoCheckBox;
    CheckBox chrisCheckBox;
    CheckBox jamesCheckBox;
    CheckBox asturiasCheckBox;
    CheckBox navaCheckBox;
    CheckBox italyCheckBox;
    CheckBox wrong1CheckBox;
    CheckBox diameterOfmoonCheckBox;
    CheckBox wrong3CheckBox;
    CheckBox nineteenthirtyCheckBox;
    CheckBox ninetennthirtynineCheckBox;
    CheckBox bombayCheckBox;
    CheckBox delhiCheckBox;
    CheckBox chennaiCheckBox;
    int score;
    TextView scoreTextView;
    Resources mResources;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         initialize();

    }
    public void initialize(){
        submitButton= (Button) findViewById(R.id.submit_button);
        robertCheckBox=(CheckBox) findViewById(R.id.robert);
        madridCheckBox=(CheckBox) findViewById(R.id.madrid);
        diameterOfEarthCheckbox=(CheckBox) findViewById(R.id.diameter_of_earth);
        bangladeshYearPartitionedCheckBox=(CheckBox) findViewById(R.id.nineteen_thirty_seven);
        bangladeshCheckBox=(CheckBox) findViewById(R.id.bangladesh);
        rodrigoCheckBox=(CheckBox) findViewById(R.id.rodrigo);
        chrisCheckBox=(CheckBox) findViewById(R.id.chris);
        jamesCheckBox=(CheckBox) findViewById(R.id.james);
        asturiasCheckBox=(CheckBox) findViewById(R.id.asturias);
        navaCheckBox=(CheckBox) findViewById(R.id.navarre);
        italyCheckBox=(CheckBox) findViewById(R.id.italy);
        wrong1CheckBox=(CheckBox) findViewById(R.id.wrong1);
        diameterOfmoonCheckBox= (CheckBox) findViewById(R.id.diameter_of_moon);
        wrong3CheckBox=(CheckBox) findViewById(R.id.wrong3);
        nineteenthirtyCheckBox=(CheckBox) findViewById(R.id.nineteenthirty);//some random number
        ninetennthirtynineCheckBox=(CheckBox) findViewById(R.id.nineteenthirtynine);//some random number
        bombayCheckBox=(CheckBox) findViewById(R.id.bombay);
        delhiCheckBox=(CheckBox) findViewById(R.id.delhi);
        chennaiCheckBox=(CheckBox) findViewById(R.id.chennai);
        pakistanPartitionCheckBOx=(CheckBox) findViewById(R.id.nineteen_fourty_seven);
        mResources=getResources();
        scoreTextView=(TextView) findViewById(R.id.score);
    }

    public void submit(View v) {
        if (robertCheckBox.isChecked()) {
            score += 1;
            robertCheckBox.setBackgroundColor(mResources.getColor(R.color.green));
        } else if (rodrigoCheckBox.isChecked() || chrisCheckBox.isChecked() || jamesCheckBox.isChecked() || asturiasCheckBox.isChecked()) {
            if (rodrigoCheckBox.isChecked()) {
                rodrigoCheckBox.setBackgroundColor(mResources.getColor(R.color.red));
                chrisCheckBox.setBackgroundColor(mResources.getColor(R.color.red));
                jamesCheckBox.setBackgroundColor(mResources.getColor(R.color.red));
                asturiasCheckBox.setBackgroundColor(mResources.getColor(R.color.red));
            }
        }
        if (madridCheckBox.isChecked()&& italyCheckBox.isChecked()) {
            score += 1;
            madridCheckBox.setBackgroundColor(mResources.getColor(R.color.green));
            italyCheckBox.setBackgroundColor(mResources.getColor(R.color.green));
        } else {
            if (asturiasCheckBox.isChecked() || navaCheckBox.isChecked() ) {
                asturiasCheckBox.setBackgroundColor(mResources.getColor(R.color.red));
                navaCheckBox.setBackgroundColor(mResources.getColor(R.color.red));

            }

        }
        //tweleve thousand is the correct answer so it turns to green and score updates to 1
        if (diameterOfEarthCheckbox.isChecked()&&diameterOfmoonCheckBox.isChecked())
        {       diameterOfEarthCheckbox.setBackgroundColor(mResources.getColor(R.color.green));
            diameterOfmoonCheckBox.setBackgroundColor(mResources.getColor(R.color.green));
                score += 1;
        }
    else if(wrong1CheckBox.isChecked()||wrong3CheckBox.isChecked())
    {
        wrong3CheckBox.setBackgroundColor(mResources.getColor(R.color.red));
        
        wrong1CheckBox.setBackgroundColor(mResources.getColor(R.color.red));
    }
    if(bangladeshYearPartitionedCheckBox.isChecked() && pakistanPartitionCheckBOx.isChecked())
    {
        score+=1;
        bangladeshYearPartitionedCheckBox.setBackgroundColor(mResources.getColor(R.color.green));
        pakistanPartitionCheckBOx.setBackgroundColor(mResources.getColor(R.color.green));
    }
    else if (ninetennthirtynineCheckBox.isChecked()||nineteenthirtyCheckBox.isChecked())
        {
            ninetennthirtynineCheckBox.setBackgroundColor(mResources.getColor(R.color.red));

            nineteenthirtyCheckBox.setBackgroundColor(mResources.getColor(R.color.red));
        }
    if(bangladeshCheckBox.isChecked())
    {
        score+=1;
        bangladeshCheckBox.setBackgroundColor(mResources.getColor(R.color.green));
    }
    else if(bombayCheckBox.isChecked()||chennaiCheckBox.isChecked()||delhiCheckBox.isChecked()) {
        bombayCheckBox.setBackgroundColor(mResources.getColor(R.color.red));
        chennaiCheckBox.setBackgroundColor(mResources.getColor(R.color.red));
        delhiCheckBox.setBackgroundColor(mResources.getColor(R.color.red));
    }
    scoreTextView.setText(mResources.getText(R.string.score)+String.valueOf(score));
    }
//Reseting all the checkboxes to null.
    public void reset(View v)
    {
        robertCheckBox.setChecked(false);
        jamesCheckBox.setChecked(false);
        chrisCheckBox.setChecked(false);
        rodrigoCheckBox.setChecked(false);
        delhiCheckBox.setChecked(false);
        chennaiCheckBox.setChecked(false);
        bombayCheckBox.setChecked(false);
        bangladeshCheckBox.setChecked(false);
        nineteenthirtyCheckBox.setChecked(false);
        pakistanPartitionCheckBOx.setChecked(false);
        ninetennthirtynineCheckBox.setChecked(false);
        bangladeshYearPartitionedCheckBox.setChecked(false);
        wrong1CheckBox.setChecked(false);
        diameterOfmoonCheckBox.setChecked(false);
        wrong3CheckBox.setChecked(false);
        diameterOfEarthCheckbox.setChecked(false);
        italyCheckBox.setChecked(false);
        navaCheckBox.setChecked(false);
        madridCheckBox.setChecked(false);
        asturiasCheckBox.setChecked(false);
        score=0;

    }
}
