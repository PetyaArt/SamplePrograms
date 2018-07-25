package com.example.petya.lesson15contextmenu;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textViewColor;
    private TextView textViewSize;

    final int MENU_COLOR_RED = 1;
    final int MENU_COLOR_GREEN = 2;
    final int MENU_COLOR_BLUE = 3;

    final int MENU_SIZE_22 = 4;
    final int MENU_SIZE_26 = 5;
    final int MENU_SIZE_30 = 6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewColor = findViewById(R.id.textViewColor);
        textViewSize = findViewById(R.id.textViewSize);

        // для них необходимо создавать контекстное меню
        registerForContextMenu(textViewColor);
        registerForContextMenu(textViewSize);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        switch (v.getId()) {
            case R.id.textViewColor:
                /*menu.add(0, MENU_COLOR_RED, 0, "Red");
                menu.add(0, MENU_COLOR_GREEN, 0, "Green");
                menu.add(0, MENU_COLOR_BLUE, 0, "Blue");*/
                getMenuInflater().inflate(R.menu.mymenu, menu);
                break;
            case R.id.textViewSize:
                /*menu.add(0, MENU_SIZE_22, 0, "22");
                menu.add(0, MENU_SIZE_26, 0, "26");
                menu.add(0, MENU_SIZE_30, 0, "30");*/
                getMenuInflater().inflate(R.menu.mymenu2, menu);
                break;
        }
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.id_red:
                textViewColor.setTextColor(Color.RED);
                textViewColor.setText("Text color  = red");
                break;
            case R.id.id_green:
                textViewColor.setTextColor(Color.GREEN);
                textViewColor.setText("Text color  = green");
                break;
            case R.id.id_blue:
                textViewColor.setTextColor(Color.BLUE);
                textViewColor.setText("Text color  = blue");
                break;
            case R.id.id_22:
                textViewSize.setTextSize(22);
                textViewSize.setText("Text size = 22");
                break;
            case R.id.id_26:
                textViewSize.setTextSize(26);
                textViewSize.setText("Text size = 26");
                break;
            case R.id.id_30:
                textViewSize.setTextSize(30);
                textViewSize.setText("Text size = 30");
                break;

            /*case MENU_COLOR_RED:
                textViewColor.setTextColor(Color.RED);
                textViewColor.setText("Text color  = red");
                break;
            case MENU_COLOR_GREEN:
                textViewColor.setTextColor(Color.GREEN);
                textViewColor.setText("Text color  = green");
                break;
            case MENU_COLOR_BLUE:
                textViewColor.setTextColor(Color.BLUE);
                textViewColor.setText("Text color  = blue");
                break;
            case MENU_SIZE_22:
                textViewSize.setTextSize(22);
                textViewSize.setText("Text size = 22");
                break;
            case MENU_SIZE_26:
                textViewSize.setTextSize(26);
                textViewSize.setText("Text size = 26");
                break;
            case MENU_SIZE_30:
                textViewSize.setTextSize(30);
                textViewSize.setText("Text size = 30");
                break;*/
        }
        return super.onContextItemSelected(item);
    }
}
