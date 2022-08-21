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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SetCounterUsers extends AppCompatActivity {

    Button enter;
    EditText number;
    TextView warning;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_counter_users);

        enter = (Button) findViewById(R.id.entercounter);
        number = (EditText) findViewById(R.id.numbercounter);
        warning =(TextView) findViewById(R.id.warning);
        getAnotherCounter();
        getAnotherCounter2();
        String compare = Preference.getCounter2(SetCounterUsers.this);
        String compare2 = Preference.getCounter3(SetCounterUsers.this);
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nocounter = number.getText().toString();
                if(nocounter.equals(compare) || nocounter.equals(compare2)){
                    warning.setVisibility(View.VISIBLE);
                }
                else{
                    warning.setVisibility(View.INVISIBLE);
                    Handler handler = new Handler();
                    handler.post(new Runnable(){
                        @Override
                        public void run() {
                            //creating array for parameters
                            String[] field = new String[2];
                            field[0] = "Username";
                            field[1] = "Counter";
                            //creating array for data
                            String[] data = new String[2];
                            data[0] = Preference.getUname(SetCounterUsers.this);
                            data[1] = nocounter;
                            ConnectDatabase putData = new ConnectDatabase("http://iqueue123.laygolan.com/" + "SetCounterU.php", "POST", field, data);
                            if (putData.startPut()){
                                if(putData.onComplete()){
                                    String result = putData.getResult();
                                    if (result.equals("Set counter success")){
                                        Preference.saveCounter(nocounter,SetCounterUsers.this);
                                        Toast.makeText(SetCounterUsers.this, "Set counter success", Toast.LENGTH_SHORT).show();
                                    }
                                    else Toast.makeText(SetCounterUsers.this, "Error", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    });
                }
            }
        });

    }

    private void getAnotherCounter() {
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                //creating array for parameters
                String [] fld = new String[1];
                fld[0] = "Username";
                String [] dat = new String[1];
                dat[0] = "C1038163";
                ConnectDatabase putData = new ConnectDatabase("http://iqueue123.laygolan.com/" + "ShowCounterU.php", "POST", fld, dat);
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        String result = putData.getResult();
                        if (!result.equals("There's no Counter")) showJSON(result);
                    } else Toast.makeText(SetCounterUsers.this, "ERROR", Toast.LENGTH_SHORT).show();
                }
            }

            private void showJSON(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray result = jsonObject.getJSONArray("Result");
                    for (int i = 0; i < result.length(); i++) {
                        JSONObject jo = result.getJSONObject(i);
                        String counter = jo.getString("Counter");
                        compareCounter(counter);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void getAnotherCounter2() {
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                //creating array for parameters
                String [] fld = new String[1];
                fld[0] = "Username";
                String [] dat = new String[1];
                dat[0] = "CYASDEMO";
                ConnectDatabase putData = new ConnectDatabase("http://iqueue123.laygolan.com/" + "ShowCounterU.php", "POST", fld, dat);
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        String result = putData.getResult();
                        if (!result.equals("There's no Counter")) showJSON(result);
                    } else Toast.makeText(SetCounterUsers.this, "ERROR", Toast.LENGTH_SHORT).show();
                }
            }

            private void showJSON(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray result = jsonObject.getJSONArray("Result");
                    for (int i = 0; i < result.length(); i++) {
                        JSONObject jo = result.getJSONObject(i);
                        String counter = jo.getString("Counter");
                        compareCounterSelf(counter);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void compareCounter(String counter) {
        Preference.saveCounter2(counter,SetCounterUsers.this);
    }

    private void compareCounterSelf(String counter) {
        Preference.saveCounter3(counter,SetCounterUsers.this);
    }

    public void onBackPressed(){
        Intent intent = new Intent(SetCounterUsers.this, UsersHomepage.class);
        startActivity(intent);
    }
}
