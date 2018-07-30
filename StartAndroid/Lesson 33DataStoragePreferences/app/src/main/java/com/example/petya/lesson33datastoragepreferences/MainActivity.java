package com.example.petya.lesson33datastoragepreferences;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText editText;
    Button button1, button2;
    SharedPreferences sPreferences;

    final String SAVED_TEXT = "saved_text";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);

        button1 = findViewById(R.id.buttonSave);
        button2 = findViewById(R.id.buttonLoad);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);

        loadText();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.buttonSave:
                saveText();
                break;
            case R.id.buttonLoad:
                loadText();
                break;
        }
    }

    private void saveText() {
        sPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sPreferences.edit();
        editor.putString(SAVED_TEXT, editText.getText().toString());
        editor.commit();
        Toast.makeText(this, "Text saved", Toast.LENGTH_SHORT).show();
    }

    private void loadText() {
        sPreferences = getPreferences(MODE_PRIVATE);
        String savedText = sPreferences.getString(SAVED_TEXT, "");
        editText.setText(savedText);
        Toast.makeText(this, "Text loaded", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveText();
    }
}
