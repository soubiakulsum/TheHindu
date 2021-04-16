package com.thehindu.thehinduclone.ui.menu;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.View;


public class SportsFragment extends Fragment {

    public static SportsFragment newInstance(){
        SportsFragment sportsFragment = new SportsFragment();
        return sportsFragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

}