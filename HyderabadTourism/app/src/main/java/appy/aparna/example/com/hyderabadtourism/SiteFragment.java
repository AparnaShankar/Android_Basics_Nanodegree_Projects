package appy.aparna.example.com.hyderabadtourism;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;


public class SiteFragment extends Fragment {


    public SiteFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.place_list, container, false);
        // Create a list of places
        final ArrayList<Place> places = new ArrayList<Place>();
        places.add(new Place(getString(R.string.zoo), getString(R.string.zoo_d), R.drawable.zoo));
        places.add(new Place(getString(R.string.ntr), getString(R.string.ntr_d), R.drawable.ntr_gardens));
        places.add(new Place(getString(R.string.Birla), getString(R.string.Birla_d), R.drawable.birla_mandir));
        places.add(new Place(getString(R.string.statue), getString(R.string.statue_d), R.drawable.statue));
        //create a place adapter object
        PlaceAdapter Adapter = new PlaceAdapter(getActivity(), places);

        ListView listView = (ListView) rootView.findViewById(R.id.list);
        //Link listView to adapter
        listView.setAdapter(Adapter);

        return rootView;
    }
}