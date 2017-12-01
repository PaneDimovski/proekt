package com.example.anti.proekt;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {


    @BindView(R.id.guest2)
    TextView txt;

    @BindView(R.id.login)
    Button kop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         ButterKnife.bind(this);
    }

    @OnClick(R.id.login)
    public void klik(View v) {

        final Vibrator vibrator = (Vibrator) MainActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(100);

        Intent intent2 = new Intent(this, Main2Activity.class);
        intent2.putExtra("login", true);
        startActivity(intent2);
    }


    @OnClick(R.id.guest2)
    public void klik2(View v) {

        final Vibrator vibrator = (Vibrator) MainActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(100);


        Intent data = new Intent(MainActivity.this, Main3Activity.class);
        User  guest = new User("", "", "Guest", 'm');
        data.putExtra("NAME_EXTRA", guest);dakjsdas
        startActivity(data);

    }

}
