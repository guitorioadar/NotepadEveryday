package com.example.SimpleSqliteExample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ContactDbHelper extends SQLiteOpenHelper {

    private final String TAG = "ContactDbHelper";

    public  static final String DATABASE_NAME = "contact_db";
    public static final int DATABASE_VERSION = 1;

    public static final String CREATE_TABLE = "create table "+ContactContract.ContactEntry.TABLE_NAME+
            "("+ ContactContract.ContactEntry.CONTACT_ID+" number, "+
            ContactContract.ContactEntry.NAME+" text, "+
            ContactContract.ContactEntry.EMAIL+" text);";
    public static final String DROP_TABLE = "drop table if exists "+ ContactContract.ContactEntry.TABLE_NAME;

    public ContactDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d(TAG, "ContactDbHelper: Database Created");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        Log.d(TAG, "onCreate: Table Created....");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(DROP_TABLE);
        onCreate(db);
    }

    public void addContact(int id,String name, String email, SQLiteDatabase database){

        ContentValues contentValues = new ContentValues();
        contentValues.put(ContactContract.ContactEntry.CONTACT_ID,id);
        contentValues.put(ContactContract.ContactEntry.NAME,name);
        contentValues.put(ContactContract.ContactEntry.EMAIL,email);
        
        database.insert(ContactContract.ContactEntry.TABLE_NAME,null,contentValues);
        Log.d(TAG, "addContact: One row inserted");

    }

    public Cursor readContact(SQLiteDatabase database){

        String[] projections = {ContactContract.ContactEntry.CONTACT_ID,ContactContract.ContactEntry.NAME, ContactContract.ContactEntry.EMAIL};

        Cursor cursor = database.query(ContactContract.ContactEntry.TABLE_NAME,projections,null,null,null,null,null);

        return cursor;
    }

    public void updateContacts(int id, String name,String email,SQLiteDatabase database){
        ContentValues contentValues = new ContentValues();

        contentValues.put(ContactContract.ContactEntry.NAME,name);
        contentValues.put(ContactContract.ContactEntry.EMAIL,email);

        String selection = ContactContract.ContactEntry.CONTACT_ID + " = "+id;

        database.update(ContactContract.ContactEntry.TABLE_NAME,contentValues,selection,null);


    }

    public void deleteContact(int id,SQLiteDatabase database){

        String selection = ContactContract.ContactEntry.CONTACT_ID+" = "+id;

        database.delete(ContactContract.ContactEntry.TABLE_NAME,selection,null);


    }

}
