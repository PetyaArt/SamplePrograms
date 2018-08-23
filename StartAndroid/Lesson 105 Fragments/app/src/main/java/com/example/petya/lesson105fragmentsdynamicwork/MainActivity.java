package com.example.petya.lesson105fragmentsdynamicwork;

import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

public class MainActivity extends AppCompatActivity {

    Fragment1 mFragment1;
    Fragment2 mFragment2;
    FragmentTransaction mFragmentTransaction;
    CheckBox mCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFragment1 = new Fragment1();
        mFragment2 = new Fragment2();

        mCheckBox = findViewById(R.id.chbStack);
    }

    public void onClick(View view) {
        mFragmentTransaction = getFragmentManager().beginTransaction();
        switch (view.getId()) {
            case R.id.btnAdd:
                mFragmentTransaction.add(R.id.frgmCont, mFragment1);
                break;
            case R.id.btnRemove:
                mFragmentTransaction.remove(mFragment1);
                break;
            case R.id.btnReplace:
                mFragmentTransaction.replace(R.id.frgmCont, mFragment2);
                break;
        }
        if (mCheckBox.isChecked())
            mFragmentTransaction.addToBackStack(null);
        mFragmentTransaction.commit();
    }
}
