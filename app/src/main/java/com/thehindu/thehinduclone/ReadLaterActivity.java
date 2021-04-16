package com.thehindu.thehinduclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.thehindu.thehinduclone.ui.home.SideNavActivity;
import com.thehindu.R;

public class ReadLaterActivity extends AppCompatActivity {

    private ImageView mIvImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_later);

        mIvImage = findViewById(R.id.ivArrow1);

        mIvImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ReadLaterActivity.this, SideNavActivity.class);
                startActivity(intent);
            }
        });
    }
}