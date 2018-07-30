package com.example.petya.lesson28extras;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText editTextFirst, editTextLast;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextFirst = findViewById(R.id.editTextFirst);
        editTextLast = findViewById(R.id.editTextLast);

        button = findViewById(R.id.button);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, ViewActivity.class);
        intent.putExtra("fname", editTextFirst.getText().toString());
        intent.putExtra("lname", editTextLast.getText().toString());
        startActivity(intent);
    }
}
