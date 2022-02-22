package com.example.facebook.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.facebook.R;
import com.example.facebook.pojo.PostModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    PostViewModel postViewModel;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        postViewModel = ViewModelProviders.of(this).get(PostViewModel.class);
        postViewModel.getPosts();
        recyclerView = findViewById(R.id.rv);
        final PostsAdapter adapter = new PostsAdapter();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        recyclerView.setAdapter(adapter);
        postViewModel.posListMutableLiveData.observe(this, new Observer<List<PostModel>>() {
            @Override
            public void onChanged(List<PostModel> postModels) {
                adapter.setList(postModels);
                adapter.notifyDataSetChanged();
            }
        });
    }
}