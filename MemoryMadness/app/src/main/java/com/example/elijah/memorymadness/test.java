package com.example.elijah.memorymadness;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.RelativeLayout;

public class test extends AppCompatActivity {

    RelativeLayout test_page;
    Button test_btn;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);

        test_page = (RelativeLayout) findViewById(R.id.test_page);
        test_page.setBackgroundColor(Color.parseColor("#FFFFCC"));



    }



}
