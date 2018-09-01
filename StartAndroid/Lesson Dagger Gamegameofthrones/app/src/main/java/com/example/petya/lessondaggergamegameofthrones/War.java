package com.example.petya.lessondaggergamegameofthrones;

import javax.inject.Inject;

public class War {

    private Starks starks;
    private Boltons boltons;

    @Inject
    public War(Starks starks, Boltons bolton){
        this.starks = starks;
        this.boltons = bolton;
    }

    public void prepare(){
        starks.prepareForWar();
        boltons.prepareForWar();
    }

    public void report(){
        starks.reportForWar();
        boltons.reportForWar();
    }
}
