package com.example.ing.myapplication;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by ING on 03-04-2018.
 */

@Dao
public interface DaoAccess {

    @Insert
    long insertOnlySingleForm (Forms forms);
    @Insert
    void insertOnlySingleQuestion (Questions Questions);
    @Insert
    void insertMultipleForms (List<Forms> FormList);
    @Insert
    void insertMultipleQuestions (List<Questions> QuestionList);
    @Query("SELECT * FROM Forms WHERE formId = :formId")
    Forms fetchOneFormsbyFormId (int formId);
    @Query("SELECT * FROM Forms")
    List<Forms> fetchAllForms();
    @Query("SELECT * FROM Questions WHERE formId = :formId")
    List<Questions> fetchAllQuestionsId(int formId);
    @Query("DELETE FROM Forms")
    void nukeTableF();
    @Query("DELETE FROM Questions")
    void nukeTableQ();
    @Update
    void updateMovie (Forms forms);
    @Delete
    void deleteMovie (Forms forms);
}