package com.example.elijah.memorymadness;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;

public class question_five_image extends AppCompatActivity {

    RelativeLayout question_five_image_page;
    TextView q_five, countdown_display;
    String scenario = "" +
            "A group of farmers decided they were going to combine their stock \n" +
            "Andrew had: 17 oranges \n"+
            "Elijah had: 3 female dogs \n"+
            "Rick had: 23 pears.";
    Intent intent;
    String user_id, user_level, user_score, user_gender, countdown, color_scheme;

    Question Questions[] = new Question[4];
    int x;

    Thread ElijahThread;
    Button ready;
    Integer ready_btn_clicked = 0;

    long timeToWait;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            q_five.setVisibility(View.INVISIBLE);
        }
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_five_image);

        question_five_image_page = (RelativeLayout) findViewById(R.id.question_five_image_page);
        question_five_image_page.setBackgroundColor(Color.parseColor("#b366ff"));

        Question question1 = new Question("" +
                "A group of farmers decided they were going to combine their stock \n" +
                "Andrew had: 17 oranges \n"+
                "Elijah had: 3 female dogs \n"+
                "Rick had: 23 pears."
                ,"How many fruit did the farmers have combined?", "HINT: 23 + 17", "HINT: (8 * 4) + 8","40");

        Question question2 = new Question("" +
                "A pet store in Manchester is running low on stock they have...  \n" +
                "Dogs: 5 \n"+
                "Cats: 3 \n"+
                "Birds: 12."
                ,"How many pets did the store have that can't fly?", "HINT: 1 + 7", "HINT: (9 * 3) - 19","8");

        Question question3 = new Question("" +
                "A property manager has 3 types of building available  \n" +
                "Bungalows: 10 \n"+
                "Houses:  6 \n"+
                "Flats: 12"
                ,"How many houses did the property manager have?", "HINT: 5 + 1", "HINT: (3 * 3) - 3","6");

        Question question4 = new Question("" +
                "On a street there were...\n" +
                "Blue cars: 4 \n"+
                "Red: 3 \n"+
                "Black: 6 \n"+
                "Bicycles: 2"
                ,"How many cars were on the street that weren't red?", "HINT: 11 - 1", "HINT: (6 * 2) - 2","10");

        Questions[0] = question1;
        Questions[1] = question2;
        Questions[2] = question3;
        Questions[3] = question4;

        Random ran = new Random();
        x = ran.nextInt(Questions.length);

        intent = getIntent();
        color_scheme = intent.getStringExtra("COLORSCHEME");
        user_id = intent.getStringExtra("USERID");
        user_score = intent.getStringExtra("USERSCORE");
        user_level = intent.getStringExtra("USERLEVEL");
        user_gender = intent.getStringExtra("USERGENDER");

        switch (user_level)
        {
            case "BASIC":
                timeToWait = 15000;
                break;

            case "ADVANCED":
                timeToWait = 10000;
                break;
        }

        q_five = (TextView) findViewById(R.id.question_five_text);
        q_five.setText(Questions[x].get_scenario());
        q_five.getLayoutParams().height = 1000;
        q_five.setVisibility(View.INVISIBLE);

        Runnable r = new Runnable() {
            @Override
            public void run() {
                long future = System.currentTimeMillis() + timeToWait;
                while(System.currentTimeMillis() < future)
                {
                    try{
                        wait(future-System.currentTimeMillis());
                    }catch (Exception e){}
                }
                handler.sendEmptyMessage(0);
            }
        };
        ElijahThread = new Thread(r);

        ready_btn_clicked = 0;
        ready = (Button) findViewById(R.id.ready_btn);
        ready.setText(R.string.ready);
        ready.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ready_btn_clicked += 1;
                if (ready_btn_clicked == 1) {
                    q_five.setVisibility(View.VISIBLE);
                    ElijahThread.start();
                    ready.setText(R.string.proceed);

                } else if (ready_btn_clicked > 1) {
                    ready_onClick(ready);
                }
            }
        });

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

    public void ready_onClick(View view)
    {
        if(user_level.equals("BASIC")){

        }else if (user_level.equals("ADVANCED")){

        }

        intent = new Intent(this, question_five_question.class);
        intent.putExtra("USERID", user_id);
        intent.putExtra("USERLEVEL", user_level);
        intent.putExtra("USERGENDER", user_gender);
        intent.putExtra("USERSCORE", user_score);
        intent.putExtra("COLORSCHEME", color_scheme);
        intent.putExtra("QUESTION", Questions[x].get_question());
        intent.putExtra("ANSWER", Questions[x].get_answer());
        intent.putExtra("BHINT", Questions[x].get_Bhint());
        intent.putExtra("AHINT", Questions[x].get_Ahint());
        startActivity(intent);
    }

    public void change_color_scheme(String color, Drawable btn_type){

        question_five_image_page.setBackgroundColor(Color.parseColor(color));
        ready.setBackground(btn_type);
    }

}
