package com.example.ing.myapplication;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import org.json.JSONObject;

/**
 * Created by ING on 03-04-2018.
 */

@Entity
public class Forms {
    @NonNull
    @PrimaryKey (autoGenerate = true)
    private int formId;
    private String formName;
    private String formDate;
    private String formComment;
    private String formCategory;

    public Forms() {
    }

    public int getFormId() { return formId; }
    public void setFormId (int formId) { this.formId = formId; }
    public String getFormName() { return formName; }
    public void setFormName (String formName) { this.formName = formName; }
    public String getFormDate() { return formDate; }
    public void setFormDate (String formDate) { this.formDate = formDate; }
    public String getFormComment() { return formComment; }
    public void setFormComment (String formComment) { this.formComment = formComment; }
    public String getFormCategory() { return formCategory; }
    public void setFormCategory (String formCategory) { this.formCategory = formCategory; }
}