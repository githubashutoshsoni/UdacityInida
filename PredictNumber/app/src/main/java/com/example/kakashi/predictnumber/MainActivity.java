package com.example.kakashi.predictnumber;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.shashank.sony.fancygifdialoglib.FancyGifDialog;
import com.shashank.sony.fancygifdialoglib.FancyGifDialogListener;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    int score = 0;
    int flag = 0;

    int one = 1;
    int two = 2;
    int three = 3;
    @BindView(R.id.score) TextView scoreTextView;
    @BindView(R.id.one_number)
    Button butterButtonOne;
    @BindView(R.id.two_number)
    Button addTwoButton;
    @BindView(R.id.three_number)
    Button addThreeButton;
    @BindView(R.id.reset)
    Button resetButton;
    @BindView(R.id.game_rules) TextView gameRulesTextView;
    @BindView(R.id.iamfast) TextView fastTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        String tooFast="";
        String gameRules= "1. You are going to start with 1, 2 or 3. \n " +
                "2. I will add 1,2 or 3 for the answer in step 1 \n" +
                "3. Then you're going to continue doing that until we reach infinity :P jk. :p\n" +
                "4. Ok, Until anyone who gets 15.\n" +
                "5.  The one who gets 15 will be the winner!";
        gameRulesTextView.setText(gameRules);

        resetButton = findViewById(R.id.reset);

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetvalues();
            }
        });

        butterButtonOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag = 1;
                score += 1;
                updateScore(one);
                result();
                computing(score);

            }
        });

        addTwoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag = 1;
                score += 2;
                updateScore(two);
                result();
                computing(score);
            }
        });

        addThreeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag = 1;
                score += 3;
                updateScore(three);
                result();
                computing(score);
            }
        });


    }

    void computing(int n) {
        flag = 0;
        int max, min;
        if (n <= 4) {
            Random random = new Random();
            max = 3;
            min = 1;
            int r = random.nextInt((max - min) + 1) + min;
            n += r;
            score = n;
            updateScore(r);
        } else {
            switch (n) {
                case 5:
                    n += 1;
                    score = n;
                    updateScore(one);
                    result();
                    break;
                case 6:
                    n += 1;
                    score = n;
                    updateScore(one);
                    result();
                    break;
                case 7:
                    n += 3;
                    score = n;
                    updateScore(three);
                    result();
                    break;
                case 8:
                    n += 3;
                    score = n;
                    updateScore(three);
                    result();
                    break;
                case 9:
                    n += 2;
                    score = n;
                    updateScore(two);
                    result();
                    break;
                case 10:
                    n += 1;
                    score = n;
                    updateScore(one);
                    result();
                    break;
                case 11:
                    n += 1;
                    score = n;
                    updateScore(one);
                    result();
                    break;
                case 12:
                    n += 3;
                    score = n;
                    updateScore(three);
                    result();
                    break;
                case 13:
                    n += 2;
                    updateScore(two);
                    score = n;
                    result();
                    break;
                case 14:
                    n += 1;
                    score = n;
                    updateScore(one);
                    result();
                    break;
            }
        }
    }

    void result() {

        if (score == 15 && flag == 1) {
            Toast.makeText(MainActivity.this, "awesome user", Toast.LENGTH_SHORT).show();
            scoreTextView.setText(score + " Congratulations You have won the game");
            alertDialogHuman();
        } else if (score == 15 && flag == 0) {
            Toast.makeText(MainActivity.this, "awesome Computer", Toast.LENGTH_SHORT).show();
            scoreTextView.setText(score + " Computer has won the game");
            alertDialogComputer();
        }

    }

    void updateScore(int n) {

        int previousScore = n;
        String result = Integer.toString(score);
        scoreTextView.append(result + " ");
        String tooFast="Sorry I'm too fast. I added "+n+ " to "+ " your answer so the result is "+ score ;
        fastTextView.setText(tooFast);

    }

    void resetvalues() {
        score = 0;
        updateScore(0);
        scoreTextView.setText(null);
        fastTextView.setText(null);
    }

    void alertDialogHuman(){
        new FancyGifDialog.Builder(this)
                .setTitle("You are awesome! ")
                .setMessage("That was nice playing. Now try to do it for three times in a row to see if you can really be a champion.")
                .setNegativeBtnText("Exit")
                .setPositiveBtnBackground("#FF4081")
                .setPositiveBtnText("Again?")
                .setNegativeBtnBackground("#FFA9A7A8")
                .setGifResource(R.drawable.awesome)   //Pass your Gif here
                .isCancellable(true)
                .OnPositiveClicked(new FancyGifDialogListener() {
                    @Override
                    public void OnClick() {
                        resetvalues();
                    }
                })
                .OnNegativeClicked(new FancyGifDialogListener() {
                    @Override
                    public void OnClick() {
                        finish();
                    }
                })
                .build();
    }
    void alertDialogComputer(){
        new FancyGifDialog.Builder(this)
                .setTitle("Computer wins!")
                .setMessage("Computer has won the game. Technology will take over humans. You are the only hope. Try to  win Please!!")
                .setNegativeBtnText("Exit")
                .setPositiveBtnBackground("#FF4081")
                .setPositiveBtnText("Again")
                .setNegativeBtnBackground("#FFA9A7A8")
                .setGifResource(R.drawable.again)   //Pass your Gif here
                .isCancellable(true)
                .OnPositiveClicked(new FancyGifDialogListener() {
                    @Override
                    public void OnClick() {
                        resetvalues();
                    }
                })
                .OnNegativeClicked(new FancyGifDialogListener() {
                    @Override
                    public void OnClick() {
                        finish();
                    }
                })
                .build();

    }
}
