package com.androvaid.notepadeveryday.notedb;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.androvaid.notepadeveryday.notedb.dao.NoteDao;
import com.androvaid.notepadeveryday.notedb.model.Note;
import com.androvaid.notepadeveryday.util.Constants;


@Database(entities = {Note.class},version = 1)
public abstract class NoteDatabase extends RoomDatabase{

    public abstract NoteDao getNoteDao();

    private static NoteDatabase noteDB;

    public static NoteDatabase getInstance(Context context){

        if(null == noteDB){
            noteDB = buildDatabaseInstance(context);
        }
        return noteDB;
    }

    private static NoteDatabase buildDatabaseInstance(Context context){
        return Room.databaseBuilder(context,NoteDatabase.class, Constants.DB_NAME).allowMainThreadQueries().build();
    }

    public void cleanup(){
        noteDB = null;
    }

}
