package com.chathu.recyclerviewpaging;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Toast;

import com.akiniyalocts.pagingrecycler.PagingDelegate;
import com.chathu.recyclerviewpaging.Adapter.MyRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements PagingDelegate.OnPageListener {

    private static final int MAX_ITEM = 25;
    RecyclerView recyclerView;
    MyRecyclerAdapter adapter;
    CardView cardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cardView = (CardView)findViewById(R.id.cardView_progress_bar);
        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,layoutManager.getOrientation()));
        
        generateData();
    }

    private void generateData() {
        List<String> stringList = new ArrayList<>();
        for (int i=0;i<5;i++)
            stringList.add(new StringBuilder("Item id #").append(i).toString());
        adapter = new MyRecyclerAdapter(stringList);

        PagingDelegate pagingDelegate = new PagingDelegate.Builder(adapter)
                .attachTo(recyclerView)
                .listenWith(this)
                .build();
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onPage(int i) {
        cardView.setVisibility(View.VISIBLE);
        if (i < MAX_ITEM){
            new Handler(Looper.myLooper())
                   .postDelayed(new Runnable() {
                       @Override
                       public void run() {
                           int lastSize = adapter.getPagingItemCount();
                           cardView.setVisibility(View.GONE);
                           adapter.addNewItem(5);
                           recyclerView.smoothScrollToPosition(lastSize+4);
                       }
                   },1500);
        }
        else
            onDonePaging();
    }

    @Override
    public void onDonePaging() {
        cardView.setVisibility(View.GONE);
        Toast.makeText(this,"Max Loading...",Toast.LENGTH_SHORT).show();
    }
}