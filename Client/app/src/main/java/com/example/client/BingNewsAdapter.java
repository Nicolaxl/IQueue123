package com.example.client;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.helper.widget.Carousel;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BingNewsAdapter extends RecyclerView.Adapter<BingNewsAdapter.ViewHolder> {

    Context con;
    ArrayList<NewsModel> newsModelArrayList;

    public BingNewsAdapter(Context con, ArrayList<NewsModel> newsModelArrayList) {
        this.con = con;
        this.newsModelArrayList = newsModelArrayList;
    }

    @NonNull
    @Override
    public BingNewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v = LayoutInflater.from(con).inflate(R.layout.layout_news,null,false);
       return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BingNewsAdapter.ViewHolder holder, int position) {

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(con,webView.class);
                intent.putExtra("url",newsModelArrayList.get(position).getUrl());
                con.startActivity(intent);
            }
        });

        holder.time.setText("Published At:-" + newsModelArrayList.get(position).getPublishedAt());
        holder.author.setText(newsModelArrayList.get(position).getAuthor());
        holder.heading.setText(newsModelArrayList.get(position).getTitle());
        holder.content.setText(newsModelArrayList.get(position).getDescription());
        Glide.with(con).load(newsModelArrayList.get(position).getUrlToImage()).into(holder.imagenews);



    }

    @Override
    public int getItemCount() {
        return newsModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView heading,content,author, time;
        CardView cardView;
        ImageView imagenews;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            heading = itemView.findViewById(R.id.heading);
            content = itemView.findViewById(R.id.contentNews);
            author = itemView.findViewById(R.id.author);
            time = itemView.findViewById(R.id.timeNews);
            cardView = itemView.findViewById(R.id.cardNews);
            imagenews = itemView.findViewById(R.id.imageNews);

        }
    }
}
