package com.goblog.androidhive.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.goblog.androidhive.R;

/**
 * Created by haikal on 8/11/2016.
 */
public class MyHolder extends RecyclerView.ViewHolder {
    public  TextView nameTxt;
    public ImageView img;
    public MyHolder(View itemView) {
        super(itemView);
        nameTxt= (TextView) itemView.findViewById(R.id.nameTxt);
        img= (ImageView) itemView.findViewById(R.id.movieImage);
    }
}