package com.example.osos.deathnote;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.osos.deathnote.DataBase.AppDataBase;
import com.example.osos.deathnote.DataBase.NoteEntity;

import java.util.List;


public class MainViewModel extends AndroidViewModel {

    public LiveData<List<NoteEntity>> getNotes() {
        return notes;
    }

    private LiveData<List<NoteEntity>> notes;

    public MainViewModel(@NonNull Application application) {
        super(application);

        AppDataBase dataBase=AppDataBase.getsInstance(this.getApplication());
        notes=dataBase.noteDao().loadAllNotes();

    }

}
