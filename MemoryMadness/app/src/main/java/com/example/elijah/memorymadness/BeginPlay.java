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

public class BeginPlay extends AppCompatActivity {

    RelativeLayout begin_play;
    Button begin_btn;
    Intent intent;
    String user_id, user_level, user_gender, user_score, intro, color_scheme;
    TextView introduction;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.begin_play);

        begin_play = (RelativeLayout) findViewById(R.id.begin_play_page);
        begin_play.setBackgroundColor(Color.parseColor("#b366ff"));

        begin_btn = (Button) findViewById(R.id.begin_play_btn);
        introduction = (TextView) findViewById(R.id.introduction);

        intro = "For the next 5 questions you are asked to observe  as much information as you can for each question";

        introduction.setText(intro);
        introduction.setTextColor(Color.BLACK);
        introduction.setWidth(1000);
        introduction.setPadding(100, 0, 0, 0);
        introduction.setTextSize(20);

        begin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start();
            }
        });

        intent = getIntent();
        color_scheme = intent.getStringExtra("COLORSCHEME");
        user_id = intent.getStringExtra("USERID");
        user_level = intent.getStringExtra("USERLEVEL");
        user_gender = intent.getStringExtra("USERGENDER");
        user_score = "0";

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

    public void start()
    {
        intent = new Intent(this, question_one_image.class);
        intent.putExtra("USERID", user_id);
        intent.putExtra("USERLEVEL", user_level);
        intent.putExtra("USERGENDER", user_gender);
        intent.putExtra("USERSCORE", user_score);
        intent.putExtra("COLORSCHEME",color_scheme);
        startActivity(intent);
    }

    public void change_color_scheme(String color, Drawable btn_type){

        begin_play.setBackgroundColor(Color.parseColor(color));
        begin_btn.setBackground(btn_type);
    }


}
