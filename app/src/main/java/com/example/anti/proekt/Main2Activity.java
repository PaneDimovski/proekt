package com.example.anti.proekt;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.anti.proekt.Main3Activity.REQUEST_CODE;

public class Main2Activity extends AppCompatActivity {


    @BindView(R.id.next)
    Button kop;

    @BindView(R.id.name)
    EditText edtname;

    @BindView(R.id.last)
    EditText edtlast;

    @BindView(R.id.user)
    EditText edtuser;

    @BindView(R.id.male)
    RadioButton m;

    @BindView(R.id.female)
    RadioButton f;

    User user1;

    User user3;

    char gender = 'N';

    String nextkopce = " ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vtor_layout);


        ButterKnife.bind(this);

        Intent intent = getIntent();
        if (intent.hasExtra("EditUser")) {

            User user2 = (User) intent.getExtras().getSerializable("EditUser");

            edtname.setText(user2.getName());
            edtlast.setText(user2.getLastname());
            edtuser.setText(user2.getUsername());
            gender=user2.getGender();

            if (gender == 'm') {
                m.setChecked(true);
            } else {
                f.setChecked(true);
            }

        }

        if (intent.hasExtra("lista")) {

            User user2 = (User) intent.getExtras().getSerializable("lista");

            edtname.setText(user2.getName());
            edtlast.setText(user2.getLastname());
            edtuser.setText(user2.getUsername());

        }

    }


    @OnClick({R.id.male, R.id.female})
    public void klik2(RadioButton mz) {

        boolean checked = mz.isChecked();

        switch (mz.getId()) {

            case R.id.male:
                if (checked) {
                    gender = 'm';
                }
                break;
            case R.id.female:
                if (checked) {
                    gender = 'z';
                }
                break;

        }

    }


    @OnClick(R.id.next)
    public void klik(View v) {


        final Vibrator vibrator = (Vibrator) Main2Activity.this.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(100);

        user3 = new User();

        user3.setName(edtname.getText().toString());
        user3.setLastname(edtlast.getText().toString());
        user3.setUsername(edtuser.getText().toString());
        user3.setGender(gender);

        Intent intent1 = new Intent(Main2Activity.this, Main3Activity.class);
        intent1.putExtra("EXTRA", user3);

        if (getIntent().hasExtra("login")) {

            startActivity(intent1);

        }
        else {
            setResult(RESULT_OK, intent1);
            finish();
        }

    }

}
