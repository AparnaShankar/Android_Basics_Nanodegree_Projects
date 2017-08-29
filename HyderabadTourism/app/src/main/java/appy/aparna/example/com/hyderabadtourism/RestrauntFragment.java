package appy.aparna.example.com.hyderabadtourism;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class RestrauntFragment extends Fragment {

    public RestrauntFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.place_list, container, false);
        // Create a list of places
        final ArrayList<Place> places = new ArrayList<Place>();
        places.add(new Place(getString(R.string.sahib), getString(R.string.sahib_d), R.drawable.sahib_sindh_sultan));
        places.add(new Place(getString(R.string.chutney), getString(R.string.chutney_d), R.drawable.chutney));
        places.add(new Place(getString(R.string.bahar), getString(R.string.bahar_d), R.drawable.cafe_bahar));
        places.add(new Place(getString(R.string.east_78), getString(R.string.east_78_d), R.drawable.east_78));
        //create a place adapter object
        PlaceAdapter Adapter = new PlaceAdapter(getActivity(), places);

        ListView listView = (ListView) rootView.findViewById(R.id.list);
        //Link listView to adapter
        listView.setAdapter(Adapter);

        return rootView;
    }


}
