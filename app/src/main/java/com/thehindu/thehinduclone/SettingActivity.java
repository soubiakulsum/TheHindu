package com.thehindu.thehinduclone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import com.thehindu.thehinduclone.ui.home.SideNavActivity;
import com.thehindu.R;

public class SettingActivity extends AppCompatActivity {

    private ImageView mIvImage;
    private SeekBar seekBar;
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

      mSwitch = findViewById(R.id.switch1);
      mIvArrow = findViewById(R.id.ivArrow3);
      sharedPreferences = getSharedPreferences("night",0);
      Boolean booleanValue = sharedPreferences.getBoolean("night_mode",true);

      if (booleanValue){
          AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
          mSwitch.setChecked(true);
          mIvArrow.setImageResource(R.drawable.ic_back_arrow);
      }
      mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
          @Override
          public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
              if (isChecked){
                  AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                  mSwitch.setChecked(true);
                  SharedPreferences.Editor editor = sharedPreferences.edit();
                  editor.putBoolean("night_mode",true);
                  editor.commit();
              }
              else {
                  AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                  mSwitch.setChecked(false);
                  SharedPreferences.Editor editor = sharedPreferences.edit();
                  editor.putBoolean("night_mode",false);
                  editor.commit();
              }
          }
      });

        mIvImage = findViewById(R.id.ivArrow3);
        seekBar = findViewById(R.id.seekBar);
        tvSmall = findViewById(R.id.tvS);
        tvMedium = findViewById(R.id.tvM);
        tvLarge = findViewById(R.id.tvL);
        tvXtraLarge = findViewById(R.id.tvXL);

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

                switch (progress){
                    case 0:
                        tvSmall.setTextColor(Color.parseColor("#FF0000"));
                        break;
                    case 1:
                        tvMedium.setTextColor(Color.parseColor("#FF0000"));
                        break;
                    case 2:
                        tvLarge.setTextColor(Color.parseColor("#FF0000"));
                        break;
                    case 3:
                        tvXtraLarge.setTextColor(Color.parseColor("#FF0000"));
                        break;

                }
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