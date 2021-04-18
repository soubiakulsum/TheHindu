package com.thehindu.the_hindu.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.kingfisher.easyviewindicator.RecyclerViewIndicator;
import com.thehindu.R;
import com.thehindu.the_hindu.model.AccountsModel;
import com.thehindu.the_hindu.recycler.AccountsAdapter;

import java.util.ArrayList;
import java.util.List;

public class AccountsActivity extends AppCompatActivity {
    private List<AccountsModel> accountsModelList = new ArrayList<>();
    private RecyclerView accounts_recycler;
    private RecyclerViewIndicator recyclerViewIndicator;
    private Button btnSignUp;
    private Button btnExplore;
    private TextView tvSubscribe;
    private TextView tvHaveAnAccount;
    private TextView tvSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accounts);
        initViewsAndListeners();
        buildData();
        setRecyclerData();
    }

    private void setRecyclerData() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        accounts_recycler.setLayoutManager(linearLayoutManager);
        recyclerViewIndicator.setRecyclerView(accounts_recycler);
        AccountsAdapter productAdapter = new AccountsAdapter(accountsModelList);
        accounts_recycler.setAdapter(productAdapter);

        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(accounts_recycler);

    }

    private void buildData() {
        accountsModelList.add(new AccountsModel(R.drawable.member));
        accountsModelList.add(new AccountsModel(R.drawable.briefingaccounts));
        accountsModelList.add(new AccountsModel(R.drawable.trending));
        accountsModelList.add(new AccountsModel(R.drawable.recommendation));
        accountsModelList.add(new AccountsModel(R.drawable.adfreeexperience));
        accountsModelList.add(new AccountsModel(R.drawable.fasterload));
    }

    private void initViewsAndListeners() {
        accounts_recycler = findViewById(R.id.accounts_recycler);
        recyclerViewIndicator = findViewById(R.id.recyclerViewIndicator);
        btnSignUp = findViewById(R.id.btnSignUp);
        btnExplore = findViewById(R.id.btnExplore);
        tvSubscribe = findViewById(R.id.tvSubscribe);
        tvHaveAnAccount = findViewById(R.id.tvHaveAnAccount);
        tvSignIn = findViewById(R.id.tvSignIn);

    }
}