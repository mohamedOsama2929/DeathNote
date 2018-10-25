package com.example.osos.deathnote;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.osos.deathnote.DataBase.AppDataBase;
import com.example.osos.deathnote.DataBase.NoteEntity;

public class AddTaskViewModel extends ViewModel {

    private LiveData<NoteEntity> note;

    public AddTaskViewModel(AppDataBase dataBase,int noteId) {
        note=dataBase.noteDao().loadNoteById(noteId);
    }

    public LiveData<NoteEntity> getNote() {
        return note;
    }



}
