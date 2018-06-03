package com.example.android.quizapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView mfirstChoice;
    CheckBox checkBoxeRobert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
public void roberChoice(View v){
        checkBoxeRobert= findViewById(R.id.rober_check_box);
        boolean check=checkBoxeRobert.isChecked();
        if(!check)
        checkBoxeRobert.setChecked(true);
        else
            checkBoxeRobert.setChecked(false);
    }
    public void submitButton(View v){
    if(checkBoxeRobert.isChecked()){
       Toast mToast= Toast.makeText(this, "You are Right",Toast.LENGTH_LONG );
    mToast.show();
    }
    else{
        Toast mToast=Toast.makeText(this,"Sorry try again", Toast.LENGTH_SHORT);
        mToast.show();
    }

    }


}
