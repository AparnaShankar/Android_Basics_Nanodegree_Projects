package appy.aparna.example.com.booklist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Administrator on 5/27/2017.
 */

public class BookAdapter extends ArrayAdapter<Book> {


    //Constructor
    public BookAdapter(Context context, ArrayList<Book> books) {
        super(context, 0, books);
    }

    //Rendering List items
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItem = convertView;
        if (listItem == null) {
            listItem = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        //Book object
        Book current = getItem(position);

        //Book thumbnail image
        ImageView image = (ImageView) listItem.findViewById(R.id.image);

        //If thumbnail image is there
        if (current.getImageUrl() != null) {
            Picasso.with(getContext()).load(current.getImageUrl()).into(image);
        }
        //if thumbnail image is not there
        else {
            image.setImageResource(R.drawable.no_image);
        }
        //Book title
        TextView title = (TextView) listItem.findViewById(R.id.title);
        title.setText(current.getTitle());

        //Book author
        TextView author = (TextView) listItem.findViewById(R.id.author);
        author.setText(current.getAuthor());
        return listItem;
    }
}
