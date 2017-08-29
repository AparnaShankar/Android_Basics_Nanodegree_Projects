package appy.aparna.example.com.hyderabadtourism;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CategoryAdapter categoryAdapter = new CategoryAdapter(getSupportFragmentManager(), this);

        //Find the view pager
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        //Connect the view pager to category adapter
        viewPager.setAdapter(categoryAdapter);

        // Find the tab layout that shows the tabs
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        // Connect the tab layout with the view pager.
        tabLayout.setupWithViewPager(viewPager);
    }
}
