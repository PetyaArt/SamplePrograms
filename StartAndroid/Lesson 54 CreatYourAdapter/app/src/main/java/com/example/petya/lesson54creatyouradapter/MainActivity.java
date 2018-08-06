package com.example.petya.lesson54creatyouradapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Product> mProductArrayList = new ArrayList<>();
    BoxAdapter mBoxAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fillData();
        mBoxAdapter = new BoxAdapter(this, mProductArrayList);

        ListView listView = findViewById(R.id.lvMain);
        listView.setAdapter(mBoxAdapter);

    }

    private void fillData() {
        int max = 9999;
        int min = 999;
        int range = max - min + 1;
        for (int i = 0; i < 1_000_000; i++) {
            int mRandomNumber = (int)(Math.random() * range) + min;
            mProductArrayList.add(new Product("Product " + i, mRandomNumber, R.mipmap.ic_launcher, false));
        }
    }

    public void showResult(View v){
        String result = "Товары в корзине";

        for (Product p: mBoxAdapter.getBox()){
            if (p.box) {
                result += "\n" + p.name;
            }
        }
        Toast.makeText(this, result, Toast.LENGTH_LONG).show();
    }
}
