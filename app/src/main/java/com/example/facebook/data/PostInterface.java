package com.example.facebook.data;

import com.example.facebook.pojo.PostModel;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.http.GET;

public interface PostInterface {
    @GET("posts/")
    Single<List<PostModel>> getPosts();
}
