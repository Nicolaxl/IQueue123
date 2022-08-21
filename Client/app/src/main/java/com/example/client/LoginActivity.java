package com.example.client;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import java.util.Locale;

public class LoginActivity extends AppCompatActivity {

    EditText username, password;
    ProgressDialog login;
    private long backpress;
    Toast toast;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        MaterialButton loginbtn = (MaterialButton) findViewById(R.id.loginbtn);

        login = new ProgressDialog(LoginActivity.this);
        login.setMessage("Logging Account !!");
        login.setCancelable(false);

        //admin and admin
        loginbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String uname = username.getText().toString();
                String pw = password.getText().toString();
                if(uname.equals("") || pw.equals(""))
                    Toast.makeText(LoginActivity.this, "Please Enter All Field", Toast.LENGTH_SHORT).show();
                else if(uname.contains("c") == true || uname.contains("a") == true){
                    uname.toUpperCase();
                }
                else{
                    login.show();

                    if(uname.contains("C") == true){
                        Handler handler = new Handler();
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                //creating array for parameters
                                String [] fld = new String[2];
                                fld[0] = "Username";
                                fld[1] = "Password";
                                String [] dat = new String[2];
                                dat[0] = uname;
                                dat[1] = pw;
                                ConnectDatabase getData = new ConnectDatabase("http://iqueue123.laygolan.com/" + "Login.php", "POST", fld, dat);
                                if (getData.startPut()) {
                                    if (getData.onComplete()) {
                                        String res = getData.getResult();
                                        if(res.equals("Wrong username or password")){
                                            Toast.makeText(LoginActivity.this, "Wrong username or password", Toast.LENGTH_SHORT).show();
                                            login.dismiss();
                                        }
                                        else{
                                            Preference.saveUname(uname,LoginActivity.this);
                                            Preference.savePassword(pw, LoginActivity.this);
                                            Intent intent = new Intent(LoginActivity.this, UsersHomepage.class);
                                            startActivity(intent);
                                            username.setText("");
                                            password.setText("");
                                        }
                                    }
                                    else
                                        Toast.makeText(LoginActivity.this, "Error", Toast.LENGTH_SHORT).show();
                                        login.dismiss();
                                }
                                login.dismiss();
                            }
                        });
                    }
                    else if(uname.contains("A") == true){
                        Handler handler = new Handler();
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                //creating array for parameters
                                String [] fld = new String[2];
                                fld[0] = "Username";
                                fld[1] = "Password";
                                String [] dat = new String[2];
                                dat[0] = uname;
                                dat[1] = pw;
                                ConnectDatabase getData = new ConnectDatabase("http://iqueue123.laygolan.com/" + "LoginAd.php", "POST", fld, dat);
                                if (getData.startPut()) {
                                    if (getData.onComplete()) {
                                        String res = getData.getResult();
                                        if(res.equals("Wrong username or password")){
                                            Toast.makeText(LoginActivity.this, "Wrong username or password", Toast.LENGTH_SHORT).show();
                                            login.dismiss();
                                        }
                                        else{
                                            Preference.saveUname(uname,LoginActivity.this);
                                            Preference.savePassword(pw, LoginActivity.this);
                                            Intent intent = new Intent(LoginActivity.this, AdminHomepage.class);
                                            startActivity(intent);
                                            username.setText("");
                                            password.setText("");
                                        }
                                    }
                                }
                                login.dismiss();
                            }
                        });
                    }
                    else{
                        Toast.makeText(LoginActivity.this, "Wrong username or password", Toast.LENGTH_SHORT).show();
                        login.dismiss();
                    }
                }


            }
        });
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
            toast = Toast.makeText(LoginActivity.this,"Press back again to exit",Toast.LENGTH_SHORT);
            toast.show();
        }

        backpress =System.currentTimeMillis();
    }

}
