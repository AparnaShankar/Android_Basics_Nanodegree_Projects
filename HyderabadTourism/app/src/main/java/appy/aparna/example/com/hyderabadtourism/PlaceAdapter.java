package appy.aparna.example.com.hyderabadtourism;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import static appy.aparna.example.com.hyderabadtourism.R.layout.place;

/**
 * Created by Administrator on 5/24/2017.
 */

public class PlaceAdapter extends ArrayAdapter<Place> {

    //Constructor
    public PlaceAdapter(Activity context, ArrayList<Place> place) {
        super(context, 0, place);
    }

    //GetView method
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.place, parent, false);
        }
        Place currentPlace = getItem(position);

        //TextView Name
        TextView mTextView = (TextView) listItemView.findViewById(R.id.name);
        mTextView.setText(currentPlace.getName());

        //TextView Description
        TextView dTextView = (TextView) listItemView.findViewById(R.id.desc);
        dTextView.setText(currentPlace.getDesc());

        //ImageView of the place
        ImageView imageView = (ImageView) listItemView.findViewById(R.id.image);
        imageView.setImageResource(currentPlace.getImageReasourceId());

        return listItemView;
    }
}

