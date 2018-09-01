package com.example.petya.lessondagger;

import dagger.Component;

@Component(modules = {StorageModule.class, NetworkModule.class})
public interface AppComponent {
    void injectsMainActivity(MainActivity mainActivity);
}
