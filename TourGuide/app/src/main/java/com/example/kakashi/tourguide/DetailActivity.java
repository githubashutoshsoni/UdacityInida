package com.example.kakashi.tourguide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {
@BindView(R.id.description)
    TextView descriptionTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent= getIntent();
        String description= intent.getStringExtra("description");
        descriptionTextView.setText(description);
        ButterKnife.bind(this);
    }
}
