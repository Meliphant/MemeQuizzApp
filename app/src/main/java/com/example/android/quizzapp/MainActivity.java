package com.example.android.quizzapp;

import android.content.DialogInterface;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText name;
    Button submit;
    int score = 0, buttonState = 1;
    String userName;


    /**
     * ID array for all RadioGroups
     */
    private int[] allRadioGroupsArr = {
            R.id.answers1,
            R.id.answers2,
            R.id.answers3,
            R.id.answers5,
            R.id.answers6,
            R.id.answers7,
            R.id.answers8
    };
    /**
     * ID array for all RadioGroups with correct answers
     */
    private int[] allCheckBoxesArr = {
            R.id.radio4_1,
            R.id.radio4_2,
            R.id.radio4_3,
            R.id.radio4_4
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText) findViewById(R.id.name);
        userName = name.getText().toString();
        if (userName.length() == 0) userName = getString(R.string.username);

        submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(this);


    }

    @Override
    public void onClick(View v){
        switch (v.getId()){

            case R.id.submit:

                if (buttonState == 1){
                    getRadioAnswers();
                    getCheckBoxAnswers();
                    if (score > 0) checkAnswer();
                } else resetAnswer();

                    //Toast.makeText(getApplicationContext(), "Your score is "  + score, Toast.LENGTH_SHORT).show();
                break;
        }
    }
    private void checkAnswer(){

        TextView scoreResult = (TextView) findViewById(R.id.scoreResult);

        scoreResult.setText("Dear " + userName + "!\n" + "Your score is " + score);
        scoreResult.setVisibility(View.VISIBLE);

        submit.setText(getString(R.string.again));

        score = 0;
        buttonState = 0;
    }
    private void resetAnswer(){

        TextView scoreResult = (TextView) findViewById(R.id.scoreResult);
        scoreResult.setVisibility(View.GONE);

        submit.setText(getString(R.string.again));

        clearCheckBoxAnswers();
        clearRadioAnswers();
        name.setText("");
        name.clearFocus();
        buttonState = 1;
    }

    //calculates RadioButton question
    private void getRadioAnswers(){
        int i = 0;
        Resources res = getResources();
        String[]  answerRB = res.getStringArray(R.array.correctRadioGroupsArr);

        for (int idRadioGroup : allRadioGroupsArr){
            RadioGroup currRadioGroup = (RadioGroup) findViewById(idRadioGroup);
            if (currRadioGroup.getCheckedRadioButtonId() != -1) {
                String textRadioButton = ((RadioButton) findViewById(currRadioGroup.getCheckedRadioButtonId())).getText().toString();
                if (textRadioButton.equals(answerRB[i])) score++;
            }
            i++;
        }
    }

    //calculates CheckBoxes question
    private void getCheckBoxAnswers(){
        int i = 0;
        Resources res = getResources();
        String[]  answerCB = res.getStringArray(R.array.correctCheckBoxesArr);

        for (int idCB : allCheckBoxesArr){

            CheckBox currCheckBox = (CheckBox) findViewById(idCB);

            if (currCheckBox.isChecked()){
                String textCheckBox = ((CheckBox) findViewById(idCB)).getText().toString();
                if (textCheckBox.equals(currCheckBox)) i ++;
            }
            if (i == 3) score ++;
        }
    }

    private void clearRadioAnswers(){
        for (int idRB : allRadioGroupsArr){
            RadioGroup currRadioGroup = (RadioGroup) findViewById(idRB);
            currRadioGroup.clearCheck();
        }
    }

    private void clearCheckBoxAnswers(){
        for (int idCB : allCheckBoxesArr){
            CheckBox currCheckBox = (CheckBox) findViewById(idCB);
            if (currCheckBox.isChecked()) currCheckBox.setChecked(false);
        }
    }

}