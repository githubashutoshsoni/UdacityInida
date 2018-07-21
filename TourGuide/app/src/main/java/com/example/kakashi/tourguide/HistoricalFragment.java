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
public class HistoricalFragment extends Fragment {
    MediaPlayer mediaPlayer;
    private boolean isOpen=false;
    private ConstraintSet layout1,layout2;
  //  @BindView(R.id.constraint) ConstraintLayout constraintLayout;
    public HistoricalFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

    View rootView= inflater.inflate(R.layout.list_items, container, false);
        final ArrayList<Places> places= new ArrayList<Places>();
        places.add(new Places(getString(R.string.vivekanada_house_historical),getString(R.string.time_vivek_ananda),getString(R.string.address_vivek_ananda),R.drawable.vivekananda_house_));
        places.add(new Places(getString(R.string.semmozhi_poonga_historical),getString(R.string.time_gmc),getString(R.string.address_semmozhi_poonga),R.drawable.semmozi_poonga));
        places.add(new Places(getString(R.string.government_chennai_historical),getString(R.string.time_ambattur),getString(R.string.address_government_museum),R.drawable.government_museum));
        places.add(new Places(getString(R.string.fort_st_george_historical),getString(R.string.time_ambattur),getString(R.string.address_fort_st),R.drawable.fort_st_george));
        PlacesAdapter placesAdapter= new PlacesAdapter(getActivity(),places,R.color.colorPrimaryDark);
        final ListView listView= (ListView) rootView.findViewById(R.id.list_items);
        listView.setAdapter(placesAdapter);
        //        layout1= new ConstraintSet();
        //        layout2= new ConstraintSet();
        //        I have error here. :/

       // layout2.clone(getActivity(),R.layout.expanded_location);
       // layout1.clone(constraintLayout);



        //Please remove the following comment and check what I am doing wrong.
       /* listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Places currentPlaces= places.get(i);
                if(!isOpen)
                {
                    TransitionManager.beginDelayedTransition(constraintLayout);
                    layout2.applyTo(constraintLayout);
                    isOpen=!isOpen;
                }
                else {
                    TransitionManager.beginDelayedTransition(constraintLayout);
                    layout1.applyTo(constraintLayout);
                    isOpen=!isOpen;

                }
            }
        });
       ButterKnife.bind(this, rootView);
        */
        return  rootView;
    }
}

