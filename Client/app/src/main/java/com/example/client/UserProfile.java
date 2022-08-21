package com.example.client;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class UserProfile extends AppCompatActivity {

    TextView username,password;
    String uname,pw;
    Button change;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);

        username = (TextView) findViewById(R.id.et_name_up);
        password = (TextView) findViewById(R.id.et_password_up);
        change = (Button) findViewById(R.id.btn_up);

        uname = Preference.getUname(UserProfile.this);
        pw = Preference.getPassword(UserProfile.this);

        username.setText(uname);
        password.setText(pw);

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserProfile.this,EditProfile.class);
                startActivity(intent);
            }
        });
    }

    public void onBackPressed(){
        uname = Preference.getUname(UserProfile.this);
        if(uname.contains("C") == true){
            Intent intent = new Intent(UserProfile.this,UsersHomepage.class);
            startActivity(intent);
        }
        else if(uname.contains("A")== true){
            Intent intent = new Intent(UserProfile.this,AdminHomepage.class);
            startActivity(intent);
        }

    }

}
