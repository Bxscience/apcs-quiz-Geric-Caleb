package com.example.agent.midyearjava;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.SlidingDrawer;
import android.widget.Toast;
import java.io.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


// class to provide operations with database

public class MyDataBaseHelper extends SQLiteOpenHelper implements Serializable {




    ArrayList<ArrayList<ArrayList<ArrayList<ArrayList<String>>>>> source = new ArrayList<ArrayList<ArrayList<ArrayList<ArrayList<String>>>>>();



    // Database name
    public static String DATABASE_QUESTION = "questionBank.db";
    // Current version of database
    private Context context;
    private static final int DATABASE_VERSION = 1;
    // Database table name
    private static final String TABLE_QUESTION = "AllQuestions";
    // All fields used in database table
    private static final String KEY_ID = "id";
    private static final String QUESTION = "Question";
    private static final String TOPIC = "Topic";
    private static final String CHOICE1 = "choice1";
    private static final String CHOICE2 = "choice2";
    private static final String CHOICE3 = "choice3";
    private static final String CHOICE4 = "choice4";
    private static final String CHOICE5 = "choice5";
    private static final String ANSWER = "answer";

    String CREATE_TABLE_QUESTION = "CREATE TABLE "
            + TABLE_QUESTION + "(" + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT,"+ QUESTION + " TEXT,"+ TOPIC + " TEXT, "
            + CHOICE1 + " TEXT, " + CHOICE2 + " TEXT, " + CHOICE3 + " TEXT, "
            + CHOICE4 + " TEXT, "+ CHOICE5 + " TEXT, " + ANSWER + " TEXT);";

    // Question Table Create Query in this string

    public MyDataBaseHelper(Context context) {
        super(context, DATABASE_QUESTION, null, DATABASE_VERSION);
        this.context=context;
        context.deleteDatabase(DATABASE_QUESTION);

    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_TABLE_QUESTION);
    }



    public void initialize() throws IOException {
        SQLiteDatabase db = this.getWritableDatabase();
            InputStream is = null;
            try {
                is = context.getAssets().open("question.txt");
            } catch (IOException e) {
                Log.d("CRUPS", "huhuhuh");
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            String line = "";
            int count=0;
                while((line = reader.readLine()) != null){
                    String[] questionsArr = line.split("♪");
                    ContentValues values = new ContentValues();
                    values.put(KEY_ID, count);
                    values.put(TOPIC, questionsArr[0]);
                    Log.d("CRUPS", ""+questionsArr[1]);
                    values.put(QUESTION, questionsArr[1]);
                    values.put(CHOICE1, questionsArr[2]);
                    values.put(CHOICE2, questionsArr[3]);
                    values.put(CHOICE3, questionsArr[4]);
                    values.put(CHOICE4, questionsArr[5]);
                    values.put(CHOICE5, questionsArr[6]);
                    values.put(ANSWER, questionsArr[7]);
                    Log.d("CRUPS", "1");
                    db.insert(TABLE_QUESTION, null, values);
                    Log.d("CRUPS", "2");
                    count++;
                    Log.d("CRUPS", ""+count);
                }
        }







public void setSource(ArrayList<ArrayList<ArrayList<ArrayList<ArrayList<String>>>>> newOne){
    this.source=newOne;
}

    /**
     * This method is called when any modifications in database are done like
     * version is updated or database schema is changed
     */
    @Override

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTION); // drop table if exists
        onCreate(db);
    }





    /**
     * This method is used to add question detail in question Table
     */
    public void addQ(Question question) {
        SQLiteDatabase db = this.getWritableDatabase();
        // Creating content values
        ContentValues values = new ContentValues();
        values.put(KEY_ID, question.getCount());
        values.put(TOPIC, question.getTopic());
        values.put(QUESTION, question.getQuestion());
        values.put(CHOICE1, question.getChoice(0));
        values.put(CHOICE2, question.getChoice(1));
        values.put(CHOICE3,  question.getChoice(2));
        values.put(CHOICE4,  question.getChoice(3));
        values.put(CHOICE5,  question.getChoice(4));
        values.put(ANSWER, question.getAnswer());
        // insert row in question table
        long insert = db.insert(TABLE_QUESTION, null, values);

    }






    public List<Question> getQuestions(String topic){
        List<Question> questionsList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_QUESTION, new String[] { KEY_ID, TOPIC,
                        QUESTION, CHOICE1, CHOICE2, CHOICE3, CHOICE4, CHOICE5, ANSWER}, TOPIC + "=?",
                new String[] { topic }, null, null, null, null);
        if(cursor.moveToFirst()){
            do{
                Question q = new Question();
                q.setQuestion(cursor.getString(2));
                q.setChoice(0,cursor.getString(3));
                q.setChoice(1,cursor.getString(4));
                q.setChoice(2,cursor.getString(5));
                q.setChoice(3,cursor.getString(6));
                q.setChoice(4,cursor.getString(7));
                q.setAnswer(cursor.getString(8));

                questionsList.add(q);
            } while(cursor.moveToNext());
        }
        cursor.close();
        return questionsList;
    }
}
