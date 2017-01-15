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
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class SelectUser extends AppCompatActivity {

    Button button;
    TableLayout user_table;
    MyDBHandler handler;

    String all_users;
    String[] ind_users;
    String[] user_details;
    String id, name, age, gender, color_scheme;
    TableRow[] user_rows;

    String pre_user;

    RelativeLayout select_user_page;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_user);

        select_user_page = (RelativeLayout) findViewById(R.id.select_user_page);
        select_user_page.setBackgroundColor(Color.parseColor("#b366ff"));

        user_table = (TableLayout) findViewById(R.id.user_table);
        handler = new MyDBHandler(this, null, null, 1);

        intent = getIntent();
        color_scheme = intent.getStringExtra("COLORSCHEME");
        pre_user = intent.getStringExtra("USERID");

        if (pre_user == null) {

            all_users = handler.databaseToString();
            ind_users = all_users.split("\n");

            int number_of_users = ind_users.length;

            user_rows = new TableRow[number_of_users];

            for (int i = 0; i < number_of_users; i++) {

                user_rows[i] = new TableRow(this);

                user_details = ind_users[i].split(",");

                id = user_details[0];
                name = user_details[1];
                age = user_details[2];
                gender = user_details[3];

                final Users user = new Users(Integer.parseInt(id), name, age, gender);

                TextView nameView = new TextView(this);
                TextView ageView = new TextView(this);
                TextView genderView = new TextView(this);

                nameView.setText(name);
                ageView.setText(age);
                genderView.setText(gender);

                nameView.setTextColor(Color.BLACK);
                ageView.setTextColor(Color.BLACK);
                genderView.setTextColor(Color.BLACK);

                user_rows[i].addView(nameView);
                user_rows[i].addView(ageView);
                user_rows[i].addView(genderView);

                final Button select_user = new Button(this);
                select_user.setText(R.string.Select);
                select_user.setTag(id);

                select_user.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        begin_play(user);
                    }
                });

                user_rows[i].addView(select_user);

                final Button deleteButton = new Button(this);
                deleteButton.setText(R.string.Delete);
                deleteButton.setTag(id);
                deleteButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        confirm(deleteButton);
                    }
                });
                user_rows[i].addView(deleteButton);

                user_table.addView(user_rows[i]);
            }

        } else {

            all_users = handler.getUserDetails(Integer.parseInt(pre_user));
            user_details = all_users.split(",");

            id = user_details[0];
            name = user_details[1];
            age = user_details[2];
            gender = user_details[3];

            final Users user = new Users(Integer.parseInt(id), name, age, gender);

            TextView nameView = new TextView(this);
            TextView ageView = new TextView(this);
            TextView genderView = new TextView(this);

            nameView.setText(name);
            ageView.setText(age);
            genderView.setText(gender);

            TableRow userRow = new TableRow(this);

            final Button select_user = new Button(this);
            select_user.setText("Select");
            select_user.setTag(id);

            select_user.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    begin_play(user);
                }
            });

            userRow.addView(nameView);
            userRow.addView(ageView);
            userRow.addView(genderView);
            userRow.addView(select_user);

            user_table.addView(userRow);

        }

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

    public void confirm(final View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to delete this user?");

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Integer user_id = Integer.parseInt(""+view.getTag());
                handler.deleteUser(user_id);
                refresh_page();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void refresh_page() {
        Intent intent = new Intent(this, SelectUser.class);
        startActivity(intent);
    }

    public void begin_play(Users user) {
        final Intent intent = new Intent(this, BeginPlay.class);
        intent.putExtra("USERGENDER", user.get_usergender());

        final String user_id = ""+user.get_id();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose difficulty");
        builder.setItems(R.array.level, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                switch (which) {

                    case 0:
                        intent.putExtra("USERLEVEL", "BASIC");
                        intent.putExtra("USERID", user_id);
                        intent.putExtra("COLORSCHEME",color_scheme);
                        startActivity(intent);
                        break;

                    case 1:
                        intent.putExtra("USERLEVEL", "ADVANCED");
                        intent.putExtra("USERID", user_id);
                        intent.putExtra("COLORSCHEME",color_scheme);
                        startActivity(intent);
                        break;
                }

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    public void change_color_scheme(String color, Drawable btn_type){

        select_user_page.setBackgroundColor(Color.parseColor(color));
    }







}