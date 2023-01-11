package com.example.texttospeech;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private ImageView mic;
    private TextView tts;
    private static final int REQUEST_CODE_SPEECH_INPUT = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mic = findViewById(R.id.mic);
        tts = findViewById(R.id.tts);

        mic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                i1.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);

                i1.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
                i1.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak to text");

                try {

                    startActivityForResult(i1, REQUEST_CODE_SPEECH_INPUT);
                    tts.setText("");

                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Device doesn't support this feature yet " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }


        });}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE_SPEECH_INPUT:
                if (resultCode==RESULT_OK && data !=null)
                {
                    ArrayList<String> text = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    tts.setText(text.get(0));
                }
                break;

        }
       }
    }
