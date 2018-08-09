package com.example.kakashi.newsapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;
import java.util.zip.Inflater;

public class NewsAdapter extends ArrayAdapter<News> {
    public NewsAdapter(@NonNull Context context, @NonNull List<News> objects) {
        super(context, 0, objects);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //inflater copy paste here
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.activity_main, parent, false);
        }
        News currentPosition= getItem(position);
        TextView webUrlTextView= convertView.findViewById(R.id.weburl);
        webUrlTextView.setText(currentPosition.getWebURL());
        TextView webTitle= convertView.findViewById(R.id.webtitle);
        webTitle.setText(currentPosition.getWebTitle());
        TextView sectionName= convertView.findViewById(R.id.section_name);
        sectionName.setText(currentPosition.getSectionName());

        return convertView;
    }
}
