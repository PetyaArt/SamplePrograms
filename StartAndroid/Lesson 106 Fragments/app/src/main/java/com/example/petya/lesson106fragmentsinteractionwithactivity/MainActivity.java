package com.example.petya.lesson106fragmentsinteractionwithactivity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements Fragment2.onSomeEventListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment2 fragment2 = new Fragment2();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragment2, fragment2);
        fragmentTransaction.commit();
    }

    public void onClick(View view) {
        Fragment fragment1 = getFragmentManager().findFragmentById(R.id.fragment1);
        TextView textView = fragment1.getView().findViewById(R.id.textView);
        textView.setText("Access to Fragment 1 from Activity");

        Fragment fragment2 = getFragmentManager().findFragmentById(R.id.fragment2);
        TextView textView1 = fragment2.getView().findViewById(R.id.textView);
        textView1.setText("Access to Fragment 2 from Activity");
    }


    @Override
    public void someEvent(String s) {
        Fragment fragment1 = getFragmentManager().findFragmentById(R.id.fragment1);
        TextView textView = fragment1.getView().findViewById(R.id.textView);
        textView.setText("Text from Fragment 2:" + s);
    }
}
