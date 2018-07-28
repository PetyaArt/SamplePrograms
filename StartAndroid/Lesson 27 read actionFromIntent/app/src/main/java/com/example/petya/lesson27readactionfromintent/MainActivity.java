package com.example.petya.lesson27readactionfromintent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonTime = findViewById(R.id.buttonTime);
        Button buttonDate = findViewById(R.id.buttonDate);

        buttonTime.setOnClickListener(this);
        buttonDate.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent;

        switch (view.getId()) {
            case R.id.buttonTime:
                intent = new Intent("ru.startandroid.intent.action.showtime");
                startActivity(intent);
                break;
            case R.id.buttonDate:
                intent = new Intent("ru.startandroid.intent.action.showdate");
                startActivity(intent);
                break;
        }
    }
}
