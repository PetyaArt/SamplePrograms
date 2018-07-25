package com.example.petya.lesson17creatingview_component;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private RadioButton radioButtonLeft;
    private RadioButton radioButtonCenter;
    private RadioButton radioButtonRight;

    private EditText editText;

    private RadioGroup radioGroup;

    private Button buttonCreate;
    private Button buttonClear;

    private LinearLayout linearLayout;

    private int wrapContent = LinearLayout.LayoutParams.WRAP_CONTENT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioButtonLeft = findViewById(R.id.radioButtonLeft);
        radioButtonCenter = findViewById(R.id.radioButtonCenter);
        radioButtonRight = findViewById(R.id.radioButtonRight);

        editText = findViewById(R.id.editText);

        radioGroup = findViewById(R.id.gravity);

        buttonCreate = findViewById(R.id.buttonCreate);
        buttonCreate.setOnClickListener(this);
        buttonClear = findViewById(R.id.buttonClear);
        buttonClear.setOnClickListener(this);

        linearLayout = findViewById(R.id.main);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonCreate:
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(wrapContent, wrapContent);
                int buttonGravity = Gravity.LEFT;
                switch (radioGroup.getCheckedRadioButtonId()){
                    case R.id.radioButtonLeft:
                        buttonGravity = Gravity.LEFT;
                        break;
                    case R.id.radioButtonCenter:
                        buttonGravity = Gravity.CENTER;
                        break;
                    case R.id.radioButtonRight:
                        buttonGravity = Gravity.RIGHT;
                        break;
                }
                layoutParams.gravity = buttonGravity;

                Button button = new Button(this);
                button.setText(editText.getText().toString());

                linearLayout.addView(button, layoutParams);
                break;
            case R.id.buttonClear:
                linearLayout.removeAllViews();
                Toast.makeText(this, "Удалено", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
