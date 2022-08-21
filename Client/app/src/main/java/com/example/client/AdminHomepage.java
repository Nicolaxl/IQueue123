package com.example.client;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class AdminHomepage extends AppCompatActivity {
    private long backpress;
    CardView btn1,btn2,btn3,btn4;
    TextView logout,userprof,setcounter;
    float xD = 0;
    Toast toast;
    public TcpClient tcpC;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_menu);

        btn1 = (CardView)findViewById(R.id.ControlQueueA);
        btn2 = (CardView)findViewById(R.id.NoteA);
        btn3 = (CardView)findViewById(R.id.ChatroomA);
        btn4 = (CardView)findViewById(R.id.NewsA);
        logout = (TextView)findViewById(R.id.logoutA);
        userprof = (TextView)findViewById(R.id.userprofileA);
        setcounter = (TextView)findViewById(R.id.setcounterA);

        setcounter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminHomepage.this,SetCounterAdmin.class);
                startActivity(intent);
            }
        });

        userprof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminHomepage.this,UserProfile.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminHomepage.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminHomepage.this,Notepad.class);
                startActivity(intent);
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminHomepage.this, AdminQueue.class);
                startActivity(intent);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminHomepage.this, ChatBotActivity.class);
                startActivity(intent);
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminHomepage.this, BingNews.class);
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
            toast = Toast.makeText(AdminHomepage.this,"Press back again to exit",Toast.LENGTH_SHORT);
            toast.show();
        }

        backpress =System.currentTimeMillis();
    }
}
