package com.thehindu.thehinduclone.ui.menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.thehindu.R;
import com.thehindu.themain.LocalConstants;
import com.thehindu.themain.PreferenceHelper;
import com.thehindu.themain.TheHinduApplication;
import com.thehindu.themain.listeners.CommunicationListernerForReadList;
import com.thehindu.themain.models.NewsResponse;
import com.thehindu.themain.models.recyclerview.NewsRvAdapter;
import com.thehindu.themain.services.ClientCalls;

import java.util.List;

import kotlinx.coroutines.CoroutineContextKt;
import kotlinx.coroutines.CoroutineScope;

public class HomeFragment extends Fragment implements CommunicationListernerForReadList {

    ClientCalls clientCalls = ClientCalls.Companion.getInstances();
    List<NewsResponse> newsResponses;

    RecyclerView recyclerView;
    NewsRvAdapter newsRvAdapter;

    public static HomeFragment newInstance() {
        HomeFragment homeFragment = new HomeFragment();
        return homeFragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);


        SwipeRefreshLayout refreshRv = root.findViewById(R.id.refreshRv);
        newsResponses = clientCalls.getAllNews();
        recyclerView = root.findViewById(R.id.rv_home);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        newsRvAdapter = new NewsRvAdapter(newsResponses, this);
        recyclerView.setAdapter(newsRvAdapter);
        newsRvAdapter.notifyDataSetChanged();


        refreshRv.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshRv.setRefreshing(true);
                newsResponses = clientCalls.getAllNews();
                newsRvAdapter = new NewsRvAdapter(newsResponses, HomeFragment.this);
                recyclerView.setAdapter(newsRvAdapter);
                newsRvAdapter.notifyDataSetChanged();
                refreshRv.setRefreshing(false);
            }
        });

        return root;
    }

    @Override
    public void save_it(int id) {

    }
}