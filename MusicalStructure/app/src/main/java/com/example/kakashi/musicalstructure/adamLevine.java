package com.example.kakashi.musicalstructure;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class adamLevine extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_music);
        ArrayList<musicDetails> musicDetails= new ArrayList<>();
        //adding music details using custom list
        musicDetails.add(new musicDetails("girls like you","2:30"));
        musicDetails.add(new musicDetails("Sugar","3:30"));
        musicDetails.add(new musicDetails("Wait","1:30"));
        musicDetails.add(new musicDetails("She will be loved","5:30"));
        musicDetails.add(new musicDetails("Moves like Jagger","2:30"));
        musicDetails.add(new musicDetails("Animals","1:30"));
        //creating custom adapter.
        musicAdapter musicAdapter= new musicAdapter(this, musicDetails);
        ListView listView= (ListView) findViewById(R.id.album_list_item);
        listView.setAdapter(musicAdapter);

    }
}
