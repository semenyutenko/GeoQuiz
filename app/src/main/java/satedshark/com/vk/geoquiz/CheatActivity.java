package satedshark.com.vk.geoquiz;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {
    private static final String EXTRA_ANSWER_IS_TRUE =
            "com.vk.satedshark.geoquiz.answer_is_ true";
    private static final String EXTRA_ANSER_SHOWN =
            "com.vk.satedshark.geoquiz.answer_shown";

    public static Intent newIntent(Context packageContext, boolean answerIsTrue){
        Intent intent = new Intent(packageContext, CheatActivity.class);
        intent.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue);
        return intent;
    }
    public static boolean wasAnswerShown(Intent result){
        return result.getBooleanExtra(EXTRA_ANSER_SHOWN, false);
    }

    private boolean mAnswerIsTrue;
    private TextView mAnswerTextView;
    private Button mShowAnswerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);

        mAnswerTextView = findViewById(R.id.answer_text_view);
        mShowAnswerButton = findViewById(R.id.show_answer_button);
        mShowAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mAnswerIsTrue)mAnswerTextView.setText(R.string.button_true);
                else mAnswerTextView.setText(R.string.button_false);
                setAnswerShownResolt(true);
            }
        });
    }
    private void setAnswerShownResolt(boolean isAnserShown){
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSER_SHOWN, isAnserShown);
        setResult(RESULT_OK, data);
    }
}
