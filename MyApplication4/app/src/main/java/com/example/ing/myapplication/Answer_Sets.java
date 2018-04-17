package com.example.ing.myapplication;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/**
 * Created by ING on 10-04-2018.
 */

@Entity(foreignKeys = @ForeignKey(entity = Forms.class,
        parentColumns = "formId",
        childColumns = "formId",
        onDelete = CASCADE))

public class Answer_Sets {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int answerSetId;
    @NonNull
    private int formId;
    @NonNull
    private int userId;
    @NonNull
    private int setNumber;

    public Answer_Sets() {
    }

    public int getAnswerSetId() { return answerSetId; }
    public void setAnswerSetId (int answerSetId) { this.answerSetId = answerSetId; }
    public int getFormId() { return formId; }
    public void setFormId (int formId) { this.formId = formId; }
    public int getUserId() { return userId; }
    public void setUserId (int userId) { this.userId = userId; }
    public int getSetNumber() { return setNumber; }
    public void setSetNumber (int setNumber) { this.setNumber = setNumber; }
}
