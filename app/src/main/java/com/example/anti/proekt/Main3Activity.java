package com.example.anti.proekt;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.Image;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Main3Activity extends AppCompatActivity {

    @BindView(R.id.spiner)
    Spinner spin;

    @BindView(R.id.slika)
    ImageView slika;

    BroadcastReceiver receiver;

    @BindView(R.id.edit)
    Button edit;

    @BindView(R.id.add)
    Button add;

    @BindView(R.id.checkconn)
    Button kon;

    @BindView(R.id.namelast)
    TextView namelast;

    @BindView(R.id.netView)
    TextView netPoraka;

    @BindView(R.id.mf)
    RadioGroup mf;

    @BindView(R.id.male2)
    RadioButton m;

    @BindView(R.id.female2)
    RadioButton f;

    char pol = 'N';

    User guest;
    User selected;
    User user;
    User user2;

    int kluc = 1000;
    int kluc1 = 1001;
    int kluc2 = 1002;

    static int REQUEST_CODE = 1000;
    ArrayList<User> listaUseri = new ArrayList<User>();
    private ArrayAdapter<User> mojAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tret_layout);

        ButterKnife.bind(this);
        user = new User();
        user2 = new User();


        Intent intent = getIntent();
        if (intent.hasExtra("EXTRA")) {
            user2 = (User) intent.getSerializableExtra("EXTRA");
            //  namelast.setText(user2.getName().toString() + " " + user2.getLastname().toString());
            listaUseri.add(user2);
        }

        Intent intent1 = getIntent();
        if (intent1.hasExtra("NAME_EXTRA")) {
            guest = (User) intent1.getSerializableExtra("NAME_EXTRA");
            listaUseri.add(guest);
        }

        mojAdapter = new ArrayAdapter<User>(this, android.R.layout.simple_list_item_1, listaUseri);
        spin.setAdapter(mojAdapter);

        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override

            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

                selected = mojAdapter.getItem(position);

                pol = selected.getGender();
                namelast.setText(selected.getName().toString() + " " + selected.getLastname().toString());

                if (pol == 'z') {
                    f.setChecked(true);
                    slika.setImageDrawable(getResources().getDrawable(R.drawable.mujer, null));

                } else {
                    m.setChecked(true);
                    slika.setImageDrawable(getResources().getDrawable(R.drawable.man, null));
                }

                mf.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        int proverka = radioGroup.getCheckedRadioButtonId();

                        switch (proverka) {

                            case R.id.male2:
                                pol = 'm';
                                selected.setGender('m');
                                slika.setImageDrawable(getResources().getDrawable(R.drawable.man, null));

                                break;
                            case R.id.female2:
                                pol = 'z';
                                selected.setGender('z');
                                slika.setImageDrawable(getResources().getDrawable(R.drawable.mujer, null));
                        }
                    }


                });
            }

            @Override

            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @OnClick(R.id.edit)
    public void edtusr(View v) {

        final Vibrator vibrator = (Vibrator) Main3Activity.this.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(100);

        Intent edit = new Intent(this, Main2Activity.class);
        edit.putExtra("EditUser", selected);
        startActivityForResult(edit, kluc1);
    }

    @OnClick(R.id.add)
    public void addusr(View v) {

        final Vibrator vibrator = (Vibrator) Main3Activity.this.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(100);

        Intent add = new Intent(this, Main2Activity.class);
        startActivityForResult(add, kluc);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == kluc) {

            user = (User) data.getSerializableExtra("EXTRA");
            listaUseri.add(user);
        }

        if (resultCode == RESULT_OK && requestCode == kluc1) {

            user = (User) data.getSerializableExtra("EXTRA");

            listaUseri.get((int) spin.getSelectedItemId()).setUsername(user.getUsername());
            listaUseri.get((int) spin.getSelectedItemId()).setLastname(user.getLastname());
            listaUseri.get((int) spin.getSelectedItemId()).setName(user.getName());
            listaUseri.get((int) spin.getSelectedItemId()).setGender(user.getGender());

            pol = user.getGender();

            if (pol == 'z') {
                f.setChecked(true);
                slika.setImageDrawable(getResources().getDrawable(R.drawable.mujer, null));

            } else {
                m.setChecked(true);
                slika.setImageDrawable(getResources().getDrawable(R.drawable.man, null));

            }
            mojAdapter.notifyDataSetChanged();

            namelast.setText(selected.getName().toString() + " " + selected.getLastname().toString());
        }
        else if (resultCode == RESULT_OK && requestCode == kluc2) {

            data.hasExtra("internet");
            netPoraka.setText(data.getExtras().getString("internet"));
//        } else {
//
//            Toast.makeText(this, "No new users", Toast.LENGTH_SHORT).show();
//
        }
    }

    @OnClick(R.id.checkconn)
    public void kon(View v) {

        final Vibrator vibrator = (Vibrator) Main3Activity.this.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(100);

        Intent i = new Intent(Main3Activity.this, Main4Activity.class);
        startActivityForResult(i, kluc2);

    }

}




