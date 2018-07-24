package com.example.android.quakereport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class EarthQuakeAdapter extends ArrayAdapter<Earthquake> {

    public EarthQuakeAdapter(@NonNull Context context, @NonNull List<Earthquake> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Earthquake earthQuake= getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.earthquake_items, parent, false);
        }
        TextView magnitudeTextView= (TextView) convertView.findViewById(R.id.magnitude);
        magnitudeTextView.setText(String.valueOf(earthQuake.getmMagnitude()));
        TextView locationTextView=(TextView) convertView.findViewById(R.id.location);
        locationTextView.setText(earthQuake.getPlace());
        Date dateObject = new Date(earthQuake.getTime());
        TextView dateView = (TextView) convertView.findViewById(R.id.date);
        String formattedDate = formatDate(dateObject);
        dateView.setText(formattedDate);

        TextView timeView = (TextView) convertView.findViewById(R.id.time);
        // Format the time string (i.e. "4:30PM")
        String formattedTime = formatTime(dateObject);
        // Display the time of the current earthquake in that TextView
        timeView.setText(formattedTime);
        // Display the date of the current earthquake in that TextView
        dateView.setText(formattedDate);
        TextView timeTextView=(TextView) convertView.findViewById(R.id.time);
        timeTextView.setText(String.valueOf(earthQuake.getTime()));
        return convertView;
    }
    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }
}
