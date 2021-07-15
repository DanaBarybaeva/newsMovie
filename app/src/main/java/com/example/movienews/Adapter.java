package com.example.movienews;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> implements View.OnClickListener {
    private Context context;
    private List<Model> newsList;


    public Adapter(Context context,List<Model> newsList){
        this.context = context;
        this.newsList = newsList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.moviesitem,parent,false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Model item = newsList.get(position);


        Glide.with(context.getApplicationContext())
                .load("https://image.tmdb.org/t/p/w500"+item.getImage())
                .into(holder.photo);
//        String backdrop_path = item.getBackgroundimage();
//        Picasso.with(context).load(backdrop_path).into(holder.background);



        holder.title.setText(item.getName());

        holder.data.setText(item.getData().replace("-","."));
        holder.vote_average.setText(item.getVote_average());

        holder.relativeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,moreNewsPage.class);
                intent.putExtra("title",item.getName());
                intent.putExtra("overview",item.getDescription());
                intent.putExtra("release_date",item.getData().replace("-","."));
                intent.putExtra("backdrop_path",item.getBackgroundimage());
                intent.putExtra("vote_average",item.getVote_average());

                context.startActivity(intent);

            }
        });
        }


    int num = 1;

    @Override
    public int getItemCount() {
        if(num*10 > newsList.size()){
            return newsList.size();
        }else {
            return num+10;
        }

//        return newsList.size();




    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.morebutton:


                if(num*10 < newsList.size())

                    num = num +2;
        }


    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        public ImageView photo;
        public TextView title;
        public TextView data;
        public TextView overview;
        public ImageView background;
        public TextView vote_average;
        public ImageButton moreButton;
        public View relativeItem;






        public MyViewHolder(View v) {
            super(v);
            background = itemView.findViewById(R.id.imageBackground);
            title = itemView.findViewById(R.id.title_film);
            data = itemView.findViewById(R.id.release_year);
            vote_average=itemView.findViewById(R.id.vote_average);
            photo =  itemView.findViewById(R.id.photo);
            overview =  itemView.findViewById(R.id.description);
            relativeItem=  itemView.findViewById(R.id.relativeItem);

            moreButton = itemView.findViewById(R.id.morebutton);


        }
    }

}


