package com.example.thehinduclone.ui.menu;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ElectionFragment extends Fragment {

    public static ElectionFragment newInstance(){
        ElectionFragment electionFragment = new ElectionFragment();
        return electionFragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}