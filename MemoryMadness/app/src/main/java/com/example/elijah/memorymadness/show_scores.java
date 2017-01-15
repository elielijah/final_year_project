package com.example.elijah.memorymadness;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


public class show_scores extends AppCompatActivity {

    RelativeLayout show_scores_page;
    TableLayout user_scores;
    MyDBHandler handler;

    String all_users;
    String[] ind_users;
    String[] user_details;
    String  name, age, gender, score, level;
    TableRow[] user_rows;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_scores);

        show_scores_page = (RelativeLayout) findViewById(R.id.show_scores_page);
        show_scores_page.setBackgroundColor(Color.parseColor("#b366ff"));

        user_scores = (TableLayout) findViewById(R.id.score_table);

        handler = new MyDBHandler(this, null, null, 1);

        all_users = handler.databaseToString();
        ind_users = all_users.split("\n");

        Integer number_of_users = ind_users.length;
        user_rows = new TableRow[number_of_users];

        for (int i = 0; i < number_of_users; i++)
        {
            user_rows[i] = new TableRow(this);

            user_details = ind_users[i].split(",");

            name = user_details[1];
            age = user_details[2];
            gender = user_details[3];
            score = user_details[4];
            level = user_details[5];

            TextView nameView = new TextView(this);
            TextView ageView = new TextView(this);
            TextView genderView = new TextView(this);
            TextView scoreView = new TextView(this);
            TextView levelView = new TextView(this);

            nameView.setText(name);
            ageView.setText(age);
            genderView.setText(gender);
            scoreView.setText(score);
            levelView.setText(level);

            nameView.setTextColor(Color.BLACK);
            ageView.setTextColor(Color.BLACK);
            genderView.setTextColor(Color.BLACK);
            scoreView.setTextColor(Color.BLACK);
            levelView.setTextColor(Color.BLACK);

            ageView.setPadding(20,0,0,0);
            genderView.setPadding(20,0,0,0);
            scoreView.setPadding(20,0,0,0);
            levelView.setPadding(20,0,0,0);

            user_rows[i].addView(nameView);
            user_rows[i].addView(ageView);
            user_rows[i].addView(genderView);
            user_rows[i].addView(scoreView);
            user_rows[i].addView(levelView);

            user_scores.addView(user_rows[i]);

        }

    }


}
