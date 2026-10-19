package com.example.truefalsequiz;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView tvResult = findViewById(R.id.tvResult);
        boolean isCorrect = getIntent().getBooleanExtra("isCorrect", false);

        LinearLayout main = findViewById(R.id.main);

        if (isCorrect) {
            tvResult.setText("צדקת!");
            main.setBackgroundColor(getColor(R.color.true_correct));
        } else {
            tvResult.setText("טעית...");
            main.setBackgroundColor(getColor(R.color.false_wrong));
        }

        Button btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener( v -> {finish();}
        );
    }
}