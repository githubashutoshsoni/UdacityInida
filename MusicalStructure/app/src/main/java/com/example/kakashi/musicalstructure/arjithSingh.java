package com.example.kakashi.musicalstructure;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class arjithSingh extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_music);
        //ArrayList is defined with musicDetails as it's generic type
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        ArrayList<musicDetails> musicDetails= new ArrayList<>();
        musicDetails.add(new musicDetails("Tum Hi ho","2:30"));
        musicDetails.add(new musicDetails("Sanam re","3:30"));
        musicDetails.add(new musicDetails("Galti Se mistake","4:30"));
        musicDetails.add(new musicDetails("Muskurane","1:30"));
        musicDetails.add(new musicDetails("Phir bhi Tum ko chahunga","6:30"));
        musicDetails.add(new musicDetails("Ae dil hau mushkil","2:30"));
        musicAdapter musicAdapter= new musicAdapter(this, musicDetails);
        ListView listView= (ListView) findViewById(R.id.album_list_item);
        listView.setAdapter(musicAdapter);
    }

}
