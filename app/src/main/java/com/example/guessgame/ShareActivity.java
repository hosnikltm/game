package com.example.guessgame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class ShareActivity extends AppCompatActivity {

    private String mQuestionText;
    private EditText mEditTextShareTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        mEditTextShareTitle = findViewById(R.id.editTextShareTitle);
        mQuestionText = getIntent().getStringExtra("question text extra");

        SharedPreferences sharedPreferences = getSharedPreferences("app_pref",MODE_PRIVATE);
        String questionTitle = sharedPreferences.getString("share_title","");
        mEditTextShareTitle.setText(questionTitle);
    }

    public void onShareQuestionClicked(View view) {
        String questionTitle = mEditTextShareTitle.getText().toString();

        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT,questionTitle+"\n"+mQuestionText);
        shareIntent.setType("text/plain");
        startActivity(shareIntent);

        SharedPreferences sharedPreferences = getSharedPreferences("app_pref", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("share_title", questionTitle);
        editor.apply();



    }
}