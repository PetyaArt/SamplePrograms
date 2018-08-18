package com.example.petya.lesson115multiplescreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements TitlesFragment.onItemClickListener {

    int position = 0;
    boolean withDetails = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            position = savedInstanceState.getInt("position");
        }

        withDetails = (findViewById(R.id.cont) != null);

        if (withDetails) {
            showDetails(position);
        }

    }

    private void showDetails(int position) {
        if (withDetails) {
            DetailsFragment detailsFragment = (DetailsFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.cont);
            if (detailsFragment == null || detailsFragment.getPosition() != position) {
                detailsFragment = DetailsFragment.newInstance(position);
                getSupportFragmentManager().beginTransaction().replace(R.id.cont, detailsFragment).commit();
            }
        }
        else {
            startActivity(new Intent(this, DetailsActivity.class).putExtra("position", position));
        }
    }


    @Override
    public void itemClick(int position) {
        this.position = position;
        showDetails(position);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("position", position);
    }
}
