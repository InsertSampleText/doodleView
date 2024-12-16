package com.example.doodleapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private SeekBar red, blue, green, size, opacity;
    private ImageView colorChoice;
    private Button clearButton, colorButton, rotateButton;
    private DoodleView doodleView;
    int pro1, pro2, pro3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        red = findViewById(R.id.redBar);
        green = findViewById(R.id.greenBar);
        blue = findViewById(R.id.blueBar);
        size = findViewById(R.id.brushBar);
        opacity = findViewById(R.id.opacityBar);

        colorChoice = findViewById(R.id.colorChoice);

        clearButton = findViewById(R.id.clear);
        colorButton = findViewById(R.id.color);
        rotateButton = findViewById(R.id.rotate);

        doodleView = findViewById(R.id.doodleView);

        clearButton.setOnClickListener(v -> doodleView.clearCanvas());

        //size bar
        size.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                doodleView.setBrushSize(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        //opacity bar
        opacity.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                doodleView.setOpacity(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        //color setter

        red.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                pro1 = progress;
                colorChoice.setBackgroundColor(android.graphics.Color.argb(200,pro1, pro2, pro3));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        green.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                pro2 = progress;
                colorChoice.setBackgroundColor(android.graphics.Color.argb(200,pro1, pro2, pro3));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        blue.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                pro3 = progress;
                colorChoice.setBackgroundColor(android.graphics.Color.argb(200,pro1, pro2, pro3));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //sets brush color once pressed
        colorButton.setOnClickListener(v -> doodleView.setColor(android.graphics.Color.argb(200,pro1, pro2, pro3)));

        //rotate button
        rotateButton.setOnClickListener(v -> doodleView.setRotation(doodleView.getRotation() + 10));


    }
}