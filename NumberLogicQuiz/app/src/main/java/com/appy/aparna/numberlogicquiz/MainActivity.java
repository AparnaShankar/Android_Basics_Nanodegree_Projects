package com.appy.aparna.numberlogicquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.attr.id;

public class MainActivity extends AppCompatActivity {

    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * @param view Called when submit button is clicked
     *             adds plus 1 for correct answer
     */

    public void submitQuiz(View view) {
        int id = 0;//to store id of radio button pressed

        //Checking 1st question
        RadioGroup question1 = (RadioGroup) findViewById(R.id.option_1);
        id = question1.getCheckedRadioButtonId();
        if (id == R.id.option_1_4)
            score++;//adding plus 1

        //Checking 2nd question
        RadioGroup question2 = (RadioGroup) findViewById(R.id.option_2);
        id = question2.getCheckedRadioButtonId();
        if (id == R.id.option_2_3)
            score++;//adding plus 1

        //Checkbox question (3rd question)
        CheckBox one = (CheckBox) findViewById(R.id.option_3_1);
        CheckBox two = (CheckBox) findViewById(R.id.option_3_2);
        CheckBox three = (CheckBox) findViewById(R.id.option_3_3);
        CheckBox four = (CheckBox) findViewById(R.id.option_3_4);
        if (one.isChecked() && two.isChecked() && (!three.isChecked()) && four.isChecked())
            score++;//adding 1 point

        //Question 4
        EditText question4 = (EditText) findViewById(R.id.option_4);
        String answer="";
        answer+=question4.getText().toString();//converting edit text to string then to int
        if (!(answer.isEmpty())) {
            int ans=Integer.parseInt(answer);
            if(ans==101)
                score++;//adding 1 point
        }

        //Checking 5th question
        RadioGroup question5 = (RadioGroup) findViewById(R.id.option_5);
        id = question5.getCheckedRadioButtonId();
        if (id == R.id.option_5_3)
            score++;//adding plus 1

        //Checkbox question (6th question)
        one = (CheckBox) findViewById(R.id.option_6_1);
        two = (CheckBox) findViewById(R.id.option_6_2);
        three = (CheckBox) findViewById(R.id.option_6_3);
        four = (CheckBox) findViewById(R.id.option_6_4);
        if (one.isChecked() && (!two.isChecked()) && (!three.isChecked()) && four.isChecked())
            score++;//adding 1 point

        //Checking 7th question
        RadioGroup question7 = (RadioGroup) findViewById(R.id.option_7);
        id = question7.getCheckedRadioButtonId();
        if (id == R.id.option_7_1)
            score++;//adding plus 1

        //Checking 8th question
        RadioGroup question8 = (RadioGroup) findViewById(R.id.option_8);
        id = question8.getCheckedRadioButtonId();
        if (id == R.id.option_8_2)
            score++;//adding plus 1

        //Checking 9th question
        RadioGroup question9 = (RadioGroup) findViewById(R.id.option_9);
        id = question9.getCheckedRadioButtonId();
        if (id == R.id.option_9_2)
            score++;//adding plus 1

        //Checking 10th question
        RadioGroup question10 = (RadioGroup) findViewById(R.id.option_10);
        id = question10.getCheckedRadioButtonId();
        if (id == R.id.option_10_3)
            score++;//adding plus 1
        //Score is displayed as a Toast
        Toast.makeText(this, getString(R.string.display1) + score + getString(R.string.display2), Toast.LENGTH_SHORT).show();
        //score is set to 0 to retake the quiz
        score = 0;

    }
}
