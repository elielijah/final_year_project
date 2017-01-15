package com.example.elijah.memorymadness;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.RelativeLayout;

public class MainMenu extends AppCompatActivity {

    Button new_user_btn, select_user_btn, color_scheme_btn,setting_user_btn, test_btn;
    RelativeLayout main_menu;
    Intent intent;
    String color_scheme;

    long user_count;
    MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        main_menu = (RelativeLayout) findViewById(R.id.main_menu_page);
        main_menu.setBackgroundColor(Color.parseColor("#b366ff"));

        new_user_btn = (Button) findViewById(R.id.New_user_btn);
        select_user_btn = (Button) findViewById(R.id.select_user_btn);
        setting_user_btn  = (Button) findViewById(R.id.settings_btn);
        color_scheme_btn = (Button) findViewById(R.id.color_scheme);
        test_btn = (Button) findViewById(R.id.test_btn);
        test_btn.setVisibility(View.INVISIBLE);

        intent = getIntent();
        if (color_scheme != null) {
            color_scheme = intent.getStringExtra("COLORSCHEME");
        }else{
            color_scheme = "#b366ff";
        }
        user_count = dbHandler.numb_of_users();

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

        new_user_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new_user();
            }
        });

        select_user_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                select_user();
            }
        });

        color_scheme_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                color_scheme();
            }
        });

        setting_user_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    public void new_user() {

        intent = new Intent(this, NewUser.class);
        intent.putExtra("COLORSCHEME",color_scheme);
        startActivity(intent);
    }

    public void select_user()
    {
       if(user_count == 0){
        intent = new Intent(this, NewUser.class);
       }else{
        intent = new Intent(this, SelectUser.class);
       }
        intent.putExtra("COLORSCHEME",color_scheme);
        startActivity(intent);
    }

    public void color_scheme(){
        intent = new Intent(this, set_color_scheme.class);
        intent.putExtra("COLORSCHEME",color_scheme);
        startActivity(intent);
    }

    public void login()
    {
        intent = new Intent(this, Teacher_Login.class);
        intent.putExtra("COLORSCHEME",color_scheme);
        startActivity(intent);
    }

    public void change_color_scheme(String color, Drawable btn_type){

        main_menu.setBackgroundColor(Color.parseColor(color));
        new_user_btn.setBackground(btn_type);
        select_user_btn.setBackground(btn_type);
        setting_user_btn.setBackground(btn_type);
        color_scheme_btn.setBackground(btn_type);
    }
}
