package com.example.client;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UsersQueue extends AppCompatActivity {

    public TextView number, nocounter;
    Button reset, call;
    ImageView btn5;
    String send, emerged;
    private TcpClient tcpC;
    String acceptemergency;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.users_queue);

        number = (TextView) findViewById(R.id.queueNum);
        call = (Button) findViewById(R.id.buttonCall);
        btn5 = (ImageView)findViewById(R.id.Emergency);
        nocounter = (TextView) findViewById(R.id.counterno);

        new ConnectTask().execute("");
        number.setText(Preference.getNumber(UsersQueue.this));
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send = "call counter";
                if (tcpC != null) {
                    tcpC.sendMessage(send + " "+ Preference.getCounter(UsersQueue.this));
                }
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emerged = "Emergency";
                if (tcpC != null) {
                    tcpC.sendMessage(emerged);
                }
            }
        });

        if(Preference.getCounter(UsersQueue.this) == null)
            nocounter.setText("0");
        else
            nocounter.setText(Preference.getCounter(UsersQueue.this));

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
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
            if(acceptemergency.contains("ONC")){
                String[] getnumber = acceptemergency.split(" ");
                Preference.saveNumber(getnumber[1],UsersQueue.this);
                number.setText(getnumber[1]);
            }
            else if(acceptemergency.contains("FNC")){
                Toast.makeText(UsersQueue.this,"Server has already send",Toast.LENGTH_SHORT).show();
            }
            else if(acceptemergency.contains("EMPTYQUEUE")){
                Toast.makeText(UsersQueue.this,"There's no waiting line",Toast.LENGTH_SHORT).show();
            }
            else if(acceptemergency.contains("RESET")){
                Toast.makeText(UsersQueue.this,"Number has been reset",Toast.LENGTH_SHORT).show();
                Preference.saveNumber("0",UsersQueue.this);
                number.setText("0");
            }
            else if(acceptemergency.contains("GRANTED")){
                String getnumber2 = acceptemergency.substring(22);
                Preference.saveNumber(getnumber2,UsersQueue.this);
                number.setText(getnumber2);
            }
            else if(acceptemergency.contains("DENY")){
                Toast.makeText(UsersQueue.this,"Can't backward queue because the number of virtual queue is zero",Toast.LENGTH_SHORT).show();
            }
            // notify the adapter that the data set has changed. This means that new message received
            // from server was added to the list
        }
    }


    public void onPause(){
        super.onPause();

        // disconnect
        tcpC.stopClient();
        tcpC = null;
    }

    public void onBackPressed() {
        Intent intent = new Intent(UsersQueue.this, UsersHomepage.class);
        startActivity(intent);
    }
}
