package com.example.thehinduclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.thehinduclone.ui.home.SideNavActivity;

public class NotificationActivity extends AppCompatActivity {

    private ImageView mIvImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        mIvImage = findViewById(R.id.ivArrow2);

        mIvImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NotificationActivity.this, SideNavActivity.class);
                startActivity(intent);
            }
        });
    }
}