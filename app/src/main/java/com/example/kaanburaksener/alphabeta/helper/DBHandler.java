package com.example.kaanburaksener.alphabeta.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.kaanburaksener.alphabeta.Letter;
import com.example.kaanburaksener.alphabeta.Word;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "alphabeta_russian";

    private static final String LETTER_TABLE_NAME = "letter";
    private static final String LETTER_COLUMN_ID = "id";
    private static final String LETTER_COLUMN_UPPERCASE_LETTER = "uppercase_letter";
    private static final String LETTER_COLUMN_LOWERCASE_LETTER = "lowercase_letter";
    private static final String LETTER_COLUMN_PRONUNCIATION = "pronunciation";

    private static final String WORD_TABLE_NAME = "word";
    private static final String WORD_COLUMN_IN_RUSSIAN = "in_russian";
    private static final String WORD_COLUMN_IN_TURKISH = "in_turkish";
    private static final String WORD_COLUMN_PRONUNCIATION = "pronunciation";
    private static final String WORD_COLUMN_FIRST_LETTER_ID = "first_letter_id";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create table statements
        String CREATE_LETTER_TABLE = "CREATE TABLE IF NOT EXISTS " + LETTER_TABLE_NAME + "(" +
                LETTER_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                LETTER_COLUMN_UPPERCASE_LETTER + " TEXT, "+
                LETTER_COLUMN_LOWERCASE_LETTER + " TEXT, "+
                LETTER_COLUMN_PRONUNCIATION + " TEXT);";

        String CREATE_WORD_TABLE = "CREATE TABLE IF NOT EXISTS " + WORD_TABLE_NAME + "(" +
                WORD_COLUMN_IN_RUSSIAN + " TEXT," +
                WORD_COLUMN_IN_TURKISH + " TEXT, " +
                WORD_COLUMN_PRONUNCIATION + " TEXT," +
                WORD_COLUMN_FIRST_LETTER_ID + " INTEGER," +
                "FOREIGN KEY(" + WORD_COLUMN_FIRST_LETTER_ID + ") REFERENCES " + LETTER_TABLE_NAME + "(" + LETTER_COLUMN_ID + "));";

        String letter1 = "INSERT INTO " + LETTER_TABLE_NAME + " VALUES(1, 'А', 'а', 'a');";
        String letter2 = "INSERT INTO " + LETTER_TABLE_NAME + " VALUES(null, 'Б', 'б', 'b');";
        String letter3 = "INSERT INTO " + LETTER_TABLE_NAME + " VALUES(null, 'В', 'в', 'v');";
        String letter4 = "INSERT INTO " + LETTER_TABLE_NAME + " VALUES(null, 'Г', 'г', 'g');";
        String letter5 = "INSERT INTO " + LETTER_TABLE_NAME + " VALUES(null, 'Д', 'д', 'd');";
        String letter6 = "INSERT INTO " + LETTER_TABLE_NAME + " VALUES(null, 'Е', 'е', 'ye');";
        String letter7 = "INSERT INTO " + LETTER_TABLE_NAME + " VALUES(null, 'Ё', 'ё', 'yo');";
        String letter8 = "INSERT INTO " + LETTER_TABLE_NAME + " VALUES(null, 'Ж', 'ж', 'j');";
        String letter9 = "INSERT INTO " + LETTER_TABLE_NAME + " VALUES(null, 'З', 'з', 'z');";
        String letter10 = "INSERT INTO " + LETTER_TABLE_NAME + " VALUES(null, 'И', 'и', 'i');";
        String letter11 = "INSERT INTO " + LETTER_TABLE_NAME + " VALUES(null, 'Й', 'й', 'y');";
        String letter12 = "INSERT INTO " + LETTER_TABLE_NAME + " VALUES(null, 'К', 'к', 'k');";
        String letter13 = "INSERT INTO " + LETTER_TABLE_NAME + " VALUES(null, 'Л', 'л', 'l');";
        String letter14 = "INSERT INTO " + LETTER_TABLE_NAME + " VALUES(null, 'М', 'м', 'm');";
        String letter15 = "INSERT INTO " + LETTER_TABLE_NAME + " VALUES(null, 'Н', 'н', 'n');";
        String letter16 = "INSERT INTO " + LETTER_TABLE_NAME + " VALUES(null, 'О', 'о', 'o');";
        String letter17 = "INSERT INTO " + LETTER_TABLE_NAME + " VALUES(null, 'П', 'п', 'p');";
        String letter18 = "INSERT INTO " + LETTER_TABLE_NAME + " VALUES(null, 'Р', 'р', 'r');";
        String letter19 = "INSERT INTO " + LETTER_TABLE_NAME + " VALUES(null, 'С', 'с', 's');";
        String letter20 = "INSERT INTO " + LETTER_TABLE_NAME + " VALUES(null, 'Т', 'т', 't');";
        String letter21 = "INSERT INTO " + LETTER_TABLE_NAME + " VALUES(null, 'У', 'у', 'u');";
        String letter22 = "INSERT INTO " + LETTER_TABLE_NAME + " VALUES(null, 'Ф', 'ф', 'f');";
        String letter23 = "INSERT INTO " + LETTER_TABLE_NAME + " VALUES(null, 'Х', 'х', 'h');";
        String letter24 = "INSERT INTO " + LETTER_TABLE_NAME + " VALUES(null, 'Ц', 'ц', 'ts');";
        String letter25 = "INSERT INTO " + LETTER_TABLE_NAME + " VALUES(null, 'Ч', 'ч', 'ç');";
        String letter26 = "INSERT INTO " + LETTER_TABLE_NAME + " VALUES(null, 'Ш', 'ш', 'ş');";
        String letter27 = "INSERT INTO " + LETTER_TABLE_NAME + " VALUES(null, 'Щ', 'щ', 'şça');";
        String letter28 = "INSERT INTO " + LETTER_TABLE_NAME + " VALUES(null, 'Ъ', 'ъ', 'yumuşak');";
        String letter29 = "INSERT INTO " + LETTER_TABLE_NAME + " VALUES(null, 'Ы', 'ы', 'ı');";
        String letter30 = "INSERT INTO " + LETTER_TABLE_NAME + " VALUES(null, 'Ь', 'ь', 'sert');";
        String letter31 = "INSERT INTO " + LETTER_TABLE_NAME + " VALUES(null, 'Э', 'э', 'z');";
        String letter32 = "INSERT INTO " + LETTER_TABLE_NAME + " VALUES(null, 'Ю', 'ю', 'yu');";
        String letter33 = "INSERT INTO " + LETTER_TABLE_NAME + " VALUES(null, 'Я', 'я', 'ya');";

        //Executing the SQL statements
        //Create Tables
        db.execSQL(CREATE_LETTER_TABLE);
        db.execSQL(CREATE_WORD_TABLE);

        //Insert Default Values
        db.execSQL(letter1);
        db.execSQL(letter2);
        db.execSQL(letter3);
        db.execSQL(letter4);
        db.execSQL(letter5);
        db.execSQL(letter6);
        db.execSQL(letter7);
        db.execSQL(letter8);
        db.execSQL(letter9);
        db.execSQL(letter10);
        db.execSQL(letter11);
        db.execSQL(letter12);
        db.execSQL(letter13);
        db.execSQL(letter14);
        db.execSQL(letter15);
        db.execSQL(letter16);
        db.execSQL(letter17);
        db.execSQL(letter18);
        db.execSQL(letter19);
        db.execSQL(letter20);
        db.execSQL(letter21);
        db.execSQL(letter22);
        db.execSQL(letter23);
        db.execSQL(letter24);
        db.execSQL(letter25);
        db.execSQL(letter26);
        db.execSQL(letter27);
        db.execSQL(letter28);
        db.execSQL(letter29);
        db.execSQL(letter30);
        db.execSQL(letter31);
        db.execSQL(letter32);
        db.execSQL(letter33);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + LETTER_TABLE_NAME);
        onCreate(db);
    }

    public void addWord(String inRussian, String inTurkish, String pronunciation, int firstLetterID) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(WORD_COLUMN_IN_RUSSIAN, inRussian);
        contentValues.put(WORD_COLUMN_IN_TURKISH, inTurkish);
        contentValues.put(WORD_COLUMN_PRONUNCIATION, pronunciation);
        contentValues.put(WORD_COLUMN_FIRST_LETTER_ID, firstLetterID);

        db.insert(WORD_TABLE_NAME, null, contentValues);
        db.close();
    }

    public Letter getLetter(int id) {
        Letter letter;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "SELECT * FROM " + LETTER_TABLE_NAME + " WHERE " + LETTER_COLUMN_ID + " = " + id + "", null );

        if (res != null) {
            res.moveToFirst();
            letter = new Letter(res.getInt(res.getColumnIndex(LETTER_COLUMN_ID)), res.getString(res.getColumnIndex(LETTER_COLUMN_UPPERCASE_LETTER)), res.getString(res.getColumnIndex(LETTER_COLUMN_LOWERCASE_LETTER)),res.getString(res.getColumnIndex(LETTER_COLUMN_PRONUNCIATION)));
            return letter;
        }
        return null;
    }

    public List<Letter> getLetters() {
        List<Letter> letters = new ArrayList<Letter>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "SELECT * FROM " + LETTER_TABLE_NAME, null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            Letter newLetter = new Letter(res.getInt(res.getColumnIndex(LETTER_COLUMN_ID)), res.getString(res.getColumnIndex(LETTER_COLUMN_UPPERCASE_LETTER)), res.getString(res.getColumnIndex(LETTER_COLUMN_LOWERCASE_LETTER)),res.getString(res.getColumnIndex(LETTER_COLUMN_PRONUNCIATION)));
            letters.add(newLetter);
            res.moveToNext();
        }

        return letters;
    }

    public List<Word> getWords() {
        List<Word> words = new ArrayList<Word>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "SELECT * FROM " + WORD_TABLE_NAME, null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            Word newWord = new Word(res.getString(res.getColumnIndex(WORD_COLUMN_IN_RUSSIAN)), res.getString(res.getColumnIndex(WORD_COLUMN_IN_TURKISH)), res.getString(res.getColumnIndex(WORD_COLUMN_PRONUNCIATION)), res.getInt(res.getColumnIndex(WORD_COLUMN_FIRST_LETTER_ID)));
            words.add(newWord);
            res.moveToNext();
        }

        return words;
    }

    public List<Word> getWords(int firstLetterID) {
        List<Word> words = new ArrayList<Word>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "SELECT * FROM " + WORD_TABLE_NAME + " WHERE " + WORD_COLUMN_FIRST_LETTER_ID + " = " + firstLetterID + "", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            Word newWord = new Word(res.getString(res.getColumnIndex(WORD_COLUMN_IN_RUSSIAN)), res.getString(res.getColumnIndex(WORD_COLUMN_IN_TURKISH)), res.getString(res.getColumnIndex(WORD_COLUMN_PRONUNCIATION)), res.getInt(res.getColumnIndex(WORD_COLUMN_FIRST_LETTER_ID)));
            words.add(newWord);
            res.moveToNext();
        }

        return words;
    }
}
