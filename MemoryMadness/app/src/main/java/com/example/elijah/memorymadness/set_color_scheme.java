package com.example.elijah.memorymadness;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class set_color_scheme extends AppCompatActivity {

    RelativeLayout set_color_scheme;

    Button Default, option1, option2, option3, Main_Menu;
    String chosen_colour = "";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_color_scheme);

        set_color_scheme = (RelativeLayout) findViewById(R.id.set_color_scheme);
        set_color_scheme.setBackgroundColor(Color.parseColor("#b366ff"));

        Default = (Button) findViewById(R.id.Default);
        option1 = (Button) findViewById(R.id.option1);
        option2 = (Button) findViewById(R.id.option2);
        option3 = (Button) findViewById(R.id.option3);
        Main_Menu = (Button) findViewById(R.id.Main_menu);

        Default.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chosen_colour = "#b366ff";
                set_color(chosen_colour, getResources().getDrawable(R.drawable.custom_button));
            }
        });

        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chosen_colour = "#FFAAFF";
                set_color(chosen_colour, getResources().getDrawable(R.drawable.cs1));
            }
        });

        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chosen_colour = "#CCEEFF";
                set_color(chosen_colour, getResources().getDrawable(R.drawable.cs2));
            }
        });

        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chosen_colour = "#FFFFCC";
                set_color(chosen_colour, getResources().getDrawable(R.drawable.cs3));
            }
        });

        Main_Menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            main_menu();
            }
        });
    }


    public void set_color(String color, Drawable btn_type)
    {
        set_color_scheme.setBackgroundColor(Color.parseColor(color));
        Default.setBackground(btn_type);
        option1.setBackground(btn_type);
        option2.setBackground(btn_type);
        option3.setBackground(btn_type);
        Main_Menu.setBackground(btn_type);
    }

    public void main_menu(){

        Intent intent = new Intent(this, MainMenu.class);
        intent.putExtra("COLORSCHEME", chosen_colour);
        startActivity(intent);
    }


}
