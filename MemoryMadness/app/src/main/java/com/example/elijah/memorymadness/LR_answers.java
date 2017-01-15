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

public class LR_answers extends AppCompatActivity {


    RelativeLayout LR_answers_page;
    Button submit;
    String user_id, user_level, user_gender, user_score, color_scheme;
    String A1, A2, A3, A4, A5;
    EditText input1, input2, input3, input4, input5;
    Intent intent;
    Integer tmp_score;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lr_answers);

        LR_answers_page = (RelativeLayout) findViewById(R.id.lr_answers);
        LR_answers_page.setBackgroundColor(Color.parseColor("#b366ff"));

        submit = (Button) findViewById(R.id.submit_btn);

        intent = getIntent();
        color_scheme = intent.getStringExtra("COLORSCHEME");
        user_id = intent.getStringExtra("USERID");
        user_level = intent.getStringExtra("USERLEVEL");
        user_score = intent.getStringExtra("USERSCORE");
        user_gender = intent.getStringExtra("USERGENDER");

        input1 = (EditText) findViewById(R.id.A1);
        input2 = (EditText) findViewById(R.id.A2);
        input3 = (EditText) findViewById(R.id.A3);
        input4 = (EditText) findViewById(R.id.A4);
        input5 = (EditText) findViewById(R.id.A5);

        A1 = "run";
        A2 = "fly";
        A3 = "jump";
        A4 = "eggs";
        A5 = "legs";

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
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


    public void finish(){

        tmp_score = Integer.parseInt(user_score);

    if (input1.getText().toString().toLowerCase().equals(A1)){
        tmp_score += 10;
    }

    if (input2.getText().toString().toLowerCase().equals(A2)){
        tmp_score += 10;
    }
    if (input3.getText().toString().toLowerCase().equals(A3)){
        tmp_score += 10;
    }
    if (input4.getText().toString().toLowerCase().equals(A4)){
        tmp_score += 10;
    }
    if (input5.getText().toString().toLowerCase().equals(A5)){
        tmp_score += 10;
    }


        intent = new Intent(this, submitted.class);
        intent.putExtra("USERID", user_id);
        intent.putExtra("USERSCORE", ""+tmp_score);
        intent.putExtra("USERGENDER", user_gender);
        intent.putExtra("USERLEVEL", user_level);
        intent.putExtra("COLORSCHEME", color_scheme);
        startActivity(intent);
    }

    public void change_color_scheme(String color, Drawable btn_type){

        LR_answers_page.setBackgroundColor(Color.parseColor(color));
        submit.setBackground(btn_type);
    }

}
