package com.thehindu.thehinduclone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.thehindu.thehinduclone.ui.home.SideNavActivity;
import com.thehindu.R;
import com.thehindu.thehinduclone.ui.menu.CovidFragment;
import com.thehindu.themain.listeners.CommunicationListernerForReadList;
import com.thehindu.themain.models.NewsResponse;
import com.thehindu.themain.models.recyclerview.NewsRvAdapter;
import com.thehindu.themain.services.ClientCalls;

import java.util.List;

public class ReadLaterActivity extends AppCompatActivity implements CommunicationListernerForReadList {

    private ImageView mIvImage;
    ClientCalls clientCalls = ClientCalls.Companion.getInstances();
    List<NewsResponse> newsResponses;

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_later);
        mIvImage = findViewById(R.id.ivArrow1);
        mIvImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReadLaterActivity.this, SideNavActivity.class);
                startActivity(intent);
            }
        });

        SwipeRefreshLayout refreshRv = findViewById(R.id.refreshReadLater);
        newsResponses = clientCalls.getSavedNews();
        recyclerView = findViewById(R.id.rv_readLater);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        SideNavActivity.newsRvAdapter = new NewsRvAdapter(newsResponses, this);
        recyclerView.setAdapter(SideNavActivity.newsRvAdapter);
        SideNavActivity.newsRvAdapter.notifyDataSetChanged();

        refreshRv.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshRv.setRefreshing(true);
                newsResponses = clientCalls.getSavedNews();
                SideNavActivity.newsRvAdapter = new NewsRvAdapter(newsResponses, ReadLaterActivity.this);
                recyclerView.setAdapter(SideNavActivity.newsRvAdapter);
                SideNavActivity.newsRvAdapter.notifyDataSetChanged();
                refreshRv.setRefreshing(false);
            }
        });
    }

    @Override
    public void save_it(int id) {
        SideNavActivity.newsRvAdapter.notifyItemChanged(id);
    }

}