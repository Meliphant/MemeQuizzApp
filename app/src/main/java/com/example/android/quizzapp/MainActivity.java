package com.example.android.quizzapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener  {

    public final static String EXTRA_MESSAGE = "com.example.android.quizzapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        Button startButton = (Button) findViewById(R.id.startButton);
        startButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.startButton:
                Intent intent = new Intent(this, QuizActivity.class);

                EditText name = (EditText) findViewById(R.id.name);
                String userName = name.getText().toString();
                if (userName.length() == 0) userName = getString(R.string.username);

                intent.putExtra(EXTRA_MESSAGE, userName);
                startActivity(intent);
                break;
        }
    }
}