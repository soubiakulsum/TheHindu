package com.thehindu.thehinduclone.ui.menu;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thehindu.R;
import com.thehindu.thehinduclone.ui.home.SideNavActivity;
import com.thehindu.themain.listeners.CommunicationListernerForReadList;
import com.thehindu.themain.models.NewsResponse;
import com.thehindu.themain.models.recyclerview.NewsRvAdapter;
import com.thehindu.themain.services.ClientCalls;

import java.util.ArrayList;
import java.util.List;

public class NationalFragment extends Fragment implements CommunicationListernerForReadList {
    ClientCalls clientCalls = ClientCalls.Companion.getInstances();
    List<NewsResponse> newsResponses = new ArrayList<>();
    RecyclerView recyclerView;

    public static NationalFragment newInstance() {
        NationalFragment nationalFragment = new NationalFragment();
        return nationalFragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
       try {
           super.onViewCreated(view, savedInstanceState);

           SwipeRefreshLayout refreshRv = view.findViewById(R.id.refresh_national);
           newsResponses = clientCalls.getSpecificNews(26, "india");
           recyclerView = view.findViewById(R.id.rv_national);
           recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
           SideNavActivity.newsRvAdapter = new NewsRvAdapter(newsResponses, this);
           recyclerView.setAdapter(SideNavActivity.newsRvAdapter);
           SideNavActivity.newsRvAdapter.notifyDataSetChanged();

           refreshRv.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
               @Override
               public void onRefresh() {
                   refreshRv.setRefreshing(true);
                   newsResponses = clientCalls.getSpecificNews(26, "india");
                   SideNavActivity.newsRvAdapter = new NewsRvAdapter(newsResponses,
                           NationalFragment.this);
                   recyclerView.setAdapter(SideNavActivity.newsRvAdapter);
                   SideNavActivity.newsRvAdapter.notifyDataSetChanged();
                   refreshRv.setRefreshing(false);
               }
           });
       }catch (Exception e){
           Log.d("TAG", "onViewCreated: "+e);
       }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_national, container, false);
        return root;
    }

    @Override
    public void save_it(int id) {

    }
}