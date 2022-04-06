package com.chathu.recyclerviewpaging.Adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.akiniyalocts.pagingrecycler.PagingAdapter;
import com.chathu.recyclerviewpaging.R;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Random;

public class MyRecyclerAdapter extends PagingAdapter {

    Random random;
    List<String> stringList;

    public MyRecyclerAdapter(List<String> stringList) {
        this.stringList = stringList;
        random = new Random();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_main,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        MyViewHolder myViewHolder = (MyViewHolder)holder;
        myViewHolder.textView.setText(stringList.get(position));
        Picasso.get().load(new StringBuilder("https://picsum.photos/600/150?random=").append(random.nextInt()).toString())
                .into(myViewHolder.imageView);
    }

    @Override
    public int getPagingLayout() {
        return R.layout.layout_main;
    }

    @Override
    public int getPagingItemCount() {
        return stringList.size();
    }

    public void addNewItem(int itemCount){
        int lastSize = stringList.size();
        for (int i=lastSize;i<lastSize+itemCount;i++)
            stringList.add(new StringBuilder("Item id #").append(i).toString());
        notifyDataSetChanged(lastSize);
    }

    private void notifyDataSetChanged(int lastSize) {
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textView;

        @SuppressLint("WrongViewCast")
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = (ImageView)itemView.findViewById(R.id.img_thumbnail);
            textView = (TextView)itemView.findViewById(R.id.txt_title);
        }
    }
}
