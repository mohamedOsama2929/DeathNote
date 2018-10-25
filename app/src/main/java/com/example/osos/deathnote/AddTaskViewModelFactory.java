package com.example.osos.deathnote;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.osos.deathnote.DataBase.AppDataBase;

public class AddTaskViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final AppDataBase mDb;
    private final int mNoteId;


    public AddTaskViewModelFactory(AppDataBase mDb, int mNoteId) {
        this.mDb = mDb;
        this.mNoteId = mNoteId;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T)new AddTaskViewModel(mDb,mNoteId);
    }
}
