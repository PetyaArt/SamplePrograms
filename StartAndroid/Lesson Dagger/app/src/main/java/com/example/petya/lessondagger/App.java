package com.example.petya.lessondagger;

import android.app.Application;

public class App extends Application {

    private static AppComponent sComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        sComponent = DaggerAppComponent.create();
    }

    public static AppComponent getComponent() {
        return sComponent;
    }
}
