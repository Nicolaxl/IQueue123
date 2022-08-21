package com.example.client;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class UsersHomepage extends AppCompatActivity {
    private long backpress;
    CardView btn1,btn2,btn3,btn4;
    TextView logout,userprof, setcounter;
    Toast toast;
    private TcpClient tcpC;
    String acceptemergency;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.users_menu);

        btn1 = (CardView)findViewById(R.id.ControlQueue);
        btn2 = (CardView)findViewById(R.id.Note);
        btn3 = (CardView)findViewById(R.id.Chatroom);
        btn4 = (CardView)findViewById(R.id.NewsU);

        logout = (TextView)findViewById(R.id.logout);
        userprof = (TextView)findViewById(R.id.userprofile);
        setcounter = (TextView)findViewById(R.id.setcounter);

        setcounter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UsersHomepage.this,SetCounterUsers.class);
                startActivity(intent);
            }
        });

        userprof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UsersHomepage.this,UserProfile.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UsersHomepage.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UsersHomepage.this,Notepad.class);
                startActivity(intent);
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UsersHomepage.this, UsersQueue.class);
                startActivity(intent);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UsersHomepage.this, ChatBotActivity.class);
                startActivity(intent);
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UsersHomepage.this, BingNews.class);
                startActivity(intent);
            }
        });


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

    }



    public void onBackPressed(){


        if(backpress + 2000 > System.currentTimeMillis()){
            toast.cancel();
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);
            finishAffinity();
            finish();
            return;
        }else{
            toast = Toast.makeText(UsersHomepage.this,"Press back again to exit",Toast.LENGTH_SHORT);
            toast.show();
        }

        backpress =System.currentTimeMillis();
    }
}
