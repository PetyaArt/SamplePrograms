package com.example.petya.urok9retrofit2retrolambda;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl("https://rawgit.com/startandroid/data/master/messages/")
                .build();

        WebApi webApi = retrofit.create(WebApi.class);

        Observable<List<Message>> observable = webApi.messages(1);

        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Message>>() {
                    @Override
                    public void onCompleted() {
                        Log.d("myLogs", "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("myLogs", "onError");
                    }

                    @Override
                    public void onNext(List<Message> messages) {
                        Log.d("myLogs", "onNext " + messages);
                    }
                });
    }
}
