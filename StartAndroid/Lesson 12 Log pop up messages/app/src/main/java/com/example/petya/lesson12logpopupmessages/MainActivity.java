package com.example.petya.lesson12logpopupmessages;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "myLogs";
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "найдем View-элементы");
        textView = findViewById(R.id.tvOut);
        Button buttonOk = findViewById(R.id.buttonOk);
        Button buttonCancel = findViewById(R.id.buttonCancel);

        Log.d(TAG, "присваиваем обработчик кнопкам");
        buttonOk.setOnClickListener(this);
        buttonCancel.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        Log.d(TAG, "по id определяем кнопку, вызвавшую этот обработчик");
        switch (view.getId()) {
            case R.id.buttonOk:
                Log.d(TAG, "кнопка ОК");
                textView.setText("Нажата кнопка ОК");
                Toast.makeText(this, "Нажата кнопка ОК", Toast.LENGTH_SHORT).show();
                break;
            case R.id.buttonCancel:
                Log.d(TAG, "кнопка Cancel");
                textView.setText("Нажата кнопка Cancel");
                Toast.makeText(this, "Нажата кнопка Cancel", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
