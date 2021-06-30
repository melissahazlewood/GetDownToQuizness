package com.example.getdowntoquizness;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class DBHandler extends SQLiteOpenHelper {
    // Database Information
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "DBQuizTaker.db";

    // Table Names
    public static final String USER_TABLE_NAME = "users";
    public static final String QUIZ_TABLE_NAME = "quizzes";
    public static final String QUESTION_TABLE_NAME = "questions";

    // User Table Information
    public static final String USER_COLUMN_ID = "user_id"; //TODO: make into a common column named "id" for use in all tables?
    public static final String USER_COLUMN_NAME = "user_name";
    public static final String USER_COLUMN_USERNAME = "user_username";
    public static final String USER_COLUMN_PASSWORD = "user_password";
    public static final String USER_COLUMN_RETYPE_PASSWORD = "user_retype_password";
    public static final String USER_COLUMN_EMAIL = "user_email";
    public static final String USER_COLUMN_IS_ADMIN = "user_is_admin";
    public static final String USER_COLUMN_IS_SELECTED = "user_is_selected";
    //TODO: add phone number column?
    //TODO: add quiz and grade columns

    // Quiz Table Information
    // TODO

    // Question Table Information
    // TODO

    // Table Create Statements
    private static final String CREATE_TABLE_USERS = "CREATE TABLE " + USER_TABLE_NAME + " ( " +
            USER_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            USER_COLUMN_NAME + " TEXT, " +
            USER_COLUMN_USERNAME + " TEXT, " +
            USER_COLUMN_PASSWORD + " TEXT, " +
            USER_COLUMN_RETYPE_PASSWORD + " TEXT, " +
            USER_COLUMN_EMAIL + " TEXT, " +
            USER_COLUMN_IS_ADMIN + " BOOLEAN, " +
            USER_COLUMN_IS_SELECTED + " BOOLEAN )"; //TODO: add phone, quiz, and grade cols
    //TODO: make create statement for quiz table
    //TODO: make create statement for question table


    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        if (isEmptyUserTable())
            initializeFirstAdmin();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the tables
        db.execSQL(CREATE_TABLE_USERS);
        //TODO: insert other two create table statements
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // On upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE_NAME);
        //TODO: insert other two drop table statements
    }

    public void initializeFirstAdmin() {
        // Add the first admin user
        User userAdmin = new User("N/A", "user-admin", "1234", "1234", "N/A", true, false);
        addUserHandler(userAdmin);
    }

    public boolean isEmptyUserTable() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + USER_TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);

        return !cursor.moveToFirst();
    }

    public String loadUsersHandler() {
        String result = "";
        String query = "SELECT * FROM " + USER_TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        while (cursor.moveToFirst()) {
            String result_0 = cursor.getString(0);
            String result_1 = cursor.getString(1);
            String result_2 = cursor.getString(2);
            String result_3 = cursor.getString(3);
            String result_4 = cursor.getString(4);
            String result_5 = cursor.getString(5);
            String result_6 = cursor.getString(6);
            result += result_0 + result_1 + result_2 + result_3 + result_4 + result_5 + result_6 + System.lineSeparator();
            //TODO: add boolean columns?
            //TODO: add phone number possibly
            //TODO: add quizzes and grades
        }

        cursor.close();
        db.close();
        return result;
    }

    public void addUserHandler(User user) {
        ContentValues values = new ContentValues();

        values.put(USER_COLUMN_NAME, user.getName());
        values.put(USER_COLUMN_USERNAME, user.getUsername());
        values.put(USER_COLUMN_PASSWORD, user.getPassword());
        values.put(USER_COLUMN_RETYPE_PASSWORD, user.getRetypePassword());
        values.put(USER_COLUMN_EMAIL, user.getEmail());
        values.put(USER_COLUMN_IS_ADMIN, user.getIsAdmin());
        values.put(USER_COLUMN_IS_SELECTED, user.getIsSelected());
        //TODO: add phone number to values possibly
        //TODO: add quizzes and grades

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(USER_TABLE_NAME, null, values);
        db.close();
    }

    public Boolean checkUsernameHandler(String username) {
        Boolean found = true;
        String query = "SELECT * FROM " + USER_TABLE_NAME + " WHERE " + USER_COLUMN_USERNAME + " = '" + username + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst())
            cursor.close();
        else
            found = false;

        db.close();
        return found;
    }

    public Boolean checkCredentialsHandler(String username, String password) {
        Boolean isCorrect = false;
        String query = "SELECT " + USER_COLUMN_PASSWORD + " FROM " + USER_TABLE_NAME + " WHERE " + USER_COLUMN_USERNAME + " = '" + username + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            if (cursor.getString(0).equals(password))
                isCorrect = true;
            cursor.close();
        }

        db.close();
        return isCorrect;
    }

    public boolean isAdminHandler(String username) {
        boolean isAdmin = false;
        String query = "SELECT " + USER_COLUMN_IS_ADMIN + " FROM " + USER_TABLE_NAME + " WHERE " + USER_COLUMN_USERNAME + " = '" + username + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            if (cursor.getInt(0) == 1)
                isAdmin = true;
            cursor.close();
        }

        db.close();
        return isAdmin;
    }


    public ArrayList<HashMap<String, String>> getStudentsListHandler() {
        String query = "SELECT " + USER_COLUMN_NAME + ", " + USER_COLUMN_USERNAME + " FROM " + USER_TABLE_NAME + " WHERE " + USER_COLUMN_IS_ADMIN + " = '" + 0 + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        ArrayList<HashMap<String, String>> studentsList = new ArrayList<>();

        cursor.moveToFirst();
        do {
            HashMap<String, String> student = new HashMap<>();
            student.put("name", cursor.getString(0));
            student.put("username", cursor.getString(1));
            studentsList.add(student);
        } while (cursor.moveToNext());
        cursor.close();

        db.close();
        return studentsList;
    }

    public void removeSelectedStudentsHandler(ArrayList<String> selectedStudentUsernames) {
        for (String student : selectedStudentUsernames)
            removeStudentHandler(student);
    }

    public void removeStudentHandler(String studentUsername) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(USER_TABLE_NAME, USER_COLUMN_USERNAME + " = '" + studentUsername + "'", null);
        db.close();
    }

    public boolean isSelectedHandler(String username) {
        //TODO: maybe combine boolean handlers into a generic method since they're so similar
        boolean isSelected = false;
        String query = "SELECT " + USER_COLUMN_IS_SELECTED + " FROM " + USER_TABLE_NAME + " WHERE " + USER_COLUMN_USERNAME + " = '" + username + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            if (cursor.getInt(0) == 1)
                isSelected = true;
            cursor.close();
        }

        db.close();
        return isSelected;
    }

    public void setSelectedHandler(boolean isSelected, String username) {
        int isSelectedAsInt = 0;
        if (isSelected) isSelectedAsInt = 1;

        String query = "UPDATE " + USER_TABLE_NAME + " SET " + USER_COLUMN_IS_SELECTED + " = '" + isSelectedAsInt + "' WHERE " + USER_COLUMN_USERNAME + " = '" + username + "'";

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(query);
        db.close();
    }

    public ArrayList<String> getSelectedHandler() {
        String query = "SELECT " + USER_COLUMN_USERNAME + " FROM " + USER_TABLE_NAME + " WHERE " + USER_COLUMN_IS_SELECTED + " = '" + 1 + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        ArrayList<String> selectedStudentsList = new ArrayList<>();

        while (cursor.moveToNext()) {
            selectedStudentsList.add(cursor.getString(0));
        }
        cursor.close();

        db.close();
        System.out.println("selectedStudentsList: " + selectedStudentsList);
        return selectedStudentsList;
    }
}
