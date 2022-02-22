package com.example.facebook.ui.main;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.facebook.data.PostsClient;
import com.example.facebook.pojo.PostModel;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostViewModel extends ViewModel {
    private static final String TAG="PostViewModel";

    MutableLiveData<List<PostModel>>posListMutableLiveData= new MutableLiveData<List<PostModel>>();

    public void getPosts(){
        /*Call<List<PostModel>> call = PostsClient.getINSTANCE().getPosts();
        call.enqueue(new Callback<List<PostModel>>() {
            @Override
            public void onResponse(Call<List<PostModel>> call, Response<List<PostModel>> response) {
                if (response.code()!=200){
                    Log.e(TAG, "onResponse: no internet");
                }
                posListMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<PostModel>> call, Throwable t) {
                Log.e(TAG, "onFailure: "+t.getMessage() );
            }
        });*/
        Single<List<PostModel>> observable = PostsClient.getINSTANCE().getPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
       /* Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(Object o) {
                posListMutableLiveData.setValue((List<PostModel>) o);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e(TAG, "onError: "+e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        };*/
        observable.subscribe(o->posListMutableLiveData.setValue(o),l-> Log.e(TAG, "getPosts: error : "+l.getMessage() ));

    }
}
