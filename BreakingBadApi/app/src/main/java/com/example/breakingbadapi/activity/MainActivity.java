package com.example.breakingbadapi.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import com.example.breakingbadapi.data.BB_Person;
import com.example.breakingbadapi.task.BB_Task;
import com.example.breakingbadapi.R;
import com.example.breakingbadapi.task.contract.TaskListener;
import com.example.breakingbadapi.adapter.PictureAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity implements TaskListener {

    GridView gvPersons;
    TextView tvLoader;
    PictureAdapter pictureAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gvPersons = findViewById(R.id.gvPictures);
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
        gvPersons.setVisibility(View.VISIBLE);

        if(pictureAdapter == null) {
            pictureAdapter = new PictureAdapter(this, bb_pictureList);
            gvPersons.setAdapter(pictureAdapter);
        } else {
            pictureAdapter.setPictureList(bb_pictureList);
            pictureAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void provideContent(List<BB_Person> contentList, List<Bitmap> pictureList) {
        refreshUI(pictureList);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_bonus_menu, menu);

        MenuItem switchMenu = menu.findItem(R.id.action_switch);
        switchMenu.setVisible(true);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_switch) {
            Intent bonusIntent = new Intent(this, BonusActivity.class);
            startActivity(bonusIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}