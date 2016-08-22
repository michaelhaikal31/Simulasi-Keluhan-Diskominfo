package com.goblog.androidhive.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.goblog.androidhive.PicassoClient;
import com.goblog.androidhive.R;
import com.goblog.androidhive.model.Movie;

import java.util.ArrayList;

/**
 * Created by haikal on 8/11/2016.
 */
public class MyAdapter extends RecyclerView.Adapter<MyHolder> {
    Context c;
    ArrayList<Movie> movies;
    public MyAdapter(Context c, ArrayList<Movie> movies) {
        this.c = c;
        this.movies = movies;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.model,parent,false);
        MyHolder holder=new MyHolder(v);
        return holder;
    }
    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.nameTxt.setText(movies.get(position).getName());
        PicassoClient.downloadImage(c,movies.get(position).getUrl(),holder.img);
    }
    @Override
    public int getItemCount() {
        return movies.size();
    }
}