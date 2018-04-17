package com.example.ing.myapplication;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/**
 * Created by ING on 10-04-2018.
 */

@Entity(foreignKeys = {@ForeignKey(entity = Questions.class,
        parentColumns = "questionId",
        childColumns = "questionId",
        onDelete = CASCADE),@ForeignKey(entity = Answer_Sets.class,
        parentColumns = "answerSetId",
        childColumns = "setId",
        onDelete = CASCADE)})

public class User_Answers {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int answerId;
    @NonNull
    private int questionId;
    @NonNull
    private int setId;
    private String answer;

    public User_Answers() {
    }

    public int getAnswerSetId() { return answerId; }
    public void setAnswerSetId (int answerId) { this.answerId = answerId; }
    public int getQuestionId() { return questionId; }
    public void setQuestionId (int questionId) { this.questionId = questionId; }
    public int getSetId() { return setId; }
    public void setSetId (int setId) { this.setId = setId; }
    public String getAnswer() { return answer; }
    public void setAnswer (String answer) { this.answer = answer; }
}
