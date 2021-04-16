package com.example.thehinduclone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.thehinduclone.ui.home.SideNavActivity;

public class ReadLaterActivity extends AppCompatActivity {

    private ImageView mIvImage;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_later);

        mIvImage = findViewById(R.id.ivArrow1);
        recyclerView = findViewById(R.id.recyclerViews);

        mIvImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ReadLaterActivity.this, SideNavActivity.class);
                startActivity(intent);
            }
        });
    }
}