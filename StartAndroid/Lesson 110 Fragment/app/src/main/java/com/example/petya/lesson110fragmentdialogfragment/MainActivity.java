package com.example.petya.lesson110fragmentdialogfragment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    Dialog1 mDialog1;
    Dialog2 mDialog2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDialog1 = new Dialog1();
        mDialog2 = new Dialog2();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnDlg1:
                mDialog1.show(getFragmentManager(), "d1g1");
            case R.id.btnDlg2:
                mDialog2.show(getFragmentManager(), "d1g2");
        }
    }
}
