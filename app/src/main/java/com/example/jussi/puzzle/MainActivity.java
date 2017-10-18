package com.example.jussi.puzzle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final View button = findViewById(R.id.button_start);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                startPlay();
                // Code here executes on main thread after user presses button
            }
        });

    }

    private void startPlay() {
         Intent intent = new Intent(MainActivity.this,playActivity.class);
        startActivity(intent);

    }
}
