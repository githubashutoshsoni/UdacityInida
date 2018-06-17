package com.example.android.cricketipl;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    int out_mi = 0;
    int out_csk = 0;
    int csk_score = 0;
    int mi_score = 0;
    double overs_csk = 0;
    double overs_mi = 0;
    TextView csk_score_textView;
    TextView mi_score_textView;
    TextView csk_overs_textView;
    TextView mi_overs_textView;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void out_csk(View v) {

        out_csk += 1;
        String out = String.valueOf(out_csk);

        update_csk_overs();
        update_csk_score(0);

    }

    public void one_csk(View v) {
        update_csk_score(1);
        update_csk_overs();
    }

    public void update_csk_score(int score) {
        csk_score += score;
        csk_score_textView = (TextView) findViewById(R.id.score_csk);
        String increaseBy = String.valueOf(csk_score);
        csk_score_textView.setText("Score:" + increaseBy + "/" + out_csk);
    }


    public void update_csk_overs() {


        if (overs_csk % 10 == 6) {
            overs_csk += 4;
        } else {
            overs_csk += 1;
        }

        String string_overs_csk = String.valueOf(overs_csk / 10);
        csk_overs_textView = findViewById(R.id.csk_overs);
        csk_overs_textView.setText(string_overs_csk);
    }

    public void update_mi_overs() {
        double roundOff = (double) Math.round(overs_mi * 10) / 10;
        overs_mi = roundOff;

        if (overs_mi % 10 == 6) {
            overs_mi += 4;
        } else {
            overs_mi += 1;
        }

        String string_overs_mi = String.valueOf(overs_mi / 10);
        mi_overs_textView = findViewById(R.id.mi_overs);
        mi_overs_textView.setText(string_overs_mi);
    }

    public void update_mi_score(int score) {
        mi_score += score;
        mi_score_textView = (TextView) findViewById(R.id.score_mi);
        String increaseBy = String.valueOf(mi_score);
        mi_score_textView.setText("Score:" + increaseBy + "/" + out_mi);
    }


    public void four_csk(View v) {
        update_csk_score(4);
        update_csk_overs();
    }

    public void six_csk(View v) {
        update_csk_score(6);
        update_csk_overs();
    }

    //MI functions
    public void one_mi(View v) {
        update_mi_score(1);
        update_mi_overs();
    }

    public void four_mi(View v) {
        update_mi_score(4);
        update_mi_overs();
    }

    public void six_mi(View v) {
        update_mi_score(6);
        update_mi_overs();
    }

    public void out_mi(View v) {

        out_mi += 1;
        String out = String.valueOf(out_mi);


        update_mi_overs();
        update_mi_score(0);
    }

    public void result(View v) {
        result = findViewById(R.id.result_textView);
        if (mi_score > csk_score) {

            result.setText("The result is: " + "MI");
        } else if (mi_score < csk_score) {
            result.setText("The result is: " + "CSK");
        } else {
            result.setText("DRAW");
        }
    }

    public void resetButton(View v) {
        out_mi = 0;
        out_csk = 0;
        overs_mi = (double) 0;
        overs_csk = (double) 0;
        csk_score = 0;
        mi_score = 0;
        update_mi_score(0);
        update_csk_score(0);
        update_mi_overs();
        update_csk_overs();
    }

}
