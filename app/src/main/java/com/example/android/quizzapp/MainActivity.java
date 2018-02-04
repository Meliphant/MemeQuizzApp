package com.example.android.quizzapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public final static String EXTRA_MESSAGE = "com.example.android.quizzapp.MESSAGE";
    private ProgressBar mProgressBar;
    private EditText name;
    private String userName;
    private static final String USER_NAME_PATTERN = "[\\w.-]{1,20}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //hiding keyboard
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        mProgressBar = findViewById(R.id.pb_loading_indicator);
        name = findViewById(R.id.name);

        Button startButton = findViewById(R.id.startButton);
        startButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        userName = name.getText().toString();

        if (isUserNameNotCorrect(userName)) {
            Toast.makeText(this, R.string.input_name_error_msg, Toast.LENGTH_LONG).show();
            return;
        }

        mProgressBar.setVisibility(View.VISIBLE);

        final Intent intent = new Intent(this, QuizActivity.class);
        intent.putExtra(EXTRA_MESSAGE, userName);
        //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        //call the destroy method to kill MainActivity intent after redirecting to QuizActivity
        finish();
    }
    private boolean isUserNameNotCorrect(String userName) {
        return userName.isEmpty() || !userName.matches(USER_NAME_PATTERN);
    }

    private void hideKeyboard() {
        View view = this.getCurrentFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        if (view != null && imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View view = getCurrentFocus();
            if (view instanceof EditText) {

                view.clearFocus();
                hideKeyboard();
            }
        }

        return super.dispatchTouchEvent(ev);
    }
/*
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage(R.string.exitApp)
                .setCancelable(false)
                .setNegativeButton(R.string.no, null)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        exitFromApp();
                    }
                }).create().show();
    }

    public void exitFromApp() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }*/
}