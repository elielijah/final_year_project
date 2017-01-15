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

public class question_three_image extends AppCompatActivity {


    RelativeLayout question_three_image_page;
    TextView q_three;
    Intent intent;
    String user_id, user_level, user_score, user_gender, color_scheme;

    Question Questions[] = new Question[4];
    int x;

    Thread ElijahThread;
    Button ready;
    Integer ready_btn_clicked = 0;

    long timeToWait;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            q_three.setVisibility(View.INVISIBLE);
        }
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_three_image);

        question_three_image_page = (RelativeLayout) findViewById(R.id.question_three_image_page);
        question_three_image_page.setBackgroundColor(Color.parseColor("#b366ff"));

        Question question1 = new Question("Once upon a time in a small city named Leeds there was a young boy called Jordan aged 12 and his sister Mary aged 8. The two children both went into the woods they came across an injured bird, they picked the bird up and named it ruby because of its bright red feathers."
                ,"How old is Jordan?", "HINT: 6 * 2", "HINT: (8 * 4) - 20","12", "What was the combined age of both the children?", "20", "25", "15","30"
                ,"HINT: 43 - 13", "HINT: (67 - 14) - 33");

        Question question2 = new Question("Once upon a time in a city named Manchester there was a young boy called Elijah aged 14 and his sister Esther aged 9. The two children both went to the store and bought 3 items strawberries, blueberries, and oranges. The total was £5.50"
                ,"How many items did the children buy?", "HINT: 9/3", "HINT: (47 - 35) - 9","3", "What was the total price of the items they bought?", "£5.50", "£4.50", "£10", "£5"
                , "HINT: The answer is £3 + £2.50.", "HINT: From £10 you'd £4.50 change.");

        Question question3 = new Question("Once upon a time in a city named Birmingham there was a young boy called Joel aged 15 and his sister Mia aged 5. The two children both went to school Joel felt ill and went home at 2 Mia stayed in school and studies Maths, History and German."
                ,"What time did Joel leave?", "HINT: 18-16", "HINT: (14 * 3) / 21","2", "What was the name of the city they were in?", "birmingham", "manchester", "london", "leeds"
                ,"HINT: The city begins with a 'B'.", "HINT: The city is located in the west midlands.");

        Question question4 = new Question("Once upon a time in a city named London there was a young boy called Theo aged 12 and his sister Katie aged 8. The two children both went to an amusement park Theo won 2 prizes a teddy bear and a gold fish he named it nemo."
                ,"What was the combined age of the children?", "HINT: 10 + 10", "HINT: ((90 * 2) + 20) / 10","20", "What did Theo name the gold fish?", "nemo", "pingu", "coco pop", "lucky"
                ,"HINT: The name of the fish begins with 'n'.", "HINT: The fish is named after a famous Disney film.");

        Questions[0] = question1;
        Questions[1] = question2;
        Questions[2] = question3;
        Questions[3] = question4;

        Random ran = new Random();
        x = ran.nextInt(Questions.length);

        intent = getIntent();
        color_scheme = intent.getStringExtra("COLORSCHEME");
        user_id = intent.getStringExtra("USERID");
        user_gender = intent.getStringExtra("USERGENDER");
        user_level = intent.getStringExtra("USERLEVEL");
        user_score = intent.getStringExtra("USERSCORE");

        switch (user_level)
        {
            case "BASIC":
                timeToWait = 20000;
                break;

            case "ADVANCED":
                timeToWait = 15000;
                break;
        }

        q_three = (TextView) findViewById(R.id.question_three_text);
        q_three.setText(Questions[x].get_scenario());
        q_three.setVisibility(View.INVISIBLE);

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
                    q_three.setVisibility(View.VISIBLE);
                    ElijahThread.start();
                    ready.setText(R.string.proceed);

                } else if (ready_btn_clicked > 1) {
                    ready_onClick(ready);
                }

            }
        });

        if(color_scheme != null) {

            switch (color_scheme) {
                case "#b366ff":
                    change_color_scheme(color_scheme, getResources().getDrawable(R.drawable.custom_button));
                    break;

                case "#FFAAFF":
                    change_color_scheme(color_scheme, getResources().getDrawable(R.drawable.cs1));
                    break;

                case "#CCEEFF":
                    change_color_scheme(color_scheme, getResources().getDrawable(R.drawable.cs2));
                    break;

                case "#FFFFCC":
                    change_color_scheme(color_scheme, getResources().getDrawable(R.drawable.cs3));
                    break;
            }
        }
    }

    public void ready_onClick(View view)
    {
        intent = new Intent(this, question_three_question.class);
        intent.putExtra("USERID", user_id);
        intent.putExtra("USERLEVEL", user_level);
        intent.putExtra("USERGENDER", user_gender);
        intent.putExtra("USERSCORE", user_score);
        intent.putExtra("COLORSCHEME", color_scheme);
        intent.putExtra("QUESTION", Questions[x].get_question());
        intent.putExtra("ANSWER", Questions[x].get_answer());
        intent.putExtra("BHINT", Questions[x].get_Bhint());
        intent.putExtra("AHINT", Questions[x].get_Ahint());
        intent.putExtra("TWOQUESTION", Questions[x].get_two_question());
        intent.putExtra("TWOANSWER", Questions[x].get_part_two_answer());
        intent.putExtra("TWOBHINT", Questions[x].get_part_two_hint_basic());
        intent.putExtra("TWOAHINT", Questions[x].get_part_two_hint_advanced());
        intent.putExtra("WA1", Questions[x].get_wrong_answer1());
        intent.putExtra("WA2", Questions[x].get_wrong_answer2());
        intent.putExtra("WA3", Questions[x].get_wrong_answer3());
        startActivity(intent);
    }

    public void change_color_scheme(String color, Drawable btn_type){
        question_three_image_page.setBackgroundColor(Color.parseColor(color));
        ready.setBackground(btn_type);
    }

}
