package com.example.osos.deathnote.DataBase;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface DaoInterface {

    @Query("SELECT * FROM note ORDER BY id")
    LiveData<List<NoteEntity>> loadAllNotes();

    @Query("SELECT * FROM note WHERE id =:id")
    LiveData<NoteEntity> loadNoteById(int id);

    @Insert
    void insertNote(NoteEntity note);

    @Update(onConflict =OnConflictStrategy.REPLACE)
    void updateNote(NoteEntity note);

    @Delete
    void deleteNote(NoteEntity note);
}
