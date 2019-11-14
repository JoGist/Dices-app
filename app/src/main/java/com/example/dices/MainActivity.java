package com.example.dices;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;
import android.graphics.Color;
import android.view.View.OnTouchListener;

    public class MainActivity extends AppCompatActivity {

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RelativeLayout background = findViewById(R.id.background);
        final ImageView closeSettings = findViewById(R.id.settingsClose);
        final Button rolldice = findViewById(R.id.rolldice);
        final ImageView settingsIcon = findViewById(R.id.settingsIcon);
        final Switch toggleDarkMode = findViewById(R.id.toggleDarkMode);
        final Spinner rollmethod = findViewById(R.id.rolltypespinner);
        final RelativeLayout settingsPanel = findViewById(R.id.settingsPanel);
        final TextView toptext = findViewById(R.id.toptext);
        //final MediaPlayer rollingSound = MediaPlayer.create(this, R.raw.dice);
        final MediaPlayer rollingSound = MediaPlayer.create(this, R.raw.stonks);
        rollmethod.setSelection(1);

        toggleDarkMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(toggleDarkMode.isChecked()) {
                    background.setBackgroundColor(Color.parseColor("Black"));
                    settingsIcon.setImageResource(R.drawable.settings_w);
                    toptext.setTextColor(Color.parseColor("Black")); }
                else {
                    background.setBackgroundColor(Color.parseColor("White"));
                    settingsIcon.setImageResource(R.drawable.settings_b);
                    toptext.setTextColor(Color.parseColor("White"));
                } } } );

        settingsIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (settingsPanel.getVisibility() == View.VISIBLE)
                { settingsPanel.setVisibility(View.INVISIBLE);
                    //rolldice.setVisibility(View.VISIBLE);
                  settingsIcon.setVisibility(View.VISIBLE); }
                else
                { settingsPanel.setVisibility(View.VISIBLE);
                    //rolldice.setVisibility(View.INVISIBLE);
                  settingsIcon.setVisibility(View.INVISIBLE); }
            } } );

        closeSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settingsPanel.setVisibility(View.INVISIBLE);
                settingsIcon.setVisibility(View.VISIBLE);
            } } );

        rollmethod.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                RollMethod();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                RollMethod();
            }
        });


        rolldice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toptext.setVisibility(View.INVISIBLE);
                //if(toggleSound.isChecked())
                    rollingSound.start();
                higherDice();
            } } ); }

    int dice1score() {
        return new Random().nextInt(6) + 1;
    }

    int dice2score() {
        return new Random().nextInt(6) + 1;
    }

    @SuppressLint("ClickableViewAccessibility")
    void RollMethod() {
        Spinner rollmethod = findViewById(R.id.rolltypespinner);
        Button rolldice = findViewById(R.id.rolldice);
        RelativeLayout background = findViewById(R.id.background);
        String currValue = String.valueOf(rollmethod.getSelectedItem());
        TextView toptext = findViewById(R.id.toptext);
            switch (currValue) {
                case "Tap":
                    Toast.makeText(getApplicationContext(), "Tap mode selected", Toast.LENGTH_SHORT).show();
                    rolldice.setVisibility(View.INVISIBLE);
                    toptext.setText(R.string.tap);
                    toptext.setVisibility(View.VISIBLE);
                    background.setOnTouchListener(new OnTouchListener() {
                        @SuppressWarnings("ClickableViewAccessibility")
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            switch (event.getAction()) {
                                case MotionEvent.ACTION_DOWN:
                                    higherDice();
                                    return true;

                                case MotionEvent.ACTION_UP:
                                    return true;
                            }
                            return false;
                        }

                    });
                    break;
                case "Shake":
                     Toast.makeText(getApplicationContext(), "Shake mode selected", Toast.LENGTH_SHORT).show();
                    rolldice.setVisibility(View.INVISIBLE);
                    toptext.setVisibility(View.VISIBLE);
                    toptext.setText(R.string.shake);
                    break;
                case "Button":
                default:
                    Toast.makeText(getApplicationContext(), "Button mode selected", Toast.LENGTH_SHORT).show();
                    toptext.setText(R.string.button);
                    toptext.setVisibility(View.VISIBLE);
                    rolldice.setVisibility(View.VISIBLE);
                    break;
            }
    }

    void higherDice() {
        int x = dice1score();
        int y = dice2score();
        if (x>=y) {
            dice1(x);
            dice2(y);
            //Toast.makeText(getApplicationContext(), "dice1 maggiore" + x + y, Toast.LENGTH_SHORT).show();
        }
        else {
            dice1inv(y);
            dice2inv(x);
            //Toast.makeText(getApplicationContext(), "dice2 maggiore" + x + y, Toast.LENGTH_SHORT).show();
        } }

    void dice1(int x) {
        ImageView dice1 = findViewById(R.id.dice1);
        Switch toggleDarkMode = findViewById(R.id.toggleDarkMode);
        if (x==1) {
            if(toggleDarkMode.isChecked())
                 dice1.setImageResource(R.drawable.dice_one_w);
            else dice1.setImageResource(R.drawable.dice_one_b); }
        else if(x==2) {
            if(toggleDarkMode.isChecked())
                dice1.setImageResource(R.drawable.dice_two_w);
            else dice1.setImageResource(R.drawable.dice_two_b); }
        else if(x==3) {
            if(toggleDarkMode.isChecked())
                dice1.setImageResource(R.drawable.dice_three_w);
            else dice1.setImageResource(R.drawable.dice_three_b); }
        else if(x==4) {
            if(toggleDarkMode.isChecked())
                dice1.setImageResource(R.drawable.dice_four_w);
            else dice1.setImageResource(R.drawable.dice_four_b); }
        else if(x==5) {
            if(toggleDarkMode.isChecked())
                dice1.setImageResource(R.drawable.dice_five_w);
            else dice1.setImageResource(R.drawable.dice_five_b); }
        else if(x==6) {
            if(toggleDarkMode.isChecked())
                dice1.setImageResource(R.drawable.dice_six_w);
            else dice1.setImageResource(R.drawable.dice_six_b); }
        else Toast.makeText(getApplicationContext(), "Dice 1 error", Toast.LENGTH_SHORT).show();
    }

    void dice1inv(int y) {
        ImageView dice2 = findViewById(R.id.dice1);
        Switch toggleDarkMode = findViewById(R.id.toggleDarkMode);
        if (y==1) {
            if(toggleDarkMode.isChecked())
                dice2.setImageResource(R.drawable.dice_one_w);
            else dice2.setImageResource(R.drawable.dice_one_b); }
        else if(y==2) {
            if(toggleDarkMode.isChecked())
                dice2.setImageResource(R.drawable.dice_two_w);
            else dice2.setImageResource(R.drawable.dice_two_b); }
        else if(y==3) {
            if(toggleDarkMode.isChecked())
                dice2.setImageResource(R.drawable.dice_three_w);
            else dice2.setImageResource(R.drawable.dice_three_b); }
        else if(y==4) {
            if(toggleDarkMode.isChecked())
                dice2.setImageResource(R.drawable.dice_four_w);
            else dice2.setImageResource(R.drawable.dice_four_b); }
        else if(y==5) {
            if(toggleDarkMode.isChecked())
                dice2.setImageResource(R.drawable.dice_five_w);
            else dice2.setImageResource(R.drawable.dice_five_b); }
        else if(y==6) {
            if(toggleDarkMode.isChecked())
                dice2.setImageResource(R.drawable.dice_six_w);
            else dice2.setImageResource(R.drawable.dice_six_b); }
        else Toast.makeText(getApplicationContext(), "Dice 2 error", Toast.LENGTH_SHORT).show();
    }

    void dice2(int y) {
        ImageView dice2 = findViewById(R.id.dice2);
        Switch toggleDarkMode = findViewById(R.id.toggleDarkMode);
        if (y==1) {
            if(toggleDarkMode.isChecked())
                dice2.setImageResource(R.drawable.dice_one_w);
            else dice2.setImageResource(R.drawable.dice_one_b); }
        else if(y==2) {
            if(toggleDarkMode.isChecked())
                dice2.setImageResource(R.drawable.dice_two_w);
            else dice2.setImageResource(R.drawable.dice_two_b); }
        else if(y==3) {
            if(toggleDarkMode.isChecked())
                dice2.setImageResource(R.drawable.dice_three_w);
            else dice2.setImageResource(R.drawable.dice_three_b); }
        else if(y==4) {
            if(toggleDarkMode.isChecked())
                dice2.setImageResource(R.drawable.dice_four_w);
            else dice2.setImageResource(R.drawable.dice_four_b); }
        else if(y==5) {
            if(toggleDarkMode.isChecked())
                dice2.setImageResource(R.drawable.dice_five_w);
            else dice2.setImageResource(R.drawable.dice_five_b); }
        else if(y==6) {
            if(toggleDarkMode.isChecked())
                dice2.setImageResource(R.drawable.dice_six_w);
            else dice2.setImageResource(R.drawable.dice_six_b); }
        else Toast.makeText(getApplicationContext(), "Dice 2 error", Toast.LENGTH_SHORT).show();
    }

    void dice2inv(int x) {
        ImageView dice1 = findViewById(R.id.dice2);
        Switch toggleDarkMode = findViewById(R.id.toggleDarkMode);
        if (x==1) {
            if(toggleDarkMode.isChecked())
                dice1.setImageResource(R.drawable.dice_one_w);
            else dice1.setImageResource(R.drawable.dice_one_b); }
        else if(x==2) {
            if(toggleDarkMode.isChecked())
                dice1.setImageResource(R.drawable.dice_two_w);
            else dice1.setImageResource(R.drawable.dice_two_b); }
        else if(x==3) {
            if(toggleDarkMode.isChecked())
                dice1.setImageResource(R.drawable.dice_three_w);
            else dice1.setImageResource(R.drawable.dice_three_b); }
        else if(x==4) {
            if(toggleDarkMode.isChecked())
                dice1.setImageResource(R.drawable.dice_four_w);
            else dice1.setImageResource(R.drawable.dice_four_b); }
        else if(x==5) {
            if(toggleDarkMode.isChecked())
                dice1.setImageResource(R.drawable.dice_five_w);
            else dice1.setImageResource(R.drawable.dice_five_b); }
        else if(x==6) {
            if(toggleDarkMode.isChecked())
                dice1.setImageResource(R.drawable.dice_six_w);
            else dice1.setImageResource(R.drawable.dice_six_b); }
        else Toast.makeText(getApplicationContext(), "Dice 1 error", Toast.LENGTH_SHORT).show();
    } }
