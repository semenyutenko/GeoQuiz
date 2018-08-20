package satedshark.com.vk.geoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    //region KEY
    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "index";
    private static final String KEY_BUTTON_ACTIVE = "buttonActive";
    private static final String KEY_RIGHT_ANSWER = "rightAnswer";
    //endregion

    //region Variables
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private TextView mQuestionTextView;
    private int mCurrentIndex = 0;
    private int mRightAnswer = 0;
    private boolean mButtonActive = true;
    //endregion

    //region mQuestionBank
    private Question[] mQuestionBank = new Question[]{
            new Question(R.string.question_australia, true),
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true)
    };
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");
        setContentView(R.layout.activity_quiz);
        if(savedInstanceState != null){
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX,0);
            mButtonActive = savedInstanceState.getBoolean(KEY_BUTTON_ACTIVE, true);
            mRightAnswer = savedInstanceState.getInt(KEY_RIGHT_ANSWER, 0);
        }

//        mQuestionTextView = findViewById(R.id.question_text_view);

        mTrueButton = findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(true);
            }
        });
        mFalseButton = findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(false);
            }
        });
        mNextButton = findViewById(R.id.next_button);

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex = mCurrentIndex + 1;
                mButtonActive = true;
                updateQuestion();
            }
        });

        updateQuestion();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState(Bundle) called");
        outState.putInt(KEY_INDEX, mCurrentIndex);
        outState.putBoolean(KEY_BUTTON_ACTIVE, mButtonActive);
        outState.putInt(KEY_RIGHT_ANSWER, mRightAnswer);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"onStart() called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"onResume() called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"onPause() called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

    private void updateQuestion(){
        int question = mQuestionBank[mCurrentIndex % mQuestionBank.length].getTextResId();
        mQuestionTextView.setText(question);
        mTrueButton.setClickable(mButtonActive);
        mFalseButton.setClickable(mButtonActive);
    }
    private void checkAnswer(boolean userPressedTrue){
        boolean answerIsTrue = mQuestionBank[mCurrentIndex% mQuestionBank.length].isAnswerTrue();
        int messageResId = 0;
        if (userPressedTrue == answerIsTrue){
            messageResId = R.string.correct_toast;
            mRightAnswer ++;
        }else {
            messageResId = R.string.incorrect_toast;
        }
        mButtonActive = false;
        mTrueButton.setClickable(mButtonActive);
        mFalseButton.setClickable(mButtonActive);
        Toast.makeText(this, getResources().getString(messageResId) + " " +
                ((int)((double)mRightAnswer / (mCurrentIndex + 1) * 100)) + " %", Toast.LENGTH_SHORT).show();
    }
}
