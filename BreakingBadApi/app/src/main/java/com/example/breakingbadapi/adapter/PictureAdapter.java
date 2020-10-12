package com.example.breakingbadapi.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.breakingbadapi.R;

import java.util.List;

public class PictureAdapter extends BaseAdapter {

    private Context context;
    private List<Bitmap> values;

    public PictureAdapter(Context context,  List<Bitmap> values) {
        this.context = context;
        this.values = values;
    }

    public void setPictureList(List<Bitmap> values) {
        this.values = values;
    }

    @Override
    public int getCount() {
        return values.size();
    }

    @Override
    public Object getItem(int i) {
        return values.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if(view == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(context);
            view = layoutInflater.inflate(R.layout.picture_view, viewGroup, false);
        }

        final ImageView imageView = (ImageView) view.findViewById(R.id.ivPicture);

        imageView.setImageBitmap(values.get(i));
        return view;
    }
}
