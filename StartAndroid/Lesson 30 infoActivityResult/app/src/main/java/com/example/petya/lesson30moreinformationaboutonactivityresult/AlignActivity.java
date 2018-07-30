package com.example.petya.lesson30moreinformationaboutonactivityresult;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;

public class AlignActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnLeft;
    Button btnCenter;
    Button btnRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_align);

        btnLeft = findViewById(R.id.buttonLeft);
        btnCenter = findViewById(R.id.buttonCenter);
        btnRight = findViewById(R.id.buttonRight);

        btnLeft.setOnClickListener(this);
        btnCenter.setOnClickListener(this);
        btnRight.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.buttonLeft:
                intent.putExtra("alignment", Gravity.LEFT);
                break;
            case R.id.buttonCenter:
                intent.putExtra("alignment", Gravity.CENTER);
                break;
            case R.id.buttonRight:
                intent.putExtra("alignment", Gravity.RIGHT);
                break;
        }
        setResult(RESULT_OK, intent);
        finish();
    }
}
