package com.example.petya.criminalintent.activity;

import android.support.v4.app.Fragment;

import com.example.petya.criminalintent.R;
import com.example.petya.criminalintent.fragment.CrimeListFragment;

public class CrimeListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_twopane;
    }
}
