package com.example.facebook.data;

import com.example.facebook.pojo.PostModel;

import java.util.List;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostsClient {
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    private static PostsClient INSTANCE;
    private PostInterface postInterface;

    public PostsClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        postInterface = retrofit.create(PostInterface.class);

    }

    public static PostsClient getINSTANCE() {
        if (INSTANCE == null){
            INSTANCE = new PostsClient();
        }
        return INSTANCE;
    }
    //hyrg3 data mra a7da m4 m7tag on next f hbdl observable to single
    public Single<List<PostModel>> getPosts(){
        return postInterface.getPosts();
    }
}
