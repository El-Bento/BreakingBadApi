package com.example.breakingbadapi.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.breakingbadapi.data.BB_Person;
import com.example.breakingbadapi.task.BB_Task;
import com.example.breakingbadapi.R;
import com.example.breakingbadapi.task.contract.TaskListener;
import com.example.breakingbadapi.adapter.PictureRecyclerAdapter;

import java.util.List;

public class BonusActivity extends AppCompatActivity implements TaskListener {

    RecyclerView rvPersons;
    TextView tvLoader;
    PictureRecyclerAdapter pictureAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_bonus_activity);

        rvPersons = (RecyclerView) findViewById(R.id.pictureRecyclerView);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
        rvPersons.setLayoutManager(staggeredGridLayoutManager);

        tvLoader = findViewById(R.id.tvLoader);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getFromServer();
    }

    private void getFromServer() {
        try {
            new BB_Task(this, getString(R.string.bb_api_url)).execute();
        } catch (Exception ex) {
            Log.d("breakinbadapi", "getFromServer: " + ex.getMessage());
        }
    }

    private void refreshUI(List<Bitmap> bb_pictureList) {
        tvLoader.setVisibility(View.GONE);
        rvPersons.setVisibility(View.VISIBLE);

        if(pictureAdapter == null) {
            pictureAdapter = new PictureRecyclerAdapter(this, bb_pictureList);
            rvPersons.setAdapter(pictureAdapter);
        } else {
            pictureAdapter.setPictureList(bb_pictureList);
            pictureAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void provideContent(List<BB_Person> contentList, List<Bitmap> pictureList) {
        refreshUI(pictureList);
    }
}
