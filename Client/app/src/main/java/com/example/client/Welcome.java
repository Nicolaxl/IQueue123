package com.example.client;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.Timer;
import java.util.TimerTask;

public class Welcome extends AppCompatActivity {
    private static String TAG;
    public static final int REQUEST_CODE = 123;
    Timer timer;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_iqueue123);
        //startService(new Intent(getApplication(), MyService.class));
        /*service = new MyService();
        serviceIntent = new Intent(this, service.getClass());
        if (!isMyServiceRunning(service.getClass())) startService(serviceIntent);*/

        startActivity();
    }

    /*private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                Log.i ("Service status", "Running");
                return true;
            }
        }
        Log.i ("Service status", "Not running");
        return false;
    }


    @Override
    protected void onDestroy() {
        //stopService(mServiceIntent);
        Intent broadcastIntent = new Intent();
        broadcastIntent.setAction("restartservice");
        broadcastIntent.setClass(this, Restarter.class);
        this.sendBroadcast(broadcastIntent);
        super.onDestroy();
    }*/

    public void startActivity(){
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                intent = new Intent(Welcome.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3000);
    }



}
