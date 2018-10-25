package com.example.osos.deathnote.DataBase;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "note")
public class NoteEntity {

	@PrimaryKey(autoGenerate = true)
	private int id;
	private String title;
	private String contant;
	private boolean completed;

	public NoteEntity(String title, String contant, boolean completed) {
		this.title = title;
		this.contant = contant;
		this.completed = completed;
	}

	public String getContant() {
		return contant;
	}

	public void setContant(String contant) {
		this.contant = contant;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setCompleted(boolean completed){
		this.completed = completed;
	}

	public boolean isCompleted(){
		return completed;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}
}
