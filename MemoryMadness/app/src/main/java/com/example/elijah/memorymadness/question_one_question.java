package com.example.elijah.memorymadness;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class question_one_question extends AppCompatActivity {


    RelativeLayout first_question;
    TextView question;
    Button A, B, C, D, HINT;

    Intent intent;

    String user_id, user_level, user_gender, user_score, color_scheme, hint;
    String c_question, c_answer, c_Bhint, c_Ahint, c_wrong1, c_wrong2, c_wrong3;

    int temp_score;
    Boolean hint_used = false, pointAwarded = false;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_one_question);

        first_question = (RelativeLayout) findViewById(R.id.question_one);
        first_question.setBackgroundColor(Color.parseColor("#b366ff"));

        intent = getIntent();
        color_scheme = intent.getStringExtra("COLORSCHEME");
        user_id = intent.getStringExtra("USERID");
        user_level = intent.getStringExtra("USERLEVEL");
        user_score = intent.getStringExtra("USERSCORE");
        user_gender = intent.getStringExtra("USERGENDER");
        c_question = intent.getStringExtra("QUESTION");
        c_answer = intent.getStringExtra("ANSWER");
        c_Bhint = intent.getStringExtra("BHINT");
        c_Ahint = intent.getStringExtra("AHINT");
        c_wrong1 = intent.getStringExtra("WA1");
        c_wrong2 = intent.getStringExtra("WA2");
        c_wrong3 = intent.getStringExtra("WA3");


        String easy_question = "What colour was the triangle?";
        String medium_question = "What shape was the primary colour?";
        String hard_question = "Which two shapes make the colour yellow when their colours are mixed?";

        String easy_answer = "PURPLE";
        String easy_dummy1 = "RED";
        String easy_dummy2 = "GREEN";
        String easy_dummy3 = "YELLOW";

        question = (TextView)findViewById(R.id.first_question);
        A = (Button) findViewById(R.id.q1_btn_A);
        B = (Button) findViewById(R.id.q1_btn_B);
        C = (Button) findViewById(R.id.q1_btn_C);
        D = (Button) findViewById(R.id.q1_btn_D);
        HINT = (Button) findViewById(R.id.q1_btn_hint);

        question.setText(c_question);

        C.setText(c_answer);
        A.setText(c_wrong1);
        B.setText(c_wrong2);
        D.setText(c_wrong3);

        if(user_level.equals("BASIC")){
            hint = c_Bhint;
        }else{
            hint = c_Ahint;
        }

        A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                A.setText(R.string.Wrong_answer);
                wrong_answer(A);
            }
        });

        B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                B.setText(R.string.Wrong_answer);
                wrong_answer(B);
            }
        });

        C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    C.setText(R.string.Correct_answer);
                    correct_answer(C);
            }
        });

        D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    D.setText(R.string.Wrong_answer);
                    wrong_answer(D);
            }
        });
        HINT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hint_btn(HINT);
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

    public void correct_answer(View view)
    {
        if(pointAwarded == false){
            temp_score = temp_score + 10;
        }pointAwarded = true;
        user_score = ""+temp_score;
        intent = new Intent(this, question_two_image.class);
        intent.putExtra("USERID", user_id);
        intent.putExtra("USERSCORE", user_score);
        intent.putExtra("USERGENDER", user_gender);
        intent.putExtra("USERLEVEL", user_level);
        intent.putExtra("COLORSCHEME", color_scheme);
        startActivity(intent);
    }

    public void wrong_answer(View view)
    {
        hint_btn(question);
    }

    public void hint_btn(View view)
    {
        if(hint_used != true)
        {
            temp_score = temp_score - 2;
        }
        hint_used = true;
        Toast.makeText(question_one_question.this, hint, Toast.LENGTH_LONG).show();
    }

    public void change_color_scheme(String color, Drawable btn_type){

        first_question.setBackgroundColor(Color.parseColor(color));
        A.setBackground(btn_type);
        B.setBackground(btn_type);
        C.setBackground(btn_type);
        D.setBackground(btn_type);
        HINT.setBackground(btn_type);
    }


}
