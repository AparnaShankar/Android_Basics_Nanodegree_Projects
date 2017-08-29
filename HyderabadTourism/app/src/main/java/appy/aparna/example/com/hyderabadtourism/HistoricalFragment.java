package appy.aparna.example.com.hyderabadtourism;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class HistoricalFragment extends Fragment {

    public HistoricalFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.place_list, container, false);
        // Create a list of places
        final ArrayList<Place> places = new ArrayList<Place>();
        places.add(new Place(getString(R.string.masjid), getString(R.string.masjid_d), R.drawable.mecca_masjid));
        places.add(new Place(getString(R.string.charminar), getString(R.string.charminar_d), R.drawable.chrminar));
        places.add(new Place(getString(R.string.golconda), getString(R.string.golconda_d), R.drawable.golconda));
        places.add(new Place(getString(R.string.palace), getString(R.string.palace_d), R.drawable.chowmahalla_palace));
        //create a place adapter object
        PlaceAdapter Adapter = new PlaceAdapter(getActivity(), places);

        ListView listView = (ListView) rootView.findViewById(R.id.list);
        //Link listView to adapter
        listView.setAdapter(Adapter);

        return rootView;
    }

}
