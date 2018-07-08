package com.example.kakashi.musicalstructure;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class adamLavine extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_music);

        ArrayList<musicDetails> musicDetails= new ArrayList<>();
        musicDetails.add(new musicDetails("Some song name","2:30"));
        musicDetails.add(new musicDetails("Some song name","2:30"));
        musicDetails.add(new musicDetails("Some song name","2:30"));
        musicDetails.add(new musicDetails("Some song name","2:30"));
        musicDetails.add(new musicDetails("Some song name","2:30"));
        musicDetails.add(new musicDetails("Some song name","2:30"));
        musicAdapter musicAdapter= new musicAdapter(this, musicDetails);
        ListView listView= (ListView) findViewById(R.id.album_list_item);
        listView.setAdapter(musicAdapter);
    }
}
