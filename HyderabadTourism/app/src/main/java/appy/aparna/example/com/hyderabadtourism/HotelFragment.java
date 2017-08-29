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


public class HotelFragment extends Fragment {

    public HotelFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.place_list, container, false);
        // Create a list of places
        final ArrayList<Place> places = new ArrayList<Place>();
        places.add(new Place(getString(R.string.deccan), getString(R.string.deccan_d), R.drawable.taj_deccan));
        places.add(new Place(getString(R.string.krishna), getString(R.string.krishna_d), R.drawable.tajkrishna));
        places.add(new Place(getString(R.string.marriot), getString(R.string.marriot_d), R.drawable.hyderabad_marriot));
        places.add(new Place(getString(R.string.castle), getString(R.string.castle_d), R.drawable.amrutha_castle));
        //create a place adapter object
        PlaceAdapter Adapter = new PlaceAdapter(getActivity(), places);

        ListView listView = (ListView) rootView.findViewById(R.id.list);
        //Link listView to adapter
        listView.setAdapter(Adapter);

        return rootView;
    }

}
