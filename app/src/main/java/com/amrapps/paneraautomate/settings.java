package com.amrapps.paneraautomate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by alex on 11/7/15.
 */
public class settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

    }
    public void tutorial(View v) {
        Intent intent = new Intent(this, firstRun.class);
        startActivity(intent);
    }
}
