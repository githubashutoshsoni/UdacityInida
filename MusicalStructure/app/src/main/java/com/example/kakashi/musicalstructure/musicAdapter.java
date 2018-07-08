package com.example.kakashi.musicalstructure;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class musicAdapter extends ArrayAdapter<musicDetails> {
    musicAdapter(Context activity, ArrayList<musicDetails> music){
        super(activity,0,music);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
    View listItemView=convertView;
    musicDetails currentMusic= getItem(position);
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.activity_arjith_singh, parent, false);
        }
        TextView songNameTextView= (TextView)listItemView.findViewById(R.id.name_of_song) ;
        songNameTextView.setText(currentMusic.getNameOfSong());
        TextView songLengthTextView=(TextView) listItemView.findViewById(R.id.length_of_song);
        songLengthTextView.setText(currentMusic.getLengthOfSong());
        return listItemView;
    }

}
