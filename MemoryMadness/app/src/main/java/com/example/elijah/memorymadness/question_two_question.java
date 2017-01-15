package com.example.elijah.memorymadness;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class question_two_question extends AppCompatActivity {


    RelativeLayout question_two_question_page;
    TextView question;
    Button HINT, SUBMIT;
    Intent intent;
    String user_id, user_level, user_score, user_gender, color_scheme, hint;
    String c_question, c_answer, c_Bhint, c_Ahint;
    String temp;

    int temp_score;
    Boolean hint_used = false, pointAwarded = false;

    EditText user_input;
    int user_answer;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_two_question);

        question_two_question_page = (RelativeLayout) findViewById(R.id.question_two_question_page);
        question_two_question_page.setBackgroundColor(Color.parseColor("#b366ff"));

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

        temp_score = Integer.parseInt(user_score);

        question = (TextView)findViewById(R.id.second_question);
        HINT = (Button) findViewById(R.id.q2_btn_hint);
        SUBMIT = (Button) findViewById(R.id.q2_submit);
        user_input = (EditText) findViewById(R.id.user_input);

        question.setText(c_question);

        SUBMIT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             temp = user_input.getText().toString();

                if(temp.equals("")){
                    Toast.makeText(question_two_question.this,"Please enter answer",Toast.LENGTH_LONG).show();
                }else{
                    user_answer = Integer.parseInt(temp);
                    if (temp.equals(c_answer)) {
                        correct_answer(SUBMIT);
                    } else {
                        wrong_answer(SUBMIT);
                    }
                }
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
        intent = new Intent(this, question_three_image.class);
        intent.putExtra("USERID", user_id);
        intent.putExtra("USERSCORE", user_score);
        intent.putExtra("USERLEVEL", user_level);
        intent.putExtra("USERGENDER", user_gender);
        intent.putExtra("COLORSCHEME", color_scheme);

        startActivity(intent);
    }

    public void wrong_answer(View view)
    {
        hint_btn(question);
        user_input.setText("");
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
        Toast.makeText(question_two_question.this, hint, Toast.LENGTH_LONG).show();
    }

    public void change_color_scheme(String color, Drawable btn_type){

        question_two_question_page.setBackgroundColor(Color.parseColor(color));
        HINT.setBackground(btn_type);
        SUBMIT.setBackground(btn_type);

    }
}
