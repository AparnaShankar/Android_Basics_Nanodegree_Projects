package appy.aparna.example.com.booklist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private EditText text;
    private String author;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button searchButton = (Button) findViewById(R.id.button);

        //Setting onClickListener
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Extracting text in edit text field
                text = (EditText) findViewById(R.id.text);
                author = text.getText().toString();
                if (author.isEmpty()) {
                    //If the Edit Text is empty display a Toast message
                    Toast.makeText(MainActivity.this, "Enter the name of the author.", Toast.LENGTH_SHORT).show();
                } else {
                    //Go to the book list activity if the user has entered author name
                    Intent intent = new Intent(MainActivity.this, BookListActivity.class);
                    intent.putExtra("AUTHOR", author);
                    startActivity(intent);
                }
            }
        });
    }
}
