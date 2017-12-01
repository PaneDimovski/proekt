package com.example.anti.proekt;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class Broadcast extends BroadcastReceiver {


    private boolean isConnected = false;
    AlertDialog.Builder builder;
    private Context applicationContext;

    @Override
    public void onReceive(final Context context, Intent intent) {


        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("ARE YOU CONNECTED");
        builder.setMessage(isNetworkAvailable(context));
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


                TextView txtView = (TextView) ((Main4Activity) context).findViewById(R.id.netshow);
                txtView.setText(isNetworkAvailable(context));

            }


        });
        builder.create().show();

        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(1000);

    }


    private String isNetworkAvailable(Context context) {

        ConnectivityManager connection = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connection != null) {
            NetworkInfo activNetwork = connection.getActiveNetworkInfo();

            if (activNetwork != null) {
                if (activNetwork.getState() == NetworkInfo.State.CONNECTED) {

                    if (!isConnected) {
                        isConnected = true;

                    }

                    return "YES, you are connected to the Internet";

                }

            }

        }
        isConnected = false;
        return "NO, you are not connected to the Internet";


    }


    public Context getApplicationContext() {
        return applicationContext;
    }
}
