package com.example.calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Historyactivity extends AppCompatActivity {

    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historyactivity);

        text = findViewById(R.id.txt_history);

        Intent intent = getIntent();
        String result = intent.getStringExtra("HISTORY");
        result = result.replace("null","");
        String[] fnresult = result.split("/");
        StringBuilder sb = new StringBuilder();
        for (String s : fnresult) {
            sb.append(s).append("\n");
        }
        text.setText(sb.toString());
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("history", text.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        text.setText(savedInstanceState.getString("history"));
    }
}