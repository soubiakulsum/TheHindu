package com.thehindu.the_hindu.recycler;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.thehindu.R;
import com.thehindu.the_hindu.model.AccountsModel;


public class AccountsViewHolder extends RecyclerView.ViewHolder {

    private ImageView ivAccountsImage;

    public AccountsViewHolder(@NonNull View itemView) {
        super(itemView);
        initViews(itemView);
    }

    private void initViews(View itemView) {
        ivAccountsImage = itemView.findViewById(R.id.ivAccountsImage);
    }

    public void setData(AccountsModel accountsModel) {
        ivAccountsImage.setImageResource(accountsModel.getIvAccountsImage());

    }
}
