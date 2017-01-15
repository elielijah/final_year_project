package com.example.elijah.memorymadness;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class Teacher_Login extends AppCompatActivity {

    RelativeLayout teacher_login;
    EditText email,password;
    Button login;

    String user_email, user_password, correct_email, correct_password;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_login);

        correct_email = "Elijah";
        correct_password = "MMU";

        teacher_login = (RelativeLayout) findViewById(R.id.teacher_login_page);
        teacher_login.setBackgroundColor(Color.parseColor("#b366ff"));

        email = (EditText) findViewById(R.id.email_input);
        password = (EditText) findViewById(R.id.password_input);
        login = (Button) findViewById(R.id.login_btn);
        login.setText(R.string.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                user_email = email.getText().toString().trim();
                user_password = password.getText().toString().trim();

                if (user_email.equals(correct_email) && user_password.equals(correct_password)){
                    check_scores();
                }else{
                    Toast.makeText(Teacher_Login.this, "Incorrect email address/password", Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    public void check_scores(){

        Intent intent = new Intent(this, show_scores.class);
        startActivity(intent);


    }


}
