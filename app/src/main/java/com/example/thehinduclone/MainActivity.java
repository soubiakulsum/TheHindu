package com.example.thehinduclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.thehinduclone.ui.home.SideNavActivity;

public class MainActivity extends AppCompatActivity {

    private TextView mtvText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mtvText = findViewById(R.id.tvText);

        mtvText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SideNavActivity.class);
                startActivity(intent);
            }
        });
    }
}