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
import android.widget.TextView;

import java.util.Random;

public class question_one_image extends AppCompatActivity {

    RelativeLayout question_one_image_page;

    Question Questions[] = new Question[4];

    ImageView q_one;
    Intent intent;
    String user_id, user_level, user_gender, user_score, color_scheme;
    int x;

    TextView countdown_display;

    Thread ElijahThread;
    Button ready;
    Integer ready_btn_clicked = 0;


    long timeToWait;
    //long seconds = -1;


    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            q_one.setVisibility(View.INVISIBLE);
        }
    };
   /* Handler handler2 = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            countdown_display.setText(""+seconds);
        }
    };*/

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_one_image);

        question_one_image_page = (RelativeLayout) findViewById(R.id.question_one_image_page);
        question_one_image_page.setBackgroundColor(Color.parseColor("#b366ff"));

        countdown_display = (TextView) findViewById(R.id.q1_count_down);
        countdown_display.setVisibility(View.INVISIBLE);

        Question question1 = new Question(R.drawable.safari,"How many birds were in the image?", "HINT: 2 + 2", "HINT: 13 - 9","4", "2","3","5");
        Question question2 = new Question(R.drawable.flowers,"How many leaves were in the image?", "HINT: 7 - 4", "HINT:  21 / 7","3", "1","2","4");
        Question question3 = new Question(R.drawable.coloured_shapes,"What colour was the triangle?", "HINT: Combining red and blue make the answer", "HINT: Magenta is another name for the colour","Purple", "Red","Green","Yellow");
        Question question4 = new Question(R.drawable.school_children,"How many children had their hands up?", "HINT: 10 - 8","HINT: 31-29","2","1", "3","0");

        Questions[0] = question1;
        Questions[1] = question2;
        Questions[2] = question3;
        Questions[3] = question4;

        Random ran = new Random();
        x = ran.nextInt(Questions.length);

        q_one = (ImageView) findViewById(R.id.Question1);
        q_one.getLayoutParams().height = 1000;
        q_one.setImageResource(Questions[x].get_image());
        q_one.setVisibility(View.INVISIBLE);

        intent = getIntent();
        color_scheme = intent.getStringExtra("COLORSCHEME");
        user_id = intent.getStringExtra("USERID");
        user_level = intent.getStringExtra("USERLEVEL");
        user_gender = intent.getStringExtra("USERGENDER");
        user_score  = intent.getStringExtra("USERSCORE");

        switch (user_level)
        {
            case "BASIC":
                timeToWait = 15000;
                break;

            case "ADVANCED":
                timeToWait = 10000;
                break;
        }


        //countdown_display.setText(""+timeToWait / 1000);
        //countdown_display.setText(""+(System.currentTimeMillis() + timeToWait) / 1000);


        Runnable r = new Runnable() {
            @Override
            public void run() {
                long future = System.currentTimeMillis() + timeToWait;
                while(System.currentTimeMillis() < future)
                {
                    try{
                        wait(future - System.currentTimeMillis());

                    }catch (Exception e){}
                }
                handler.sendEmptyMessage(0);
            }
        };
        ElijahThread = new Thread(r);


        Runnable t = new Runnable() {
            @Override
            public void run() {

                long future = System.currentTimeMillis() + timeToWait;
                while(System.currentTimeMillis() < future)
                {
                    try{
                        wait(future - System.currentTimeMillis());

                    }catch (Exception e){}
                }
                handler.sendEmptyMessage(0);
            }
        };
        ElijahThread = new Thread(t);

        ready_btn_clicked = 0;
        ready = (Button) findViewById(R.id.ready_btn);
        ready.setText(R.string.ready);
        ready.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ready_btn_clicked += 1;
                if (ready_btn_clicked == 1) {
                    q_one.setVisibility(View.VISIBLE);
                    ElijahThread.start();
                    ready.setText(R.string.proceed);

                } else if (ready_btn_clicked > 1) {
                    ready_onClick(ready);
                }
            }
        });


        /*
        new Thread(new Runnable() {
            @Override
            public void run() {

                final long future = System.currentTimeMillis() + timeToWait;
                while(System.currentTimeMillis() -timeToWait <= future){

                    seconds += 1;
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                        //countdown_display.setText(""+future);
                            handler2.sendEmptyMessage(0);
                        }
                    });
                    try{
                        Thread.sleep(1000);
                    }catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        */
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
        intent = new Intent(this, question_one_question.class);
        intent.putExtra("USERID", user_id);
        intent.putExtra("USERLEVEL", user_level);
        intent.putExtra("USERGENDER", user_gender);
        intent.putExtra("USERSCORE", user_score);
        intent.putExtra("COLORSCHEME", color_scheme);
        intent.putExtra("QUESTION", Questions[x].get_question());
        intent.putExtra("ANSWER", Questions[x].get_answer());
        intent.putExtra("BHINT", Questions[x].get_Bhint());
        intent.putExtra("AHINT", Questions[x].get_Ahint());
        intent.putExtra("WA1", Questions[x].get_wrong_answer1());
        intent.putExtra("WA2", Questions[x].get_wrong_answer2());
        intent.putExtra("WA3", Questions[x].get_wrong_answer3());
        startActivity(intent);
    }

    public void change_color_scheme(String color, Drawable btn_type){

        question_one_image_page.setBackgroundColor(Color.parseColor(color));
        ready.setBackground(btn_type);
    }
}
