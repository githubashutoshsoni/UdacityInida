package com.example.android.quizapplication;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    Button submitButton;
    CheckBox robertCheckBox;
    CheckBox madridCheckBox;
    CheckBox diameterOfEarthCheckbox;
    CheckBox bangladeshYearPartitionedCheckBox;
    CheckBox pakistanPartitionCheckBOx;
    RadioButton bangladeshRadioButton;
    CheckBox rodrigoCheckBox;
    CheckBox chrisCheckBox;
    CheckBox jamesCheckBox;
    CheckBox asturiasCheckBox;
    CheckBox navaCheckBox;
    CheckBox romeCheckBox;
    CheckBox wrong1CheckBox;
    CheckBox diameterOfmoonCheckBox;
    CheckBox wrong3CheckBox;
    CheckBox nineteenthirtyCheckBox;
    CheckBox ninetennthirtynineCheckBox;
    RadioButton bombayRadioButton;
    EditText esCheatsEditText;
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
        bangladeshRadioButton=(RadioButton) findViewById(R.id.bangladesh);
        rodrigoCheckBox=(CheckBox) findViewById(R.id.rodrigo);
        chrisCheckBox=(CheckBox) findViewById(R.id.chris);
        jamesCheckBox=(CheckBox) findViewById(R.id.james);
        asturiasCheckBox=(CheckBox) findViewById(R.id.asturias);
        navaCheckBox=(CheckBox) findViewById(R.id.navarre);
        romeCheckBox=(CheckBox) findViewById(R.id.rome);
        wrong1CheckBox=(CheckBox) findViewById(R.id.wrong1);
        diameterOfmoonCheckBox= (CheckBox) findViewById(R.id.diameter_of_moon);
        wrong3CheckBox=(CheckBox) findViewById(R.id.wrong3);
        nineteenthirtyCheckBox=(CheckBox) findViewById(R.id.nineteenthirty);//some random number
        ninetennthirtynineCheckBox=(CheckBox) findViewById(R.id.nineteenthirtynine);//some random number
        bombayRadioButton=(RadioButton) findViewById(R.id.bombay);
        pakistanPartitionCheckBOx=(CheckBox) findViewById(R.id.nineteen_fourty_seven);
        mResources=getResources();
        scoreTextView=(TextView) findViewById(R.id.score);
        esCheatsEditText=(EditText) findViewById(R.id.escheats);
    }

    public void submit(View v) {
        if (robertCheckBox.isChecked() && rodrigoCheckBox.isChecked() && !chrisCheckBox.isChecked() &&!jamesCheckBox.isChecked() && !asturiasCheckBox.isChecked()) {
            score += 1;
            robertCheckBox.setBackgroundColor(mResources.getColor(R.color.green));
        }
        if (madridCheckBox.isChecked()&& romeCheckBox.isChecked() && !navaCheckBox.isChecked() && !asturiasCheckBox.isChecked()) {
            score += 1;
            madridCheckBox.setBackgroundColor(mResources.getColor(R.color.green));
            romeCheckBox.setBackgroundColor(mResources.getColor(R.color.green));
        } else {
            if (asturiasCheckBox.isChecked() || navaCheckBox.isChecked() ) {
                asturiasCheckBox.setBackgroundColor(mResources.getColor(R.color.red));
                navaCheckBox.setBackgroundColor(mResources.getColor(R.color.red));

            }

        }
        //tweleve thousand is the correct answer so it turns to green and score updates to 1
        if (diameterOfEarthCheckbox.isChecked()&&diameterOfmoonCheckBox.isChecked() && !wrong1CheckBox.isChecked() && !wrong3CheckBox.isChecked())
        {       diameterOfEarthCheckbox.setBackgroundColor(mResources.getColor(R.color.green));
            diameterOfmoonCheckBox.setBackgroundColor(mResources.getColor(R.color.green));
                score += 1;
        }
    else if(wrong1CheckBox.isChecked()||wrong3CheckBox.isChecked())
    {
        wrong3CheckBox.setBackgroundColor(mResources.getColor(R.color.red));
        
        wrong1CheckBox.setBackgroundColor(mResources.getColor(R.color.red));
    }
    if(bangladeshYearPartitionedCheckBox.isChecked() && pakistanPartitionCheckBOx.isChecked() && !ninetennthirtynineCheckBox.isChecked() && !nineteenthirtyCheckBox.isChecked())
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
    if(bangladeshRadioButton.isChecked())
    {
        score+=1;
        bangladeshRadioButton.setBackgroundColor(mResources.getColor(R.color.green));
    }
    else if(bombayRadioButton.isChecked()) {
        bombayRadioButton.setBackgroundColor(mResources.getColor(R.color.red));

    }
    if(esCheatsEditText.getText().toString().equals("ESCHEATS")){
            score+=1;
    }
    scoreTextView.setText(mResources.getText(R.string.score)+String.valueOf(score));
        Toast mToast= Toast.makeText(this, "your score is"+ Integer.toString(score)+"/6",Toast.LENGTH_SHORT);
        mToast.show();

    }
//Reseting all the checkboxes to null.
    public void reset(View v)
    {
        robertCheckBox.setChecked(false);
        jamesCheckBox.setChecked(false);
        chrisCheckBox.setChecked(false);
        rodrigoCheckBox.setChecked(false);
        bombayRadioButton.setChecked(false);
        bangladeshRadioButton.setChecked(false);
        nineteenthirtyCheckBox.setChecked(false);
        pakistanPartitionCheckBOx.setChecked(false);
        ninetennthirtynineCheckBox.setChecked(false);
        bangladeshYearPartitionedCheckBox.setChecked(false);
        wrong1CheckBox.setChecked(false);
        diameterOfmoonCheckBox.setChecked(false);
        wrong3CheckBox.setChecked(false);
        diameterOfEarthCheckbox.setChecked(false);
        romeCheckBox.setChecked(false);
        navaCheckBox.setChecked(false);
        madridCheckBox.setChecked(false);
        asturiasCheckBox.setChecked(false);
        score=0;
        scoreTextView.setText(null);

    }
}
