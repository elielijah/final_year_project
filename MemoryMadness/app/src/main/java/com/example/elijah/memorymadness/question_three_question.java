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

public class question_three_question extends AppCompatActivity {


    RelativeLayout question_three_question_page;
    TextView question;
    EditText user_input_field;
    String user_input;
    Button SUBMIT,HINT;
    Intent intent;
    String user_id, user_level, user_score, user_gender, color_scheme, hint;
    String c_question, c_answer, c_Bhint, c_Ahint, t_question, t_answer, t_B_hint, t_A_hint, WA1, WA2, WA3;

    int temp_score;
    Boolean hint_used = false, pointAwarded = false;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_three_question);

        question_three_question_page = (RelativeLayout) findViewById(R.id.question_three_question_page);
        question_three_question_page.setBackgroundColor(Color.parseColor("#b366ff"));

        intent = getIntent();
        color_scheme = intent.getStringExtra("COLORSCHEME");
        user_id = intent.getStringExtra("USERID");
        user_level = intent.getStringExtra("USERLEVEL");
        user_gender = intent.getStringExtra("USERGENDER");
        user_score = intent.getStringExtra("USERSCORE");
        c_question = intent.getStringExtra("QUESTION");
        c_answer = intent.getStringExtra("ANSWER");
        c_Bhint = intent.getStringExtra("BHINT");
        c_Ahint = intent.getStringExtra("AHINT");
        t_question = intent.getStringExtra("TWOQUESTION");
        t_answer = intent.getStringExtra("TWOANSWER");
        t_B_hint = intent.getStringExtra("TWOBHINT");
        t_A_hint = intent.getStringExtra("TWOAHINT");
        WA1 = intent.getStringExtra("WA1");
        WA2 = intent.getStringExtra("WA2");
        WA3 = intent.getStringExtra("WA3");

        temp_score = Integer.parseInt(user_score);

        question = (TextView)findViewById(R.id.third_question);
        user_input_field = (EditText) findViewById(R.id.third_question_field);
        HINT = (Button) findViewById(R.id.q3_btn_hint);
        SUBMIT = (Button) findViewById(R.id.submit_btn);

        question.setText(c_question);

        SUBMIT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                user_input = user_input_field.getText().toString();

                if (user_input != null) {

                    if (!user_input.equals(c_answer)) {
                        wrong_answer(SUBMIT);
                    } else {
                        correct_answer(SUBMIT);
                    }
                } else {
                    Toast.makeText(question_three_question.this, "Please enter answer", Toast.LENGTH_LONG).show();
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
        intent = new Intent(this, question_four_question.class);
        intent.putExtra("USERID", user_id);
        intent.putExtra("USERLEVEL", user_level);
        intent.putExtra("USERSCORE", user_score);
        intent.putExtra("USERGENDER", user_gender);
        intent.putExtra("COLORSCHEME", color_scheme);
        intent.putExtra("QUESTION", t_question);
        intent.putExtra("ANSWER", t_answer);
        intent.putExtra("BHINT", t_B_hint);
        intent.putExtra("AHINT", t_A_hint);
        intent.putExtra("WA1", WA1);
        intent.putExtra("WA2", WA2);
        intent.putExtra("WA3", WA3);
        startActivity(intent);
    }

    public void wrong_answer(View view)
    {
        hint_btn(question);
        user_input_field.setText("");
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
        Toast.makeText(question_three_question.this, hint, Toast.LENGTH_LONG).show();
    }

    public void change_color_scheme(String color, Drawable btn_type){

        question_three_question_page.setBackgroundColor(Color.parseColor(color));
        SUBMIT.setBackground(btn_type);
        HINT.setBackground(btn_type);
    }


}
