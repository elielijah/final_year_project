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

public class question_four_question extends AppCompatActivity {


    RelativeLayout question_four_question_page;
    TextView question;
    Button A, B, C, D, HINT;
    Intent intent;
    String user_id, user_level, user_score, user_gender, color_scheme, hint;
    String c_question, c_answer, c_Bhint, c_Ahint,WA1, WA2, WA3;;

    int temp_score;
    Boolean hint_used = false, pointAwarded = false;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_four_question);

        question_four_question_page = (RelativeLayout) findViewById(R.id.question_four_question_page);
        question_four_question_page.setBackgroundColor(Color.parseColor("#b366ff"));

        intent = getIntent();
        color_scheme = intent.getStringExtra("COLORSCHEME");
        user_id = intent.getStringExtra("USERID");
        user_level = intent.getStringExtra("USERLEVEL");
        user_gender = intent.getStringExtra("USERGENDER");
        user_score = intent.getStringExtra("USERSCORE");

        temp_score = Integer.parseInt(user_score);

        c_question = intent.getStringExtra("QUESTION");
        c_answer = intent.getStringExtra("ANSWER");
        c_Bhint = intent.getStringExtra("BHINT");
        c_Ahint = intent.getStringExtra("AHINT");
        WA1 = intent.getStringExtra("WA1");
        WA2 = intent.getStringExtra("WA2");
        WA3 = intent.getStringExtra("WA3");

        question = (TextView)findViewById(R.id.fourth_question);
        A = (Button) findViewById(R.id.q4_btn_A);
        B = (Button) findViewById(R.id.q4_btn_B);
        C = (Button) findViewById(R.id.q4_btn_C);
        D = (Button) findViewById(R.id.q4_btn_D);
        HINT = (Button) findViewById(R.id.q4_btn_hint);

        question.setText(c_question);

        A.setText(WA1);
        B.setText(c_answer);
        C.setText(WA2);
        D.setText(WA3);

        A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                A.setText("WRONG ANSWER");
                wrong_answer(A);
            }
        });

        B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                B.setText("CORRECT ANSWER");
                correct_answer(B);
            }
        });

        C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                C.setText("WRONG ANSWER");
                wrong_answer(C);

            }
        });

        D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                D.setText("WRONG ANSWER");
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
        intent = new Intent(this, question_five_image.class);
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

        switch (user_level)
        {
            case "BASIC":
                hint = c_Bhint;
                break;

            case "ADVANCED":
                hint = c_Ahint;
                break;
        }
        Toast.makeText(question_four_question.this, hint, Toast.LENGTH_LONG).show();
    }


    public void change_color_scheme(String color, Drawable btn_type){

        question_four_question_page.setBackgroundColor(Color.parseColor(color));
        A.setBackground(btn_type);
        B.setBackground(btn_type);
        C.setBackground(btn_type);
        D.setBackground(btn_type);
        HINT.setBackground(btn_type);

    }


}
