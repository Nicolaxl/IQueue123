package com.example.client;

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

public class SetCounterAdmin extends AppCompatActivity {
    Button enter;
    EditText number;
    TextView warning;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_counter_admin);

        enter = (Button) findViewById(R.id.entercounterA);
        number = (EditText) findViewById(R.id.numbercounterA);
        warning = (TextView) findViewById(R.id.warningA);
        getAnotherCounter();
        String compare = Preference.getCounterA2(SetCounterAdmin.this);
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nocounter = number.getText().toString();
                if(nocounter.equals(compare)){
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
                            data[0] = Preference.getUname(SetCounterAdmin.this);
                            data[1] = nocounter;
                            ConnectDatabase putData = new ConnectDatabase("http://iqueue123.laygolan.com/" + "SetCounterA.php", "POST", field, data);
                            if (putData.startPut()){
                                if(putData.onComplete()){
                                    String result = putData.getResult();
                                    if (result.equals("Set counter success")){
                                        Preference.saveCounterA(nocounter,SetCounterAdmin.this);
                                        Toast.makeText(SetCounterAdmin.this, "Set counter success", Toast.LENGTH_SHORT).show();
                                    }
                                    else Toast.makeText(SetCounterAdmin.this, "Error", Toast.LENGTH_SHORT).show();
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
                dat[0] = "A1045290";
                ConnectDatabase putData = new ConnectDatabase("http://iqueue123.laygolan.com/" + "ShowCounterA.php", "POST", fld, dat);
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        String result = putData.getResult();
                        if (!result.equals("There's no Counter")) showJSON(result);
                    } else Toast.makeText(SetCounterAdmin.this, "ERROR", Toast.LENGTH_SHORT).show();
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

    private void compareCounter(String counter) {
        Preference.saveCounterA2(counter,SetCounterAdmin.this);
    }

}
