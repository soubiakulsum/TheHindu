package com.thehindu.thehinduclone.ui.menu;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.thehindu.R;
import com.thehindu.thehinduclone.ui.home.SideNavActivity;
import com.thehindu.themain.LocalConstants;
import com.thehindu.themain.PreferenceHelper;
import com.thehindu.themain.TheHinduApplication;
import com.thehindu.themain.listeners.CommunicationListernerForReadList;
import com.thehindu.themain.models.NewsResponse;
import com.thehindu.themain.models.recyclerview.NewsRvAdapter;
import com.thehindu.themain.services.ClientCalls;

import java.util.ArrayList;
import java.util.List;

import kotlinx.coroutines.CoroutineContextKt;
import kotlinx.coroutines.CoroutineScope;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment implements CommunicationListernerForReadList {

    ClientCalls clientCalls = ClientCalls.Companion.getInstances();
    List<NewsResponse> newsResponses;
    List<NewsResponse> responseList = new ArrayList<>();

    RecyclerView recyclerView;
    NewsRvAdapter newsRvAdapter;
    LinearLayoutManager linearLayoutManager;

    ImageView homeTopImage;
    LinearLayout llFirstItem;
    TextView rv_home_heading, tv_time;

    boolean loading = true;
    int page = 1;
    int limit = 6;
    int pastVisiblesItems, visibleItemCount, totalItemCount;
    ProgressBar p;

    public static HomeFragment newInstance() {
        HomeFragment homeFragment = new HomeFragment();
        return homeFragment;
    }


    public void getPage() {
        loading = true;
        p.setVisibility(View.VISIBLE);
        int start = (page - 1) * limit;
        int end = page * limit;
        for (int i = start; i < end && i < newsResponses.size(); i++) {
            responseList.add(newsResponses.get(i));
            Log.d("TAG", "getPage: " + i + " " + responseList.get(i).getPostedAt());
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (newsRvAdapter != null) {
                    newsRvAdapter.notifyDataSetChanged();
                } else {
                    if (newsResponses != null) {
                        newsRvAdapter = new NewsRvAdapter(responseList, HomeFragment.this);
                        recyclerView.setAdapter(newsRvAdapter);
                        Log.d("TAG", "run: ");
                    }
                }
                loading = false;
                p.setVisibility(View.GONE);
                newsRvAdapter.notifyDataSetChanged();
            }
        }, 1500);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("TAG", "onResume: ");
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SwipeRefreshLayout refreshRv = view.findViewById(R.id.refreshRv);
        llFirstItem = view.findViewById(R.id.llFirstItem);
        homeTopImage = view.findViewById(R.id.homeTopImage);
        rv_home_heading = view.findViewById(R.id.rv_home_heading);
        tv_time = view.findViewById(R.id.tv_time);

        getData(view);

        refreshRv.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (newsRvAdapter != null) {
                    if (llFirstItem.getVisibility() == View.VISIBLE) {
                        llFirstItem.setVisibility(View.GONE);
                        refreshRv.setRefreshing(false);

                    } else {
                        refreshRv.setRefreshing(true);
                        recyclerView.setAdapter(newsRvAdapter);
                        refreshRv.setRefreshing(false);
                        newsRvAdapter.notifyDataSetChanged();
                    }
                }
            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) { //check for scroll down
                    visibleItemCount = linearLayoutManager.getChildCount();
                    totalItemCount = linearLayoutManager.getItemCount();
                    pastVisiblesItems = linearLayoutManager.findFirstVisibleItemPosition();

                    if (!loading) {
                        if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                            page++;
                            getPage();
                        }
                    }
                }
            }

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_INDICATOR_TOP) {
                    llFirstItem.setVisibility(View.GONE);
                }

            }
        });
    }

    private void getData(View view) {
        p = view.findViewById(R.id.progressBar);
        recyclerView = view.findViewById(R.id.rv_home);
        linearLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        String token = "Bearer " + PreferenceHelper.readStringFromPreference(LocalConstants.PREF_TOKEN_VALUE);
        LocalConstants.API_CLIENT.getAllNews(token).enqueue(new Callback<List<NewsResponse>>() {
            @Override
            public void onResponse(Call<List<NewsResponse>> call, Response<List<NewsResponse>> response) {
                newsResponses = response.body();
                if (newsResponses != null) {
                    if (newsResponses.size() > 0) {
                        Glide.with(view.getContext()).load(newsResponses.get(0).getMultiMedia()).into(homeTopImage);
                        rv_home_heading.setText(newsResponses.get(0).getHeadline());
                        tv_time.setText("Posted Today");
                    }
                    getPage();
                }

            }

            @Override
            public void onFailure(Call<List<NewsResponse>> call, Throwable t) {

            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        return root;
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("TAG", "onPause: ");
    }

    @Override
    public void save_it(int id) {
    }
}