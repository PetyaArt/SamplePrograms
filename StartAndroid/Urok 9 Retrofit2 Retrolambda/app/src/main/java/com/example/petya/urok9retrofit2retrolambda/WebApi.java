package com.example.petya.urok9retrofit2retrolambda;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Path;
import rx.Observable;

public interface WebApi {

    @GET("messages{page}.json")
    Observable<List<Message>> messages(@Path("page") int page);

}
