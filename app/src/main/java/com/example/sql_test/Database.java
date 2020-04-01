package com.example.sql_test;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.nfc.Tag;

import androidx.annotation.Nullable;
public class Database extends SQLiteOpenHelper {

    private static final String DATABASE = "Database.db";
    public static final String TABLE = "Database_Table";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String EMAIL = "email";
    public static final String AGE = "age";

    public Database(@Nullable Context context) {
        super( context, DATABASE, null, 1 );
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL( "create table " + TABLE + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, EMAIl TEXT, AGE INTERGER)" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    boolean add(String Name, String Email, String Age) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put( "Name", Name );
        contentValues.put( "Email", Email );
        contentValues.put( "Age", Age );
        long result = sqLiteDatabase.insert( TABLE, null, contentValues );
        return result > 0;
    }

    boolean update(String ID, String Name, String Email, String Age) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put( "ID", ID );
        contentValues.put( "Name", Name );
        contentValues.put( "Email", Email );
        contentValues.put( "Age", Age );
        sqLiteDatabase.update( TABLE, contentValues, "ID = ?", new String[]{ID} );
        return true;

    }
    
    boolean check(String Name, String Email, String Age) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String[] column = {ID};
        String selection = NAME + "=?" + " AND " + EMAIL + "=?" + " AND " + AGE + "=?";
        String[] selectionArgs = {Name, Email, Age};
        Cursor cursor = sqLiteDatabase.query( TABLE, column, selection, selectionArgs,
                                              null, null, null );
        int cursor1 = cursor.getCount();
        cursor.close();
        sqLiteDatabase.close();
        return cursor1 > 0;
    }

    Cursor show() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.rawQuery( "select * from " + TABLE, null );
    }

    Integer delete(String ID) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.delete( TABLE, "ID = ?", new String[]{ID} );
    }
}
