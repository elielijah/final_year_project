package com.example.elijah.memorymadness;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

public class NewUser extends AppCompatActivity {

    String UserName,UserAge,UserGender, color_scheme;
    Button nine_or_younger, ten_and_eleven, twelve_plus, submit;
    RelativeLayout new_user_page;
    Intent intent;

    MyDBHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_user);

        new_user_page = (RelativeLayout) findViewById(R.id.new_user_page);
        new_user_page.setBackgroundColor(Color.parseColor("#b366ff"));
        intent = getIntent();
        color_scheme = intent.getStringExtra("COLORSCHEME");
        handler = new MyDBHandler(this, null, null, 1);

        nine_or_younger = (Button)findViewById(R.id.nine_and_below_btn);
        ten_and_eleven = (Button) findViewById(R.id.ten_to_eleven);
        twelve_plus = (Button) findViewById(R.id.twelve_and_above);
        submit = (Button) findViewById(R.id.submit_btn);

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

        nine_or_younger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserAge = "Nine or younger";
            }
        });

        ten_and_eleven.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            UserAge = "Ten or Eleven";
        }
    });

        twelve_plus.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            UserAge = "Twelve and above";
        }
    });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit_onClick(submit);
            }
        });
    }

    public void radioButtonClicked(View view)
    {
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.male_button:
                if (checked)
                    UserGender = "Male";
                break;
            case R.id.female_button:
                if (checked)
                    UserGender = "Female";
                break;
        }

    }

    public void submit_onClick(View v)
    {
        EditText name_input = (EditText) findViewById(R.id.User_name);
        UserName = name_input.getText().toString().trim();

        for(int i = 0; i < UserName.length(); i++){

            if(!Character.isLetter(UserName.toCharArray()[i]))
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                //builder.setTitle("Title");
                builder.setMessage("Please enter a valid username.");

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();

                return;
            }

        }

        if (UserName == null || UserGender == null || UserAge == null){

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
           // builder.setTitle("Title");
            builder.setMessage("Please enter all information.");

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            AlertDialog alertDialog = builder.create();
            alertDialog.show();

        }else{

            Users user = new Users(UserName, UserAge, UserGender);
            handler.addUser(user);

                intent = new Intent(this, SelectUser.class);
                intent.putExtra("username", user.get_username());
                intent.putExtra("userage", user.get_userage());
                intent.putExtra("usergender", user.get_usergender());
                intent.putExtra("COLORSCHEME", color_scheme);
                startActivity(intent);

            }
        }


    public void change_color_scheme(String color, Drawable btn_type){

        new_user_page.setBackgroundColor(Color.parseColor(color));
        nine_or_younger.setBackground(btn_type);
        ten_and_eleven.setBackground(btn_type);
        twelve_plus.setBackground(btn_type);
        submit.setBackground(btn_type);
    }

}




