package com.example.ing.myapplication;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.arch.persistence.room.ForeignKey;
import static android.arch.persistence.room.ForeignKey.CASCADE;

/**
 * Created by ING on 10-04-2018.
 */

@Entity(foreignKeys = @ForeignKey(entity = Forms.class,
        parentColumns = "formId",
        childColumns = "formId",
        onDelete = CASCADE))

public class Questions {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int questionId;
    @NonNull
    private int formId;
    private String questionText;
    private String questionType;

    public Questions() {
    }

    public int getQuestionId() { return questionId; }
    public void setQuestionId (int questionId) { this.questionId = questionId; }
    public int getFormId() { return formId; }
    public void setFormId (int formId) { this.formId = formId; }
    public String getQuestionText() { return questionText; }
    public void setQuestionText (String questionText) { this.questionText = questionText; }
    public String getQuestionType() { return questionType; }
    public void setQuestionType (String questionType) { this.questionType = questionType; }
}
