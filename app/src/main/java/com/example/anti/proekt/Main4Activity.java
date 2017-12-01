package com.example.anti.proekt;

import android.app.PendingIntent;
import android.app.VoiceInteractor;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Main4Activity extends AppCompatActivity {

    @BindView(R.id.connection)
    Button konekt;

    @BindView(R.id.back)
    Button nazad;

    @BindView(R.id.stop)
    Button stop;

    @BindView(R.id.netshow)
    TextView netPoraka;

    BroadcastReceiver receiver;

    MediaPlayer p = new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cetvrt_layout);

        ButterKnife.bind(this);

    }


    @OnClick(R.id.connection)
    public void konekcija(View v) {

        try {
            AssetFileDescriptor afd = getApplicationContext().getAssets().openFd("DialUp.mp3");
            p.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            afd.close();
            p.prepare();
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("Test", "Proverka");
        }
        p.start();

        stop.setVisibility(View.VISIBLE);

        receiver = new Broadcast();
        registerReceiver(receiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

        Intent intent = new Intent(this, Broadcast.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                this.getApplicationContext(), 234324243, intent, 0);

    }

    @OnClick(R.id.stop)
    public void stopM(View v2) {

        p.stop();
    }


    @OnClick(R.id.back)
    public void nazad(View v) {

        final Vibrator vibrator = (Vibrator) Main4Activity.this.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(100);

        Intent netIntent = new Intent();
        netIntent.putExtra("internet", netPoraka.getText().toString());
        setResult(RESULT_OK, netIntent);
        finish();

    }


    @Override
    public void onBackPressed() {

        final Vibrator vibrator = (Vibrator) Main4Activity.this.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(100);

        Intent netIntent = new Intent();
        netIntent.putExtra("internet", netPoraka.getText().toString());
        setResult(RESULT_OK, netIntent);
        finish();
    }

    @Override
    protected void onPause() {

        if (receiver != null) {
            unregisterReceiver(receiver);

        }

        super.onPause();

    }
}
