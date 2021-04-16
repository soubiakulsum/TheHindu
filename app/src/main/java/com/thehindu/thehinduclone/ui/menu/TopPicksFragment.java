package com.thehindu.thehinduclone.ui.menu;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.View;


public class TopPicksFragment extends Fragment {

    public static TopPicksFragment newInstance(){
        TopPicksFragment topPicksFragment = new TopPicksFragment();
        return topPicksFragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

}