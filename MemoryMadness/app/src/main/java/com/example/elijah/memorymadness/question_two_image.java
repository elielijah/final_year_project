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
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.Random;

public class question_two_image extends AppCompatActivity {


    RelativeLayout question_two_image_page;
    ImageView q_two;
    String user_id, user_level, user_gender, user_score, color_scheme;

    Question Questions[] = new Question[4];
    int x;
    long timeToWait;

    Thread ElijahThread;
    Button ready;
    Integer ready_btn_clicked = 0;

    Intent intent;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            q_two.setVisibility(View.INVISIBLE);
        }
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_two_image);

        question_two_image_page = (RelativeLayout) findViewById(R.id.question_two_image_page);
        question_two_image_page.setBackgroundColor(Color.parseColor("#b366ff"));

        Question question1 = new Question(R.drawable.board,"What was the answer to the sum on the board?", "HINT: 2 + 2", "HINT: (56/7) / 2","4", "3", "5", "8");
        Question question2 = new Question(R.drawable.supplies,"How many books were in the image?", "HINT: 8 / 4", "HINT:  (11 * 4) / 22", "2", "1","3","4");
        Question question3 = new Question(R.drawable.jack_and_jill,"How many sunflowers did you see?", "HINT: 1 + 3", "HINT: (32/4) / 2","4", "3", "5", "8");
        Question question4 = new Question(R.drawable.school,"How many windows did you see?", "HINT: 19 - 11","HINT: 47-39","8","5", "10","6");

        Questions[0] = question1;
        Questions[1] = question2;
        Questions[2] = question3;
        Questions[3] = question4;

        Random ran = new Random();
        x = ran.nextInt(Questions.length);

        q_two = (ImageView) findViewById(R.id.Question2);
        q_two.getLayoutParams().height = 1000;
        q_two.setImageResource(Questions[x].get_image());
        q_two.setVisibility(View.INVISIBLE);

        intent = getIntent();
        color_scheme = intent.getStringExtra("COLORSCHEME");
        user_id = intent.getStringExtra("USERID");
        user_level = intent.getStringExtra("USERLEVEL");
        user_score = intent.getStringExtra("USERSCORE");
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
                    q_two.setVisibility(View.VISIBLE);
                    ElijahThread.start();
                    ready.setText(R.string.proceed);

                }else if(ready_btn_clicked > 1) {
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

    public void ready_onClick(View view){

        intent = new Intent(this, question_two_question.class);
        intent.putExtra("USERID", user_id);
        intent.putExtra("USERSCORE", user_score);
        intent.putExtra("USERGENDER", user_gender);
        intent.putExtra("USERLEVEL", user_level);
        intent.putExtra("COLORSCHEME",color_scheme);
        intent.putExtra("QUESTION", Questions[x].get_question());
        intent.putExtra("ANSWER", Questions[x].get_answer());
        intent.putExtra("BHINT", Questions[x].get_Bhint());
        intent.putExtra("AHINT", Questions[x].get_Ahint());
        startActivity(intent);
    }

    public void change_color_scheme(String color, Drawable btn_type){

        question_two_image_page.setBackgroundColor(Color.parseColor(color));
        ready.setBackground(btn_type);

    }

}
