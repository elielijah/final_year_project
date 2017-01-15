package com.example.elijah.memorymadness;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHandler extends SQLiteOpenHelper {

      private static final int DATABASE_VERSION = 7;
      private static final String DATABASE_NAME = "productDB.db";
      public static final String TABLE_MEMORY = "memory";
      public static final String COLUMN_ID = "_id";
      public static final String COLUMN_USERNAME = "username";
      public static final String COLUMN_USERAGE = "userage";
      public static final String COLUMN_USERGENDER = "usergender";
      public static final String COLUMN_USERSCORE = "userscore";
      public static final String COLUMN_USERLEVEL = "userlevel";


      //We need to pass database information along to superclass
              public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, DATABASE_NAME, factory, DATABASE_VERSION);
          }

      @Override
      public void onCreate(SQLiteDatabase db) {
            String query = "CREATE TABLE " + TABLE_MEMORY + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_USERNAME + " TEXT, " +
                COLUMN_USERAGE + " TEXT, " +
                COLUMN_USERGENDER + " TEXT, " +
                COLUMN_USERSCORE + " TEXT, " +
                COLUMN_USERLEVEL + " TEXT" +
                ");";
            db.execSQL(query);
          }

      @Override
      public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEMORY);
            onCreate(db);



          }

      //Add a new row to the database
              public void addUser(Users users){
            ContentValues values = new ContentValues();
            values.put(COLUMN_USERNAME, users.get_username());
            values.put(COLUMN_USERAGE, users.get_userage());
            values.put(COLUMN_USERGENDER, users.get_usergender());
            SQLiteDatabase db = getWritableDatabase();
            db.insert(TABLE_MEMORY, null, values);
            db.close();
          }

    public long numb_of_users(){

        return DatabaseUtils.queryNumEntries(getReadableDatabase(), TABLE_MEMORY);
    }

    public void addScore(Integer id, Integer score, String level){

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USERSCORE, score);
        values.put(COLUMN_USERLEVEL, level);
        db.update(TABLE_MEMORY, values, COLUMN_ID+"="+id, null);
    }
    public void addLevel(Integer id, String level){

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USERLEVEL, level);
        db.update(TABLE_MEMORY, values, COLUMN_ID+"="+id, null);
    }

      //Delete a product from the database

     public void deleteUser(Integer userID){
            SQLiteDatabase db = getWritableDatabase();
            db.execSQL("DELETE FROM " + TABLE_MEMORY + " WHERE " + COLUMN_ID + "=\"" + userID + "\";");
          }


      public String databaseToString(){
            String dbString = "";
            SQLiteDatabase db = getWritableDatabase();
            String query = "SELECT * FROM " + TABLE_MEMORY + " WHERE 1";

            //Cursor points to a location in your results
            Cursor c = db.rawQuery(query, null);
            //Move to the first row in your results
            c.moveToFirst();

            //Position after the last row means the end of the results
            while (!c.isAfterLast()) {
                  if (c.getString(c.getColumnIndex("username")) != null) {
                      dbString += c.getString(c.getColumnIndex("_id"));
                      dbString += ",";
                      dbString += c.getString(c.getColumnIndex("username"));
                      dbString += ",";
                      dbString += c.getString(c.getColumnIndex("userage"));
                      dbString += ",";
                      dbString += c.getString(c.getColumnIndex("usergender"));
                      dbString += ",";
                      dbString += c.getString(c.getColumnIndex("userscore"));
                      dbString += ",";
                      dbString += c.getString(c.getColumnIndex("userlevel"));
                      dbString += "\n";
                      }
                  c.moveToNext();
                }
            c.close();
            db.close();
            return dbString;
          }

      public String getUserDetails(Integer id){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_MEMORY + " WHERE " + COLUMN_ID + "=" + id;

        //Cursor points to a location in your results
        Cursor c = db.rawQuery(query, null);
        //Move to the first row in your results
        c.moveToFirst();

        //Position after the last row means the end of the results
        while (!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex("username")) != null) {
                dbString += c.getString(c.getColumnIndex("_id"));
                dbString += ",";
                dbString += c.getString(c.getColumnIndex("username"));
                dbString += ",";
                dbString += c.getString(c.getColumnIndex("userage"));
                dbString += ",";
                dbString += c.getString(c.getColumnIndex("usergender"));
                dbString += ",";
                dbString += c.getString(c.getColumnIndex("userscore"));
                dbString += ",";
                dbString += c.getString(c.getColumnIndex("userlevel"));
                dbString += "\n";
            }
            c.moveToNext();
        }
        c.close();
        db.close();
        return dbString;
    }

}