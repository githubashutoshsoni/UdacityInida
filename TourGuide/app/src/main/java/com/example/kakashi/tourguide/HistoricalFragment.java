package com.example.kakashi.tourguide;


import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
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
    public HistoricalFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView= inflater.inflate(R.layout.list_items, container, false);
        final ArrayList<Places> places= new ArrayList<Places>();

        places.add(new Places(getString(R.string.vivekanada_house_historical),getString(R.string.time_vivek_ananda),getString(R.string.address_vivek_ananda),R.drawable.vivekananda_house_));
        places.add(new Places(getString(R.string.semmozhi_poonga_historical),getString(R.string.time_gmc),getString(R.string.address_semmozhi_poonga),R.drawable.semmozi_poonga));
        places.add(new Places(getString(R.string.government_chennai_historical),getString(R.string.time_ambattur),getString(R.string.address_government_museum),R.drawable.government_museum));
        places.add(new Places(getString(R.string.fort_st_george_historical),getString(R.string.time_ambattur),getString(R.string.address_fort_st),R.drawable.fort_st_george));
        PlacesAdapter placesAdapter= new PlacesAdapter(getActivity(),places,R.color.yellow);
        final ListView listView= (ListView) rootView.findViewById(R.id.list_items);
        listView.setAdapter(placesAdapter);
        int AppBarcolor= ContextCompat.getColor(getActivity(),R.color.yellow);

        return  rootView;
    }
}

