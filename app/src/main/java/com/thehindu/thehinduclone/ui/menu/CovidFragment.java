package com.thehindu.thehinduclone.ui.menu;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class CovidFragment extends Fragment {

    public static CovidFragment newInstance(){
        CovidFragment covidFragment = new CovidFragment();
        return covidFragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}