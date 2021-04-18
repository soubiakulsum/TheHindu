package com.thehindu.the_hindu.recycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.thehindu.R;
import com.thehindu.the_hindu.model.AccountsModel;

import java.util.List;

public class AccountsAdapter extends RecyclerView.Adapter<AccountsViewHolder> {

    private List<AccountsModel> accountsModelList;

    public AccountsAdapter(List<AccountsModel> accountsModelList) {
        this.accountsModelList = accountsModelList;
    }

    @NonNull
    @Override
    public AccountsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.accounts_item_layout, parent, false);
        return new AccountsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AccountsViewHolder holder, int position) {
        holder.setData(accountsModelList.get(position));
    }

    @Override
    public int getItemCount() {
        return accountsModelList.size();
    }
}
