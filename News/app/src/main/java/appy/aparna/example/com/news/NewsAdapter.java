package appy.aparna.example.com.news;

/**
 * Created by Administrator on 5/28/2017.
 */

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class NewsAdapter extends ArrayAdapter<News> {
    private String news_title;
    private String section;
    private char section_char;

    //Constructor
    public NewsAdapter(Context context, ArrayList<News> news) {
        super(context, 0, news);
    }

    /**
     * To assign colors to sections
     *
     * @param section-first character of the section
     * @return color code of the section
     */
    private int getSectionColor(char section) {
        int sectionColorResourceId;
        switch (section) {
            case 'P':
                sectionColorResourceId = R.color.politics;
                break;
            case 'W':
                sectionColorResourceId = R.color.world;
                break;
            case 'S':
                sectionColorResourceId = R.color.sport;
                break;
            case 'B':
                sectionColorResourceId = R.color.business;
                break;
            case 'F':
                sectionColorResourceId = R.color.fashion;
                break;
            case 'E':
                sectionColorResourceId = R.color.environment;
                break;
            default:
                sectionColorResourceId = R.color.colorAccent;
                break;
        }
        return ContextCompat.getColor(getContext(), sectionColorResourceId);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItem = convertView;
        if (listItem == null) {
            listItem = LayoutInflater.from(getContext()).inflate(R.layout.news_list_item, parent, false);
        }

        //News object
        News current = getItem(position);

        //Section
        //Formatting
        section = current.getSection();
        section_char = section.toUpperCase().charAt(0);
        TextView sec = (TextView) listItem.findViewById(R.id.sec);
        sec.setText("" + section_char);

        // Set the proper background color on the section circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable sectionCircle = (GradientDrawable) sec.getBackground();

        // Get the appropriate background color based on the section first letter
        int sectionColor = getSectionColor(section_char);

        // Set the color on the section circle
        sectionCircle.setColor(sectionColor);

        //News_title
        news_title = current.getTitle();
        TextView title = (TextView) listItem.findViewById(R.id.title);
        title.setText(news_title);

        //News Section
        TextView section_view = (TextView) listItem.findViewById(R.id.section);
        section_view.setText(section);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        Date dateObject = null;
        try {
            dateObject = simpleDateFormat.parse(current.getDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //Date format
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM d, yyyy");
        String dateToDisplay = dateFormatter.format(dateObject);
        //Date
        TextView d = (TextView) listItem.findViewById(R.id.date);
        d.setText(dateToDisplay);

        // Find the TextView with view ID time
        TextView timeView = (TextView) listItem.findViewById(R.id.time);
        // Format the time string (i.e. "4:30PM")
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        String formattedTime = timeFormat.format(dateObject);
        // Display the time
        timeView.setText(formattedTime);

        return listItem;
    }
}
