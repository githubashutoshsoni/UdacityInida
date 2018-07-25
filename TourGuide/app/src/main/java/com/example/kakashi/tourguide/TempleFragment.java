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
public class TempleFragment extends Fragment {
    MediaPlayer mediaPlayer;
    //    private boolean isOpen=false;
//    private ConstraintSet layout1,layout2;
//   @BindView(R.id.constraint) ConstraintLayout constraintLayout;
    public TempleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView= inflater.inflate(R.layout.list_items, container, false);
        final ArrayList<Places> places= new ArrayList<Places>();
        places.add(new Places(getString(R.string.name_tirupati),getString(R.string.Thirupati_time),getString(R.string.tirupati_images),R.drawable.thirupati));
        places.add(new Places(getString(R.string.name_chennai_kesan),getString(R.string.time_chennai_kesan), getString(R.string.address_chennai_kesav),R.drawable.sri_kessa_chenna));
        places.add(new Places(getString(R.string.fort_st_george_historical),getString(R.string.time_ambattur),getString(R.string.address_fort_st),R.drawable.fort_st_george));
        PlacesAdapter placesAdapter= new PlacesAdapter(getActivity(),places,R.color.colorPrimaryDark);
        final ListView listView= (ListView) rootView.findViewById(R.id.list_items);
        listView.setAdapter(placesAdapter);

        return  rootView;
    }
}

