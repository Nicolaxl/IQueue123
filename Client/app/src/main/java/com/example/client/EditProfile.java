package com.example.client;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditProfile extends AppCompatActivity {

    TextView username;
    EditText password;
    String uname,pw;
    Button save;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profile);

        username = (TextView) findViewById(R.id.edit_name_up);
        password = (EditText) findViewById(R.id.edit_password_up);
        save = (Button) findViewById(R.id.btn_save);

        uname = Preference.getUname(EditProfile.this);
        pw = Preference.getPassword(EditProfile.this);

        username.setText(uname);
        password.setText(pw);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pw = password.getText().toString();
                String usname = Preference.getUname(EditProfile.this);
                if(usname.contains("C") == true){
                    Handler handler = new Handler();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //creating array for parameters
                            String[] field = new String[2];
                            field[0] = "Username";
                            field[1] = "Password";
                            //creating array for data
                            String[] data = new String[2];
                            data[0] = usname;
                            data[1] = pw;


                            ConnectDatabase putData = new ConnectDatabase("http://iqueue123.laygolan.com/" + "ChangePW.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    String result = putData.getResult();
                                    System.out.println(result);
                                    if (result.equals("Change password success")) {
                                        Preference.savePassword(pw, EditProfile.this);

                                        Intent intent = new Intent(EditProfile.this, UsersHomepage.class);
                                        startActivity(intent);
                                    } else {
                                        if (result.equals("Error: Database connection"))
                                            Toast.makeText(EditProfile.this, "Error", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        }
                    });
                }else if(usname.contains("A")==true){
                    Handler handler = new Handler();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //creating array for parameters
                            String[] field = new String[2];
                            field[0] = "Username";
                            field[1] = "Password";
                            //creating array for data
                            String[] data = new String[2];
                            data[0] = usname;
                            data[1] = pw;


                            ConnectDatabase putData = new ConnectDatabase("http://iqueue123.laygolan.com/" + "ChangePWA.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    String result = putData.getResult();
                                    System.out.println(result);
                                    if (result.equals("Change password success")) {
                                        Preference.savePassword(pw, EditProfile.this);

                                        Intent intent = new Intent(EditProfile.this, AdminHomepage.class);
                                        startActivity(intent);
                                    } else {
                                        if (result.equals("Error: Database connection"))
                                            Toast.makeText(EditProfile.this, "Error", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        }
                    });
                }

            }
        });


    }

}
