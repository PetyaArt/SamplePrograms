package com.example.petya.lesson14menugrouporder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        checkBox = findViewById(R.id.checkBox);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /*menu.add(0, 1, 0, "add");
        menu.add(0, 2, 0, "edit");
        menu.add(0, 3, 3, "delete");
        menu.add(1, 4, 1, "copy");
        menu.add(1, 5, 2, "paste");
        menu.add(1, 6, 4, "exit");*/
        getMenuInflater().inflate(R.menu.mymenu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.setGroupVisible(R.id.group2, checkBox.isChecked());
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Item Menu");
        stringBuilder.append("\r\n groupId: " + String.valueOf(item.getGroupId()));
        stringBuilder.append("\r\n itemId: " + String.valueOf(item.getItemId()));
        stringBuilder.append("\r\n order: " + String.valueOf(item.getOrder()));
        stringBuilder.append("\r\n title: " + item.getTitle());

        textView.setText(stringBuilder);

        return super.onOptionsItemSelected(item);
    }
}
