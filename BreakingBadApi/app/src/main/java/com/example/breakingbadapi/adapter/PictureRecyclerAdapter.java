package com.example.breakingbadapi.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.breakingbadapi.R;

import java.util.List;

public class PictureRecyclerAdapter extends RecyclerView.Adapter<PictureRecyclerAdapter.ViewHolder> {

    private Context context;
    private List<Bitmap> values;

    public PictureRecyclerAdapter(Context context,  List<Bitmap> values) {
        this.context = context;
        this.values = values;
    }

    public void setPictureList(List<Bitmap> values) {
        this.values = values;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.image.setImageBitmap(values.get(position));
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.image);
        }
    }
}
