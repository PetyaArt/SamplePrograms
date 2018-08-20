package com.example.petya.lesson115multiplescreen;

import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

public class DetailsActivity extends FragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE && isLarge()) {
            finish();
            return;
        }

        if (savedInstanceState == null) {
            DetailsFragment detailsFragment = DetailsFragment.newInstance(getIntent().getIntExtra("position", 0));
            getSupportFragmentManager().beginTransaction().add(android.R.id.content, detailsFragment).commit();
        }
    }

    private boolean isLarge() {
        return (getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }
}
