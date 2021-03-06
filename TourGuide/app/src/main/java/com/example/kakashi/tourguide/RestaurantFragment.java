package com.example.kakashi.tourguide;


import android.app.ActionBar;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class RestaurantFragment extends Fragment {


    public RestaurantFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView= inflater.inflate(R.layout.list_items, container, false);
        final ArrayList<Places> places= new ArrayList<Places>();
        places.add(new Places(getString(R.string.ramada_name),getString(R.string.time_ramada),"TP street Chennai",R.drawable.ramada_plaza_chennai));
        places.add(new Places(getString(R.string.radisson_name),getString(R.string.time_raddisson),"TP street Chennai",R.drawable.radisum));
        places.add(new Places(getString(R.string.leela_palace_name),getString(R.string.leela_palace_time),"",R.drawable.leelaplalace));

        PlacesAdapter placesAdapter= new PlacesAdapter(getActivity(),places,R.color.yellow);
        final ListView listView= (ListView) rootView.findViewById(R.id.list_items);
        listView.setAdapter(placesAdapter);
        int AppBarcolor= ContextCompat.getColor(getActivity(),R.color.yellow);
        return  rootView;
    }
}

