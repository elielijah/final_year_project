package com.example.elijah.memorymadness;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class submitted extends AppCompatActivity {

    RelativeLayout submitted;

    TextView display_user_score, displayCalculationProgress, try_again;
    Button restart, end_game;
    Intent intent;

    String user_level, user_score, user_gender, color_scheme;
    String[] user_details;
    String user_ID, user_name, user_age, out_of;

    ProgressBar progress, Loading;
    int progressStatus = 0;

    Handler handler = new Handler();
    MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.submitted);

        submitted = (RelativeLayout) findViewById(R.id.submitted_page);
        submitted.setBackgroundColor(Color.parseColor("#b366ff"));

        progress = (ProgressBar) findViewById(R.id.calculateScore);
        Loading = (ProgressBar) findViewById(R.id.Loading);
        displayCalculationProgress = (TextView) findViewById(R.id.complete_percentage);

        progress.setVisibility(View.INVISIBLE);
        displayCalculationProgress.setVisibility(View.INVISIBLE);
        try_again = (TextView) findViewById(R.id.try_again_display);
        try_again.setVisibility(View.INVISIBLE);

        restart = (Button) findViewById(R.id.try_again_btn);
        restart.setText(R.string.Try_again);
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restart();
            }
        });
        restart.setVisibility(View.INVISIBLE);

        end_game = (Button) findViewById(R.id.end_game_btn);
        end_game.setText(R.string.End_Game);
        end_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                end_game();
            }
        });
        end_game.setVisibility(View.INVISIBLE);

        intent = getIntent();
        color_scheme = intent.getStringExtra("COLORSCHEME");
        user_ID = intent.getStringExtra("USERID");
        user_level = intent.getStringExtra("USERLEVEL");
        user_score = intent.getStringExtra("USERSCORE");
        user_gender = intent.getStringExtra("USERGENDER");


        dbHandler.addScore(Integer.parseInt(user_ID), Integer.parseInt(user_score), user_level);

        user_details = dbHandler.getUserDetails(Integer.parseInt(user_ID)).split(",");

        user_name = user_details[1];
        user_gender = user_details[2];

        Integer tmp_score = Integer.parseInt(user_score);

        if (user_level.equals("BASIC")){

            out_of = "/50";

            if(tmp_score <= 44){

                try_again.setText("Nice try "+user_name+" give the test another go to try and better your score.");

            }else if(tmp_score > 44){

                try_again.setText("Great job "+user_name+" next time try increasing the difficulty");
            }

        }else{

            out_of = "/100";


        }

        display_user_score = (TextView) findViewById(R.id.user_score);
        display_user_score.setText("Calculating your score, this may take a moment...");

        //display_user_score.setVisibility(View.INVISIBLE);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(progressStatus < 100){

                    progressStatus += 1;
                    handler.post(new Runnable() {
                        @Override
                        public void run() {

                            progress.setProgress(progressStatus);
                            displayCalculationProgress.setText(""+progressStatus+"%");

                            if(progressStatus == 100)
                            {
                               display_user_score.setText("Your score is: " + user_score +out_of);
                               // display_user_score.setText(dbHandler.getUserDetails(1));
                                progress.setVisibility(View.INVISIBLE);
                                Loading.setVisibility(View.INVISIBLE);
                                displayCalculationProgress.setVisibility(View.INVISIBLE);

                                try_again.setVisibility(View.VISIBLE);
                                restart.setVisibility(View.VISIBLE);
                                end_game.setVisibility(View.VISIBLE);
                            }
                        }
                    });
                    try{
                        Thread.sleep(100);
                    }catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        if(color_scheme != null){

            switch (color_scheme){
                case "#b366ff" :
                    change_color_scheme(color_scheme, getResources().getDrawable(R.drawable.custom_button));
                    break;

                case "#FFAAFF" :
                    change_color_scheme(color_scheme, getResources().getDrawable(R.drawable.cs1));
                    break;

                case "#CCEEFF" :
                    change_color_scheme(color_scheme, getResources().getDrawable(R.drawable.cs2));
                    break;

                case "#FFFFCC" :
                    change_color_scheme(color_scheme, getResources().getDrawable(R.drawable.cs3));
                    break;
            }
        }

    }

    public void restart()
    {
        intent = new Intent(this, SelectUser.class);
        intent.putExtra("USERID", user_ID);
        startActivity(intent);
    }

    public void end_game()
    {
        intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }

    public void change_color_scheme(String color, Drawable btn_type){

        submitted.setBackgroundColor(Color.parseColor(color));
        end_game.setBackground(btn_type);
        restart.setBackground(btn_type);
    }
}
