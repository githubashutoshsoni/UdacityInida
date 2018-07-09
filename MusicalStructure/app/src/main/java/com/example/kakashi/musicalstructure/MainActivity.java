package com.example.kakashi.musicalstructure;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView arjithSinghImageView;
    ImageView adamLevineImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        arjithSinghImageView= (ImageView) findViewById(R.id.arjith_singh_image);
        adamLevineImageView=(ImageView) findViewById(R.id.adam_levine_image);
        arjithSinghImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent arjithIntent= new Intent(MainActivity.this, arjithSingh.class);
                startActivity(arjithIntent);
            }
        });
        adamLevineImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent adamIntent= new Intent(MainActivity.this,adamLevine.class);
                startActivity(adamIntent);
            }
        });
    }
}
