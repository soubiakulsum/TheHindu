package com.thehindu.thehinduclone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import com.thehindu.thehinduclone.ui.home.SideNavActivity;
import com.thehindu.R;
import com.thehindu.themain.LocalConstants;
import com.thehindu.themain.PreferenceHelper;

public class SettingActivity extends AppCompatActivity {

    private TextView tvSmall;
    private TextView tvMedium;
    private TextView tvLarge;
    private TextView tvXtraLarge;
    private ImageView mIvArrow;
    Switch mSwitch;
    SharedPreferences sharedPreferences = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        PreferenceHelper.getSharedPreferences(this);
        CardView cardView = findViewById(R.id.logout);
        mSwitch = findViewById(R.id.switch1);
        ImageView mIvImage = findViewById(R.id.ivArrow3);
        SeekBar seekBar = findViewById(R.id.seekBar);
        tvSmall = findViewById(R.id.tvS);
        tvMedium = findViewById(R.id.tvM);
        tvLarge = findViewById(R.id.tvL);
        tvXtraLarge = findViewById(R.id.tvXL);
        if (PreferenceHelper.readBooleanFromPreference("night_mode")) {
            mSwitch.setChecked(true);
        }
        int x = PreferenceHelper.readIntFromPreference("fontSize");
        if (PreferenceHelper.readIntFromPreference("fontSize") != 0) {
            if (x == 12) {
                seekBar.setProgress(0);
            } else if (x == 15) {
                seekBar.setProgress(1);

            } else if (x == 18) {
                seekBar.setProgress(2);

            } else {
                seekBar.setProgress(3);

            }
        }
        ProgressBar progressBar = findViewById(R.id.progressBar3);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        PreferenceHelper.writeBooleanToPreference(LocalConstants.PREF_TOKEN_BOOLEAN, false);
                        PreferenceHelper.writeStringToPreference(LocalConstants.PREF_TOKEN_VALUE, "");
                        PreferenceHelper.writeBooleanToPreference(LocalConstants.PREF_USER_LOGIN, false);
                        progressBar.setVisibility(View.GONE);
                    }
                }, 1500);
            }
        });

        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                PreferenceHelper.getSharedPreferences(SettingActivity.this);
                if (isChecked) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    mSwitch.setChecked(true);
                    PreferenceHelper.writeBooleanToPreference("night_mode", true);
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    mSwitch.setChecked(false);
                    PreferenceHelper.writeBooleanToPreference("night_mode", false);
                }
            }
        });


        mIvImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this, SideNavActivity.class);
                startActivity(intent);
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                switch (progress) {
                    case 0:
                        tvSmall.setTextColor(Color.parseColor("#FF0000"));
                        PreferenceHelper.writeIntToPreference("fontSize", 12);
                        break;
                    case 1:
                        tvMedium.setTextColor(Color.parseColor("#FF0000"));
                        PreferenceHelper.writeIntToPreference("fontSize", 15);
                        break;
                    case 2:
                        tvLarge.setTextColor(Color.parseColor("#FF0000"));
                        PreferenceHelper.writeIntToPreference("fontSize", 18);
                        break;
                    case 3:
                        tvXtraLarge.setTextColor(Color.parseColor("#FF0000"));
                        PreferenceHelper.writeIntToPreference("fontSize", 22);
                        break;
                }
                Log.d("TAG", "onProgressChanged: " + PreferenceHelper.readIntFromPreference("fontSize"));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                tvSmall.setTextColor(Color.parseColor("#000000"));
                tvMedium.setTextColor(Color.parseColor("#000000"));
                tvLarge.setTextColor(Color.parseColor("#000000"));
                tvXtraLarge.setTextColor(Color.parseColor("#000000"));

            }
        });
    }
}