package com.example.petya.lessondagger;

import java.net.HttpCookie;

import dagger.Module;
import dagger.Provides;

@Module
public class NetworkModule {

    @Provides
    NetworkUtils provideNetworkUtils() {
        return new NetworkUtils();
    }


}
