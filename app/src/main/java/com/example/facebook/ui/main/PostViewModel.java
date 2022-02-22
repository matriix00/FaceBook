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

            }

            @Override
            public void onComplete() {

            }
        };*/
        observable.subscribe(o->posListMutableLiveData.setValue(o),l-> Log.e(TAG, "getPosts: "+l ));

    }
}
