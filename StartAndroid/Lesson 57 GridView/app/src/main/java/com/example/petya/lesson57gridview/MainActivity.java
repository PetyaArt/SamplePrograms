package com.example.petya.lesson57gridview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.GridView;

public class MainActivity extends AppCompatActivity {

    String[] data = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k"};

    GridView mGridView;
    ArrayAdapter<String> mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAdapter = new ArrayAdapter<>(this, R.layout.item, R.id.textView, data);

        mGridView = findViewById(R.id.gridView);
        mGridView.setAdapter(mAdapter);
        adjustGridView();
    }

    private void adjustGridView() {
        mGridView.setNumColumns(mGridView.AUTO_FIT);
        mGridView.setColumnWidth(80);
        mGridView.setVerticalSpacing(5);
        mGridView.setHorizontalSpacing(5);
        mGridView.setStretchMode(GridView.STRETCH_SPACING_UNIFORM );
    }

}
