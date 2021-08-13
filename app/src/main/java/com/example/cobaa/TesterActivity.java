package com.example.cobaa;

import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TesterActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {
    private SeekBar seekBar;
    private TextView Persentase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tester2);
        seekBar = findViewById(R.id.seekBar);
        Persentase = findViewById(R.id.persentase);
        seekBar.setOnSeekBarChangeListener(this);
        seekBar.setEnabled(false);
        seekBar.setMax(16);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int value, boolean b) {
        Persentase.setText(String.valueOf(value));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        Toast.makeText(this, "Progress Dimulai", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        Toast.makeText(this, "Progress Berhenti", Toast.LENGTH_SHORT).show();
    }
}
