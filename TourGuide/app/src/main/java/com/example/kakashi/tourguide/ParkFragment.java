package com.example.kakashi.tourguide;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v4.app.Fragment;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindColor;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class ParkFragment extends Fragment {
    MediaPlayer mediaPlayer;

    public ParkFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView= inflater.inflate(R.layout.list_items, container, false);
        final ArrayList<Places> places= new ArrayList<Places>();
        places.add(new Places(getString(R.string.pagal_park_name),getString(R.string.pangal_time),getString(R.string.pangal_park_address),R.drawable.guindy_park));
        places.add(new Places(getString(R.string.natesan_park_name),getString(R.string.natesan_park_time),getString(R.string.natesan_park_address),R.drawable.natesan));
        places.add(new Places(getString(R.string.jeeva_park_name),getString(R.string.jeeva_park_time),getString(R.string.jeeva_address),R.drawable.jeeva));
        places.add(new Places(getString(R.string.guindy_national_park_name),getString(R.string.guindy_time),getString(R.string.guindy_location),R.drawable.guindy_park));

        PlacesAdapter placesAdapter= new PlacesAdapter(getActivity(),places,R.color.colorPrimaryDark);
        final ListView listView= (ListView) rootView.findViewById(R.id.list_items);
        listView.setAdapter(placesAdapter);

        return  rootView;
    }
}

