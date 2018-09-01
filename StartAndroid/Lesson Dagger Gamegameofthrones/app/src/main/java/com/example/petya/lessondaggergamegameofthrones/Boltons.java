package com.example.petya.lessondaggergamegameofthrones;

import android.util.Log;

import javax.inject.Inject;

public class Boltons implements House {

    @Inject //Dagger 2
    public Boltons(){
    }

    @Override
    public void prepareForWar() {
        // что-то происходит
        Log.d("MyLogs", this.getClass().getSimpleName() +" prepared for war");
    }

    @Override
    public void reportForWar() {
        // что-то происходит
        Log.d("MyLogs", this.getClass().getSimpleName() +" reporting..");
    }
}
