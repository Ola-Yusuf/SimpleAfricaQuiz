package com.example.android.simpleafricaquiz;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static android.text.TextUtils.isEmpty;

public class MainActivity extends AppCompatActivity {
    boolean timeIsRunning;
    long mSTART_TIME_IN_MILLIS, mTimeLeftInMillis;
    CountDownTimer mCountDownTimer;
    int totalScore, maximumScorePerQuestion = 10;
    CheckBox checkBoxForQ1_1, checkBoxForQ1_2, checkBoxForQ1_3, checkBoxForQ1_4,
            checkBoxForQ7_1, checkBoxForQ7_2, checkBoxForQ7_3, checkBoxForQ7_4;
    EditText editTextForQ2, editTextForQ4, editTextForQ5, editTextForQ8, editTextForQ10;
    RadioGroup radioGroupForQ3, radioGroupForQ6, radioGroupForQ9;
    String[] multipleChoiceAnswersForQuestion1, multipleChoiceAnswersForQuestion7;
    ScrollView mScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        //hide keyboard when activity (application)start
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    /**
     * This method is use to ensure username supplied is 5 to 8 in length
     * before moving to quiz layout
     * call initializer and startTimer method.
     * show toast for invalid username entry.
     *
     * @param v the start button
     */
    @SuppressLint("StringFormatInvalid")
    public void login(View v) {
        String enteredUserName = ((EditText) findViewById(R.id.userName)).getText().toString().trim();
        if (enteredUserName.length() > 4 && enteredUserName.length() < 9) {
            setContentView(R.layout.quiz); //move to the exam layout/ interface
            initializer();
            ((TextView) findViewById(R.id.displayUserName)).setText(getString(R.string.greetingUser, enteredUserName.toUpperCase()));
            startTimer();
        } else {
            showToast(getString(R.string.userNameErrorToast));
        }
    }

    /**
     * this method initialize all need variables
     */
    public void initializer() {
        totalScore = 0;
        //get Maximum Quiz Time from String xml
        mSTART_TIME_IN_MILLIS = this.getResources().getInteger(R.integer.durationOfQuizInMillisec);

        mTimeLeftInMillis = mSTART_TIME_IN_MILLIS;

        //ScrollView That contains all views used for quiz questions
        mScrollView = (ScrollView) findViewById(R.id.questionScrollView);
        //CheckBoxes for Question 1 multiple Answer
        checkBoxForQ1_1 = (CheckBox) findViewById(R.id.optionCheckBoxForQ1_1);
        checkBoxForQ1_2 = (CheckBox) findViewById(R.id.optionCheckBoxForQ1_2);
        checkBoxForQ1_3 = (CheckBox) findViewById(R.id.optionCheckBoxForQ1_3);
        checkBoxForQ1_4 = (CheckBox) findViewById(R.id.optionCheckBoxForQ1_4);
        //EditText view for Question 2
        editTextForQ2 = (EditText) findViewById(R.id.editTextForQ2);
        //RadioButtonGroup for Question 3
        radioGroupForQ3 = (RadioGroup) findViewById(R.id.radioGroupForQ3);
        //EditText view for Question 4
        editTextForQ4 = (EditText) findViewById(R.id.editTextForQ4);
        //EditText view for Question 5
        editTextForQ5 = (EditText) findViewById(R.id.editTextForQ5);
        //RadioButtonGroup for Question 6
        radioGroupForQ6 = (RadioGroup) findViewById(R.id.radioGroupForQ6);

        //CheckBoxes for Question 7 multiple Answer
        checkBoxForQ7_1 = (CheckBox) findViewById(R.id.optionCheckBoxForQ7_1);
        checkBoxForQ7_2 = (CheckBox) findViewById(R.id.optionCheckBoxForQ7_2);
        checkBoxForQ7_3 = (CheckBox) findViewById(R.id.optionCheckBoxForQ7_3);
        checkBoxForQ7_4 = (CheckBox) findViewById(R.id.optionCheckBoxForQ7_4);
        //EditText view for Question 8
        editTextForQ8 = (EditText) findViewById(R.id.editTextForQ8);
        //RadioButtonGroup for Question 9
        radioGroupForQ9 = (RadioGroup) findViewById(R.id.radioGroupForQ9);
        //EditText view for Question 10
        editTextForQ10 = (EditText) findViewById(R.id.editTextForQ10);
    }

    /**
     * This method mandate user to answer all question if time is still Running
     * before the quiz can be marked.
     *
     * @param view the submit Button
     */
    @SuppressLint("StringFormatInvalid")
    public void submitQuiz(View view) {
        if (timeIsRunning) {
            if (isQuestionWithCheckBoxNotAnswered(checkBoxForQ1_1, checkBoxForQ1_2, checkBoxForQ1_3, checkBoxForQ1_4)) {
                //scroll to the position of Q1
                scrollToUnansweredQuestion(R.id.Q1);
                showToast(getString(R.string.unansweredQuestionToastMessage, 1));
            } else if (isQuestionWithEditTextNotAnswered(editTextForQ2)) {
                scrollToUnansweredQuestion(R.id.Q2);
                showToast(getString(R.string.unansweredQuestionToastMessage, 2));
            } else if (isQuestionWithRadioGroupNotAnswered(radioGroupForQ3)) {
                scrollToUnansweredQuestion(R.id.Q3);
                showToast(getString(R.string.unansweredQuestionToastMessage, 3));
            } else if (isQuestionWithEditTextNotAnswered(editTextForQ4)) {
                scrollToUnansweredQuestion(R.id.Q4);
                showToast(getString(R.string.unansweredQuestionToastMessage, 4));
            } else if (isQuestionWithEditTextNotAnswered(editTextForQ5)) {
                scrollToUnansweredQuestion(R.id.Q5);
                showToast(getString(R.string.unansweredQuestionToastMessage, 5));
            } else if (isQuestionWithRadioGroupNotAnswered(radioGroupForQ6)) {
                scrollToUnansweredQuestion(R.id.Q6);
                showToast(getString(R.string.unansweredQuestionToastMessage, 6));
            } else if (isQuestionWithCheckBoxNotAnswered(checkBoxForQ7_1, checkBoxForQ7_2, checkBoxForQ7_3, checkBoxForQ7_4)) {
                scrollToUnansweredQuestion(R.id.Q7);
                showToast(getString(R.string.unansweredQuestionToastMessage, 7));
            } else if (isQuestionWithEditTextNotAnswered(editTextForQ8)) {
                scrollToUnansweredQuestion(R.id.Q8);
                showToast(getString(R.string.unansweredQuestionToastMessage, 8));
            } else if (isQuestionWithRadioGroupNotAnswered(radioGroupForQ9)) {
                scrollToUnansweredQuestion(R.id.Q9);
                showToast(getString(R.string.unansweredQuestionToastMessage, 9));
            } else if (isQuestionWithEditTextNotAnswered(editTextForQ10)) {
                scrollToUnansweredQuestion(R.id.Q10);
                showToast(getString(R.string.unansweredQuestionToastMessage, 10));
            } else {
                mCountDownTimer.cancel();
                timeIsRunning = false;
                updateCountDownText();
                markQuiz();
                setContentView(R.layout.users_decision);
            }
        }

    }

    /**
     * use to scroll to a particular position (i.e, unanswered question)
     *
     * @param mTextView is the view (of unanswered question) to be scrolled to
     */
    private void scrollToUnansweredQuestion(int mTextView) {
        mScrollView.scrollTo(0, ((TextView) findViewById(mTextView)).getTop());
    }

    /**
     * use to check if corresponding question is answered
     *
     * @param mRadioGroup associated to a particular question
     * @return true or false
     */
    public boolean isQuestionWithRadioGroupNotAnswered(RadioGroup mRadioGroup) {
        return mRadioGroup.getCheckedRadioButtonId() == -1;
    }

    /**
     * use to check if corresponding question is answered
     *
     * @param mEditText associated to a particular question
     * @return true or false
     */
    public boolean isQuestionWithEditTextNotAnswered(EditText mEditText) {
        return isEmpty(mEditText.getText().toString().trim());
    }

    /**
     * use to check if corresponding question is answered
     *
     * @param mCheckBox1 is the 1st CheckBox associated to a particular question
     * @param mCheckBox2 is the 2nd CheckBox associated to a particular question
     * @param mCheckBox3 is the 3rd CheckBox associated to a particular question
     * @param mCheckBox4 is the 4th CheckBox associated to a particular question
     * @return true or false
     */
    public boolean isQuestionWithCheckBoxNotAnswered(CheckBox mCheckBox1, CheckBox mCheckBox2,
                                                     CheckBox mCheckBox3, CheckBox mCheckBox4) {
        return !(mCheckBox1.isChecked() || mCheckBox2.isChecked() || mCheckBox3.isChecked() || mCheckBox4.isChecked());
    }

    //compute final score of the user
    public void markQuiz() {
        totalScore += getScoreForQuestion1();
        totalScore += getScoreForQuestion2();
        totalScore += getScoreForQuestion3();
        totalScore += getScoreForQuestion4();
        totalScore += getScoreForQuestion5();
        totalScore += getScoreForQuestion6();
        totalScore += getScoreForQuestion7();
        totalScore += getScoreForQuestion8();
        totalScore += getScoreForQuestion9();
        totalScore += getScoreForQuestion10();
    }

    @SuppressLint("StringFormatMatches")
    public void showResult(View view) {
        String performance = "";
        if (totalScore < 30) {
            performance = getString(R.string.poorPerformance);
        } else if (totalScore > 29 && totalScore < 50) {
            performance = getString(R.string.veryGoodPerformance);
        } else if (totalScore > 49 && totalScore < 70) {
            performance = getString(R.string.averagePerformance);
        } else if (totalScore > 69) {
            performance = getString(R.string.excellentPerformance);
        }
        showToast(getString(R.string.scoreReport, performance, (double) totalScore));
    }

    /**
     * this method takes the user to the login page
     *
     * @param view the tyrAgain Button
     */
    public void tryAgain(View view) {
        setContentView(R.layout.login); //move to the login layout/ interface
    }

    public int getScoreForQuestion1() {
        //get Array form string xml file
        multipleChoiceAnswersForQuestion1 = getResources().getStringArray(R.array.answersForQuestion_1);
        return scoreOfQuestionWithMultipleCorrectAnswers(multipleChoiceAnswersForQuestion1,
                checkBoxForQ1_1, checkBoxForQ1_2, checkBoxForQ1_3, checkBoxForQ1_4);
    }

    public int getScoreForQuestion2() {
        return scoreOFQuestionWithEdiText(editTextForQ2, getString(R.string.answerForQuestion_2));
    }

    public int getScoreForQuestion3() {
        return scoreOfQuestionWithRadioButtonGroup(radioGroupForQ3, getString(R.string.answerForQuestion_3));
    }

    public int getScoreForQuestion4() {
        return scoreOFQuestionWithEdiText(editTextForQ4, getString(R.string.answerForQuestion_4));
    }

    public int getScoreForQuestion5() {
        return scoreOFQuestionWithEdiText(editTextForQ5, getString(R.string.answerForQuestion_5));
    }

    public int getScoreForQuestion6() {
        return scoreOfQuestionWithRadioButtonGroup(radioGroupForQ6, getString(R.string.answerForQuestion_6));
    }

    public int getScoreForQuestion7() {
        //get Array form string xml file
        multipleChoiceAnswersForQuestion7 = getResources().getStringArray(R.array.answersForQuestion_7);

        return scoreOfQuestionWithMultipleCorrectAnswers(multipleChoiceAnswersForQuestion7,
                checkBoxForQ7_1, checkBoxForQ7_2, checkBoxForQ7_3, checkBoxForQ7_4);
    }

    public int getScoreForQuestion8() {
        return scoreOFQuestionWithEdiText(editTextForQ8, getString(R.string.answerForQuestion_8));
    }

    public int getScoreForQuestion9() {
        return scoreOfQuestionWithRadioButtonGroup(radioGroupForQ9, getString(R.string.answerForQuestion_9));
    }

    public int getScoreForQuestion10() {
        return scoreOFQuestionWithEdiText(editTextForQ10, getString(R.string.answerForQuestion_10));
    }

    /**
     * This method is use to verify the answers of Questions with RadioGroup Options
     *
     * @param mRadioGroup   is the RadioGroup that is associated to a particular Question
     * @param correctAnswer is the correct answer that exist in the RadioGroup
     * @return maximumScorePerQuestion(10) or 0 if the right answer is selected or not
     */
    public int scoreOfQuestionWithRadioButtonGroup(RadioGroup mRadioGroup, String correctAnswer) {
        if (mRadioGroup.getCheckedRadioButtonId() != -1) {
            int idOfCheckedButton = mRadioGroup.getCheckedRadioButtonId();
            if ((((RadioButton) findViewById(idOfCheckedButton)).getText().toString().trim()).
                    equalsIgnoreCase(correctAnswer.trim())) {
                return maximumScorePerQuestion;
            }
        }
        return 0;
    }

    /**
     * This method is use to verify the answers of Questions with EditText View
     *
     * @param answerEnteredByUser is the answer supplied or typed by the user
     * @param correctAnswer       is the correct answer that correspond to the particular Question
     * @return maximumScorePerQuestion(10) or 0 if the right answer is selected or not
     */
    public int scoreOFQuestionWithEdiText(EditText answerEnteredByUser, String correctAnswer) {
        if (!isEmpty(answerEnteredByUser.getText().toString().trim()) &&
                answerEnteredByUser.getText().toString().trim().equalsIgnoreCase(correctAnswer.trim())) {
            return maximumScorePerQuestion;

        }
        return 0;
    }

    /**
     * This method is use to verify the answer of Questions with multiple answers in Checkbox View
     *
     * @param arrayOfCorrectAnswer is an array that contain the correct answer associated to the Question
     * @param mCheckBox1           is the first CheckBox that might be among the right answer for the Question
     * @param mCheckBox2           is the second CheckBox that might be among the right answer for the Question
     * @param mCheckBox3           is the third CheckBox that might be among the right answer for the Question
     * @param mCheckBox4           is the fourth CheckBox that might be among the right answer for the Question
     * @return maximumScorePerQuestion(10) or 0 if the right answer is selected or not
     */
    public int scoreOfQuestionWithMultipleCorrectAnswers(String[] arrayOfCorrectAnswer,
                                                         CheckBox mCheckBox1, CheckBox mCheckBox2,
                                                         CheckBox mCheckBox3, CheckBox mCheckBox4) {
        List<String> correctAnswerForQuestionsWithCheckBoxes = Arrays.asList(arrayOfCorrectAnswer);
        int numberOfCorrectMultipleChoiceAnswerSelectedByUser = 0;
        if (mCheckBox1.isChecked() && correctAnswerForQuestionsWithCheckBoxes.contains(mCheckBox1.getText().toString().trim())) {
            numberOfCorrectMultipleChoiceAnswerSelectedByUser += 1;
        }
        if (mCheckBox2.isChecked() && correctAnswerForQuestionsWithCheckBoxes.contains(mCheckBox2.getText().toString().trim())) {
            numberOfCorrectMultipleChoiceAnswerSelectedByUser += 1;
        }
        if (mCheckBox3.isChecked() && correctAnswerForQuestionsWithCheckBoxes.contains(mCheckBox3.getText().toString().trim())) {
            numberOfCorrectMultipleChoiceAnswerSelectedByUser += 1;
        }
        if (mCheckBox4.isChecked() && correctAnswerForQuestionsWithCheckBoxes.contains(mCheckBox4.getText().toString().trim())) {
            numberOfCorrectMultipleChoiceAnswerSelectedByUser += 1;
        }
        if (numberOfCorrectMultipleChoiceAnswerSelectedByUser == arrayOfCorrectAnswer.length) {
            return maximumScorePerQuestion;
        }
        return 0;
    }

    //This method starts and Monitor the countDown Time
    private void startTimer() {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                timeIsRunning = false;
                updateCountDownText();
                showToast(getString(R.string.timeUpToast));
                markQuiz();
                setContentView(R.layout.users_decision);
            }
        }.start();
        timeIsRunning = true;
    }

    //This method is use to show the current countDown Time
    private void updateCountDownText() {
        ((TextView) findViewById(R.id.quizCountDownTime)).setText(convertMilliSecToMinuteAndSeconds());
    }

    /**
     * convert milliseconds to Minute and seconds
     *
     * @return minute and second in string
     */
    public String convertMilliSecToMinuteAndSeconds() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;
        return String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
    }

    /**
     * Toast to show notification
     *
     * @param notification is the notification/message to be shown
     */
    public void showToast(String notification) {
        Toast.makeText(this, notification, Toast.LENGTH_LONG).show();
    }

    //Show all the correct answer for Questions in the Quiz
    @SuppressLint("StringFormatInvalid")
    public void showCorrectionForAllQuizQuestion(View view) {
        String fourTab = "\n\t\t\t\t->";
        String correction = "";
        correction += getString(R.string.question_1) + "\n";
        for (int x = 0; x < multipleChoiceAnswersForQuestion1.length; x++) {
            correction += "\t\t\t\t->" + multipleChoiceAnswersForQuestion1[x] + "\n";
        }
        correction += "\n" + getString(R.string.question_2) + fourTab + getString(R.string.answerForQuestion_2) + "\n";
        correction += "\n" + getString(R.string.question_3) + fourTab + getString(R.string.answerForQuestion_3) + "\n";
        correction += "\n" + getString(R.string.question_4) + fourTab + getString(R.string.answerForQuestion_4) + "\n";
        correction += "\n" + getString(R.string.question_5) + fourTab + getString(R.string.answerForQuestion_5) + "\n";
        correction += "\n" + getString(R.string.question_6) + fourTab + getString(R.string.answerForQuestion_6) + "\n";
        correction += "\n" + getString(R.string.question_7) + "\n";
        for (int x = 0; x < multipleChoiceAnswersForQuestion7.length; x++) {
            correction += "\t\t\t\t->" + multipleChoiceAnswersForQuestion7[x] + "\n";
        }
        correction += "\n" + getString(R.string.question_8) + fourTab + getString(R.string.answerForQuestion_8) + "\n";
        correction += "\n" + getString(R.string.question_9) + fourTab + getString(R.string.answerForQuestion_9) + "\n";
        correction += "\n" + getString(R.string.question_10) + fourTab + getString(R.string.answerForQuestion_10) + "\n";
        ((TextView) findViewById(R.id.correctionField)).setText(getString(R.string.displayCorrection, correction));
    }
}
