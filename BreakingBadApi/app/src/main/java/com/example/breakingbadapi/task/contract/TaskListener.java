package com.example.breakingbadapi.task.contract;

import android.graphics.Bitmap;

import com.example.breakingbadapi.data.BB_Person;

import java.util.List;

public interface TaskListener {
    void provideContent(List<BB_Person> contentList, List<Bitmap> pictureList);
}
