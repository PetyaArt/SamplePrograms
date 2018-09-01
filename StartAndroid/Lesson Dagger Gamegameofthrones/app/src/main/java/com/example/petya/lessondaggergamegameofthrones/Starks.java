package com.example.petya.lessondaggergamegameofthrones;

import javax.inject.Inject;

public class Starks implements House {

    @Inject //Dagger 2
    public Starks(){
    }

    @Override
    public void prepareForWar() {
        // что-то происходит
        System.out.println(this.getClass().getSimpleName()+" prepared for war");
    }

    @Override
    public void reportForWar() {
        // что-то происходит
        System.out.println(this.getClass().getSimpleName()+" reporting..");
    }
}
