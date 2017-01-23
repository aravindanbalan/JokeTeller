package com.udacity.joketellerlib;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by arbalan on 1/22/17.
 */

public class JokeTellerDisplayActivity extends AppCompatActivity {
    public static String KEY_JOKE = "joke_string";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_main);

        Intent intent = getIntent();

        if (intent != null) {
            String joke = intent.getStringExtra(KEY_JOKE);
            TextView textView = (TextView) findViewById(R.id.joke);
            textView.setText(joke);
        }
    }
}
