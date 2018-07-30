package com.example.petya.lesson30moreinformationaboutonactivityresult;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ColorActivity extends AppCompatActivity implements View.OnClickListener {

    Button buttonRed;
    Button buttonGreen;
    Button buttonBlue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color);

        buttonRed = findViewById(R.id.buttonRed);
        buttonBlue = findViewById(R.id.buttonBlue);
        buttonGreen = findViewById(R.id.buttonGreen);

        buttonRed.setOnClickListener(this);
        buttonGreen.setOnClickListener(this);
        buttonBlue.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.buttonRed:
                intent.putExtra("color", Color.RED);
                break;
            case R.id.buttonBlue:
                intent.putExtra("color", Color.BLUE);
                break;
            case R.id.buttonGreen:
                intent.putExtra("color", Color.GREEN);
                break;
        }
        setResult(RESULT_OK, intent);
        finish();
    }
}
