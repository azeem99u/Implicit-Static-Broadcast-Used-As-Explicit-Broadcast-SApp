package com.exampleimplicitusedasexplicitbroad;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String ACTION_SEND = "com.exampleimplicitusedasexplicitbroad.ACTION_SEND";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button).setOnClickListener(view -> {
            Intent intent = new Intent(ACTION_SEND);
            //intent.setPackage("");
            PackageManager packageManager = getPackageManager();
            @SuppressLint("QueryPermissionsNeeded")
            List<ResolveInfo> resolveInfos = packageManager.queryBroadcastReceivers(intent,0);

            for (ResolveInfo info :resolveInfos){
                ComponentName componentName = new ComponentName(info.activityInfo.packageName,info.activityInfo.name);
                intent.setComponent(componentName);
                sendBroadcast(intent);

            }

        });
    }
}