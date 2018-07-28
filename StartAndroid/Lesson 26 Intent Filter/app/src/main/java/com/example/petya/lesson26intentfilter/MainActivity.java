package com.example.petya.lesson26intentfilter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button button1;
    private Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent;

        switch (view.getId()){
            case R.id.button:
                intent = new Intent("ru.startandroid.intent.action.showtime");
                startActivity(intent);
                break;
            case R.id.button2:
                intent = new Intent("ru.startandroid.intent.action.showdate");
                startActivity(intent);
                break;
        }
    }
}
