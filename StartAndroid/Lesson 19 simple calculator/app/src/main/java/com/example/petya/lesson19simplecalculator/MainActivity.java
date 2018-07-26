package com.example.petya.lesson19simplecalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText editText1, editText2;
    private TextView textView1;

    private Button button1, button2, button3, button4;

    private String oper = "";

    private final int MENU_RESET_ID = 1;
    private final int MENU_QUIT_ID = 2;

    @Override
    public boolean onCreatePanelMenu(int featureId, Menu menu) {
        menu.add(0, MENU_RESET_ID, 0, "Reset");
        menu.add(0, MENU_QUIT_ID, 0, "Quit");
        return super.onCreatePanelMenu(featureId, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case MENU_RESET_ID:
                textView1.setText("");
                editText1.setText("");
                editText2.setText("");
                break;
            case MENU_QUIT_ID:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);
        textView1 = findViewById(R.id.textView);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {
        float num1 = 0;
        float num2 = 0;
        float result = 0;

        if (TextUtils.isEmpty(editText1.getText()) || TextUtils.isEmpty(editText2.getText())) {
            return;
        }

        num1 = Float.parseFloat(editText1.getText().toString());
        num2 = Float.parseFloat(editText2.getText().toString());

        switch (view.getId()) {
            case R.id.button1:
                oper = "+";
                result = num1 + num2;
                break;
            case R.id.button2:
                oper = "-";
                result = num1 - num2;
                break;
            case R.id.button3:
                oper = "*";
                result = num1 * num2;
                break;
            case R.id.button4:
                if (num2 == 0.0){
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
                    textView1.setText("");
                    return;
                }
                else {
                    oper = "/";
                    result = num1 / num2;
                }
                break;
        }

        textView1.setText(num1 + " " + oper + " " + num2 + " = " + result);
    }
}
