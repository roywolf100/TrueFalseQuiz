package com.example.truefalsequiz;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    int qNum = 0;
    int userScore = 0;
    int questionsAmount;
    protected void loadQuestion()
    {
        String[] questions = getResources().getStringArray(R.array.trivia_questions);
        String question = questions[qNum];
            TextView tvQuestion = findViewById(R.id.question);
            tvQuestion.setText(question);
            questionsAmount = questions.length;
    }

    protected void loadScore()
    {
        TextView tvScore = findViewById(R.id.score);
        tvScore.setText(String.valueOf(userScore));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        boolean[] answers = {
                true,   // 1. עגבנייה היא פרי
                false,  // 2. לא הראשון (ילידים, ויקינגים)
                true,   // 3. הלווייתן הכחול הוא הכי גדול אי פעם
                false,  // 4. לא ייתכן משולש עם שתי זוויות ישרות
                false,  // 5. השפה בברזיל היא פורטוגזית
                false,  // 6. קולנוע סאונד החל במאה ה-20
                true,   // 7. רוסיה היא אכן הגדולה ביותר
                false,  // 8. מיתוס, משתמשים בכל המוח
                true,   // 9. יהלום הוא פחמן טהור
                false   // 10. וטרינר מטפל בבעלי חיים
        };


        String explanation = "ouifhsughfogjsdpc apiofmvreiogvwnjeoniuvjnaopjfpr rn98vjeg iufene gnipun iufepauijf iu juerjnpeor";
        loadScore();

        Button btnTrue = findViewById(R.id.true_button);
        btnTrue.setOnClickListener(v -> {
            boolean isCorrect;
            isCorrect = !answers[qNum];
            if(isCorrect) {
                userScore++;
                loadScore();
            }



            Intent intent = new Intent(MainActivity.this, ResultActivity.class);
            intent.putExtra("isCorrect", isCorrect);
            intent.putExtra("questionNumber", qNum );
            intent.putExtra("explanation", explanation);
            startActivity(intent);
        });

        Button btnFalse = findViewById(R.id.false_button);
        btnFalse.setOnClickListener(v -> {
            boolean isCorrect;
            isCorrect = answers[qNum];
            if(isCorrect) {
                userScore++;
                loadScore();
            }

            Intent intent = new Intent(MainActivity.this, ResultActivity.class);
            intent.putExtra("isCorrect", isCorrect);
            intent.putExtra("questionNumber", qNum );
            intent.putExtra("explanation", explanation);
            startActivity(intent);
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        loadQuestion();

        if (qNum < questionsAmount) {
            Log.d("MainActivity", String.valueOf(qNum));
            qNum++;
        }
        else
        {
            qNum = 0;
            Intent intent = new Intent(MainActivity.this, FinalScreenActivity.class);
            intent.putExtra("score", userScore);
            startActivity(intent);
        }

    }

}