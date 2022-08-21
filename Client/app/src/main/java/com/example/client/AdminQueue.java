package com.example.client;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AdminQueue extends AppCompatActivity {

    public TextView number, nocounter;
    Button reset, backward;
    String back, resetmess;
    private TcpClient tcpC;
    ImageView btn5;
    String acceptemergency;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_queue);

        number = (TextView) findViewById(R.id.queueNumA);
        reset = (Button) findViewById(R.id.buttonResetA);
        backward = (Button) findViewById(R.id.buttonBackward);
        btn5 = (ImageView)findViewById(R.id.EmergencyA);
        nocounter = (TextView) findViewById(R.id.counternoA);

        new ConnectTask().execute("");

        backward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back = "backward counter";
                if (tcpC != null) {
                    tcpC.sendMessage(back + " " + Preference.getCounterA(AdminQueue.this));
                }

            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetmess = "reset counter";
                if (tcpC != null) {
                    tcpC.sendMessage(resetmess);
                }
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emergency = "this is emergency";
                if (tcpC != null) {
                    tcpC.sendMessage(emergency);
                }
            }
        });

        nocounter.setText(Preference.getCounterA(AdminQueue.this));

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // disconnect
        tcpC.stopClient();
        tcpC = null;

    }

    public class ConnectTask extends AsyncTask<String, String, TcpClient> {

        @Override
        protected TcpClient doInBackground(String... message) {

            //we create a TCPClient object and
            tcpC = new TcpClient(new TcpClient.OnMessageReceived() {
                @Override
                //here the messageReceived method is implemented
                public void messageReceived(String message) {
                    //this method calls the onProgressUpdate
                    publishProgress(message);
                }
            });
            tcpC.run();

            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);

            //in the arrayList we add the messaged received from server
            acceptemergency = values[0];
            System.out.println("value: " + acceptemergency);
            if(acceptemergency.contains("ONC")){
                String[] getnumber = acceptemergency.split(" ");
                Preference.saveNumber(getnumber[1],AdminQueue.this);
                number.setText(getnumber[1]);
            }
            else if(acceptemergency.contains("FNC")){
                Toast.makeText(AdminQueue.this,"Server has already send",Toast.LENGTH_SHORT).show();
            }
            else if(acceptemergency.contains("EMPTYQUEUE")){
                Toast.makeText(AdminQueue.this,"There's no waiting line",Toast.LENGTH_SHORT).show();
            }

            else if(acceptemergency.contains("GRANTED")){
                String getnumber2 = acceptemergency.substring(22);
                Preference.saveNumber(getnumber2,AdminQueue.this);
                number.setText(getnumber2);
            }
            else if(acceptemergency.contains("DENY")){
                Toast.makeText(AdminQueue.this,"Can't backward queue because the number of virtual queue is zero",Toast.LENGTH_SHORT).show();
            }
            else if(acceptemergency.contains("RESET|BYADMIN")){
                Toast.makeText(AdminQueue.this,"Number has been reset",Toast.LENGTH_SHORT).show();
                Preference.saveNumber("0",AdminQueue.this);
                number.setText("0");
            }
            // notify the adapter that the data set has changed. This means that new message received
            // from server was added to the list
        }
    }
}
