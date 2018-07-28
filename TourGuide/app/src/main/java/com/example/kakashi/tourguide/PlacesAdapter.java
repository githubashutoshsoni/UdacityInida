package com.example.kakashi.tourguide;

import android.app.ActionBar;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.zip.Inflater;

import butterknife.BindView;

public class PlacesAdapter extends ArrayAdapter<Places> {

    int mColorResourceId;
    public PlacesAdapter(@NonNull Context context, @NonNull List<Places> objects,int colorResoureId) {
        super(context, 0, objects);
        mColorResourceId= colorResoureId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.places_list_view, parent, false);
        }
        Places places = getItem(position);
        TextView placeNameTextView = convertView.findViewById(R.id.placeName);
        placeNameTextView.setText(places.returnName());
        TextView timeTextView = convertView.findViewById(R.id.time_opening_closing);
        timeTextView.setText(places.returnTime());
        TextView locationTextView = convertView.findViewById(R.id.location_in_place);
        locationTextView.setText(places.returnLocation());

        if (places.hasImage()) {
            ImageView imageView = convertView.findViewById(R.id.imageView);
            imageView.setVisibility(View.VISIBLE);
            imageView.setImageResource(places.returnImageResourceId());
        } else {
            ImageView imageView = convertView.findViewById(R.id.imageView);
            imageView.setVisibility(View.GONE);
        }


        View text_container= convertView.findViewById(R.id.text_container);
        int color= ContextCompat.getColor(getContext(),mColorResourceId);
        text_container.setBackgroundColor(color);

       return convertView;
    }
}
