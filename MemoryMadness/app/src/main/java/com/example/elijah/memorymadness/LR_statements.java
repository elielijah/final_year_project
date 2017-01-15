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

import java.util.Hashtable;

public class LR_statements extends AppCompatActivity {


    RelativeLayout LR_statement_page;
    String user_id, user_level, user_gender, user_score, color_scheme;
    TextView S1, S2, S3, S4, S5;
    String state1, state2, state3, state4, state5;
    Button True, False;
    Integer Question_Counter;
    Hashtable<String, Boolean> answers = new Hashtable<String, Boolean>();
    String[] questions = new String[6];
    Intent intent;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lr_statements);

        LR_statement_page = (RelativeLayout) findViewById(R.id.lr_statements);
        LR_statement_page.setBackgroundColor(Color.parseColor("#b366ff"));

        intent = getIntent();
        color_scheme = intent.getStringExtra("COLORSCHEME");
        user_id = intent.getStringExtra("USERID");
        user_level = intent.getStringExtra("USERLEVEL");
        user_score = intent.getStringExtra("USERSCORE");
        user_gender = intent.getStringExtra("USERGENDER");

        Question_Counter = 1;

        S1 = (TextView) findViewById(R.id.statement1);
        S2 = (TextView) findViewById(R.id.statement2);
        S3 = (TextView) findViewById(R.id.statement3);
        S4 = (TextView) findViewById(R.id.statement4);
        S5 = (TextView) findViewById(R.id.statement5);

        state1 = "Snails can run";
        state2 = "Cars can fly";
        state3 = "Kangaroos can jump";
        state4 = "Chickens lay eggs";
        state5 = "Spiders have 2 legs";



        questions[1] = state1;
        questions[2] = state2;
        questions[3] = state3;
        questions[4] = state4;
        questions[5] = state5;


        answers.put(questions[1], false);
        answers.put(questions[2], false);
        answers.put(questions[3], true);
        answers.put(questions[4], true);
        answers.put(questions[5], false);


        S1.setText(state1);
        S2.setText(state2);
        S3.setText(state3);
        S4.setText(state4);
        S5.setText(state5);

        S2.setVisibility(View.INVISIBLE);
        S3.setVisibility(View.INVISIBLE);
        S4.setVisibility(View.INVISIBLE);
        S5.setVisibility(View.INVISIBLE);

        True = (Button) findViewById(R.id.true_btn);
        True.setText(R.string.True);
        True.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (answers.get(questions[Question_Counter]).equals(true))
                {
                    Toast.makeText(LR_statements.this, R.string.Correct,Toast.LENGTH_SHORT).show();
                    correct_answer();
                }else{
                    Toast.makeText(LR_statements.this, R.string.Incorrect,Toast.LENGTH_SHORT).show();
                }

            }
        });



        False = (Button) findViewById(R.id.false_btn);
        False.setText(R.string.False);
        False.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (answers.get(questions[Question_Counter]).equals(false)){
                    Toast.makeText(LR_statements.this, R.string.Correct,Toast.LENGTH_SHORT).show();
                    correct_answer();
                }else{
                    Toast.makeText(LR_statements.this, R.string.Incorrect,Toast.LENGTH_SHORT).show();
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

    public void correct_answer()
    {
        Question_Counter += 1;
        switch (Question_Counter){

            case 2 :
                S1.setVisibility(View.INVISIBLE);
                S2.setVisibility(View.VISIBLE);
                break;
            case 3 :
                S2.setVisibility(View.INVISIBLE);
                S3.setVisibility(View.VISIBLE);

                break;
            case 4 :
                S3.setVisibility(View.INVISIBLE);
                S4.setVisibility(View.VISIBLE);

                break;
            case 5 :
                S4.setVisibility(View.INVISIBLE);
                S5.setVisibility(View.VISIBLE);
                break;
            case 6 :
                ready_onClick();
                break;
        }
    }

    public void ready_onClick(){

        intent = new Intent(this, LR_answers.class);
        intent.putExtra("USERID", user_id);
        intent.putExtra("USERSCORE", user_score);
        intent.putExtra("USERGENDER", user_gender);
        intent.putExtra("USERLEVEL", user_level);
        intent.putExtra("USERSCORE", user_score);
        intent.putExtra("COLORSCHEME", color_scheme);
        startActivity(intent);
    }

    public void change_color_scheme(String color, Drawable btn_type){

        LR_statement_page.setBackgroundColor(Color.parseColor(color));
        True.setBackground(btn_type);
        False.setBackground(btn_type);

    }

}
