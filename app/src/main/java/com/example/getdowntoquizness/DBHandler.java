package com.example.getdowntoquizness;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.HashMap;

public class DBHandler extends SQLiteOpenHelper {
//-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
    // DATABASE VARIABLES //

    // Database Information
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "DBQuizTaker.db";

    // Table Names
    public static final String USER_TABLE_NAME = "users";
    public static final String QUIZ_TABLE_NAME = "quizzes";
    public static final String QUESTION_TABLE_NAME = "questions";
    public static final String STUDENT_QUIZ_STATS_TABLE_NAME = "student_quiz_stats";
    public static final String STUDENT_QUESTION_STATS_TABLE_NAME = "student_question_stats";

//-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
    // TABLE VARIABLES //

    // User Table Columns
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

    // Quiz Table Columns
    // TODO: do I need any more columns?
    public static final String QUIZ_COLUMN_ID = "quiz_id";
    public static final String QUIZ_COLUMN_NAME = "quiz_name";
    public static final String QUIZ_COLUMN_TOPIC = "quiz_topic";
    public static final String QUIZ_COLUMN_TIME_LIMIT = "quiz_time_limit";

    // Question Table Columns
    public static final String QUESTION_COLUMN_ID = "question_id";
    public static final String QUESTION_COLUMN_QUESTION_STATEMENT = "question_statement";
    public static final String QUESTION_COLUMN_ANSWER_CHOICE_1 = "question_answer_choice_1";
    public static final String QUESTION_COLUMN_ANSWER_CHOICE_2 = "question_answer_choice_2";
    public static final String QUESTION_COLUMN_ANSWER_CHOICE_3 = "question_answer_choice_3";
    public static final String QUESTION_COLUMN_ANSWER_CHOICE_4 = "question_answer_choice_4";
    public static final String QUESTION_COLUMN_CORRECT_ANSWER = "question_correct_answer";
    public static final String QUESTION_COLUMN_QUIZ_ID = "question_quiz_id";

    // Student-Quiz Stats Table Columns
    public static final String STUDENT_QUIZ_STATS_COLUMN_ID = "student_quiz_stats_id";
    public static final String STUDENT_QUIZ_STATS_COLUMN_IS_ASSIGNED = "student_quiz_stats_is_assigned";
    public static final String STUDENT_QUIZ_STATS_COLUMN_GRADE = "student_quiz_stats_grade";
    public static final String STUDENT_QUIZ_STATS_COLUMN_USERNAME = "student_quiz_stats_username";
    public static final String STUDENT_QUIZ_STATS_COLUMN_QUIZ_ID = "student_quiz_stats_quiz_id";

    // Student-Question Stats Table Columns
    public static final String STUDENT_QUESTION_STATS_COLUMN_ID = "student_question_stats_id";
    public static final String STUDENT_QUESTION_STATS_COLUMN_QUESTION_ID = "student_question_stats_question_id";
    public static final String STUDENT_QUESTION_STATS_COLUMN_USERNAME = "student_question_stats_username";
    public static final String STUDENT_QUESTION_STATS_COLUMN_STUDENT_ANSWER = "student_question_stats_student_answer";

//-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
    // TABLE CREATE STATEMENTS //

    // Create User Table Query
    private static final String CREATE_TABLE_USERS = "CREATE TABLE " + USER_TABLE_NAME + " ( " +
            USER_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            USER_COLUMN_NAME + " TEXT, " +
            USER_COLUMN_USERNAME + " TEXT, " +
            USER_COLUMN_PASSWORD + " TEXT, " +
            USER_COLUMN_RETYPE_PASSWORD + " TEXT, " +
            USER_COLUMN_EMAIL + " TEXT, " +
            USER_COLUMN_IS_ADMIN + " BOOLEAN, " +
            USER_COLUMN_IS_SELECTED + " BOOLEAN )"; //TODO: add phone, quiz, and grade cols

    // Create Quiz Table Query
    private static final String CREATE_TABLE_QUIZZES = "CREATE TABLE " + QUIZ_TABLE_NAME + " ( " +
            QUIZ_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            QUIZ_COLUMN_NAME + " TEXT, " +
            QUIZ_COLUMN_TOPIC + " INTEGER, " +
            QUIZ_COLUMN_TIME_LIMIT + " INTEGER )";

    // Create Question Table Query
    private static final String CREATE_TABLE_QUESTIONS = "CREATE TABLE " + QUESTION_TABLE_NAME + " ( " +
            QUESTION_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            QUESTION_COLUMN_QUESTION_STATEMENT + " TEXT, " +
            QUESTION_COLUMN_ANSWER_CHOICE_1 + " TEXT, " +
            QUESTION_COLUMN_ANSWER_CHOICE_2 + " TEXT, " +
            QUESTION_COLUMN_ANSWER_CHOICE_3 + " TEXT, " +
            QUESTION_COLUMN_ANSWER_CHOICE_4 + " TEXT, " +
            QUESTION_COLUMN_CORRECT_ANSWER + " TEXT, " +
            QUESTION_COLUMN_QUIZ_ID + " INTEGER )";

    // Create Student Quiz Stats Table Query
    private static final String CREATE_TABLE_STUDENT_QUIZ_STATS = "CREATE TABLE " + STUDENT_QUIZ_STATS_TABLE_NAME + " ( " +
            STUDENT_QUIZ_STATS_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            STUDENT_QUIZ_STATS_COLUMN_IS_ASSIGNED + " BOOLEAN, " +
            STUDENT_QUIZ_STATS_COLUMN_GRADE + " REAL, " +
            STUDENT_QUIZ_STATS_COLUMN_USERNAME + " TEXT, " +
            STUDENT_QUIZ_STATS_COLUMN_QUIZ_ID + " INTEGER )";

    // Create Student Question Stats Table Query
    private static final String CREATE_TABLE_STUDENT_QUESTION_STATS = "CREATE TABLE " + STUDENT_QUESTION_STATS_TABLE_NAME + " ( " +
            STUDENT_QUESTION_STATS_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            STUDENT_QUESTION_STATS_COLUMN_QUESTION_ID + " INTEGER, " +
            STUDENT_QUESTION_STATS_COLUMN_USERNAME + " TEXT, " +
            STUDENT_QUESTION_STATS_COLUMN_STUDENT_ANSWER + " TEXT )";

//-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
    // DATABASE METHODS //

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        if (isEmptyUserTable())
            initializeFirstAdmin();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the tables
        db.execSQL(CREATE_TABLE_USERS);
        db.execSQL(CREATE_TABLE_QUIZZES);
        db.execSQL(CREATE_TABLE_QUESTIONS);
        db.execSQL(CREATE_TABLE_STUDENT_QUIZ_STATS);
        db.execSQL(CREATE_TABLE_STUDENT_QUESTION_STATS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // On upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + QUIZ_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + QUESTION_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + STUDENT_QUIZ_STATS_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + STUDENT_QUESTION_STATS_TABLE_NAME);
    }

//-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
    // USER TABLE METHODS //

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

    public boolean isEmptyStudentUserTable() { //aka no students, but could have admins
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + USER_TABLE_NAME + " WHERE " + USER_COLUMN_IS_ADMIN + " = '" + 0 + "'";
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

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> student = new HashMap<>();
                student.put("name", cursor.getString(0));
                student.put("username", cursor.getString(1));
                studentsList.add(student);
            } while (cursor.moveToNext());
            cursor.close();
        }

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

//-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
    // QUIZ TABLE METHODS

    //TODO: delete bc this is just for practice
    // INSERT INTO QUIZ_TABLE_NAME (QUIZ_COLUMN_TOPIC) VALUES ('Science')

    public int getLastQuizID() {
        String query = "SELECT MAX(" + QUIZ_COLUMN_ID + ") AS max_id FROM " + QUIZ_TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        int id = 0;
        if (cursor.moveToFirst())
        {
            do
            {
                id = cursor.getInt(0);
            } while(cursor.moveToNext());
        }
        return id;
    }

    public void addQuizHandler(String name, String topic, int timeLimit, ArrayList<QuizQuestion> questions) {
        int thisQuizID = getLastQuizID() + 1;
        for (QuizQuestion question : questions)
            addQuizQuestionHandler(question,  thisQuizID++);

        ContentValues values = new ContentValues();
        values.put(QUIZ_COLUMN_NAME, name);
        values.put(QUIZ_COLUMN_TOPIC, topic);
        values.put(QUIZ_COLUMN_TIME_LIMIT, timeLimit);

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(QUIZ_TABLE_NAME, null, values);
        db.close();
    }


//-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
    // QUESTION TABLE METHODS

    public void addQuizQuestionHandler(QuizQuestion question, int quizID) {
        //TODO: make this work with more than one correct answer... rn it doesn't because
        // the correct answer column/field only accepts one value
        String questionStatement = question.getQuestionText();
        String answer1 = question.getAnswer1Text();
        String answer2 = question.getAnswer2Text();
        String answer3 = question.getAnswer3Text();
        String answer4 = question.getAnswer4Text();
        ArrayList<Integer> correctAnswerChoices = question.getCorrectAnswerChoices();

        ContentValues values = new ContentValues();

        values.put(QUESTION_COLUMN_QUESTION_STATEMENT, questionStatement);
        values.put(QUESTION_COLUMN_ANSWER_CHOICE_1, answer1);
        values.put(QUESTION_COLUMN_ANSWER_CHOICE_2, answer2);
        values.put(QUESTION_COLUMN_ANSWER_CHOICE_3, answer3);
        values.put(QUESTION_COLUMN_ANSWER_CHOICE_4, answer4);
        values.put(QUESTION_COLUMN_CORRECT_ANSWER, correctAnswerChoices.get(0));

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(QUESTION_TABLE_NAME, null, values);
        db.close();

    }

//-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
    // STUDENT QUIZ STATS TABLE METHODS

    //TODO: delete bc this is just for practice
    // INSERT INTO STUDENT_QUIZ_STATS_TABLE_NAME (STUDENT_QUIZ_STATS_COLUMN_GRADE, STUDENT_QUIZ_STATS_COLUMN_USERNAME,
    // STUDENT_QUIZ_STATS_COLUMN_QUIZ_ID) VALUES ('100', me, 0)

//-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
    // STUDENT QUESTION STATS TABLE METHODS

//-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
    // JOIN TABLE METHODS

    public double getIndividualOverallAverageHandler(String username) {
        double totalGrade = 0;
        String query = "SELECT AVG(" + STUDENT_QUIZ_STATS_COLUMN_GRADE + ") FROM " +
                STUDENT_QUIZ_STATS_TABLE_NAME + " WHERE " + STUDENT_QUIZ_STATS_COLUMN_USERNAME +
                " = '" + username + "' AND " + STUDENT_QUIZ_STATS_COLUMN_GRADE + " IS NOT NULL";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            totalGrade = cursor.getDouble(0);
            cursor.close();
        }

        db.close();
        return totalGrade;
    }

    public double getIndividualAverageForTopicHandler(String username, String topic) {
        double topicGrade = 0;
        String query = "SELECT AVG(s." + STUDENT_QUIZ_STATS_COLUMN_GRADE +
                ") FROM " + STUDENT_QUIZ_STATS_TABLE_NAME + "AS s JOIN " + QUIZ_TABLE_NAME + " AS q ON q." +
                QUIZ_COLUMN_ID + "= s." + STUDENT_QUIZ_STATS_COLUMN_QUIZ_ID + "WHERE s." +
                STUDENT_QUIZ_STATS_COLUMN_USERNAME + " = '" + username + "' AND q." + QUIZ_COLUMN_TOPIC  +
                "= '" + topic + "' AND s." + STUDENT_QUIZ_STATS_COLUMN_GRADE + " IS NOT NULL";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            topicGrade = cursor.getDouble(0);
            cursor.close();
        }

        db.close();
        return topicGrade;

    }

    public double getClassAverageForTopicHandler(String topic) {
        double topicGrade = 0;
        String query = "SELECT AVG(s." + STUDENT_QUIZ_STATS_COLUMN_GRADE + ") FROM " +
                STUDENT_QUIZ_STATS_TABLE_NAME + "AS s JOIN " + QUIZ_TABLE_NAME + " AS q ON q." +
                QUIZ_COLUMN_ID + " = s." + STUDENT_QUIZ_STATS_COLUMN_QUIZ_ID + " WHERE q." +
                QUIZ_COLUMN_TOPIC + " = " + topic + " AND s." + STUDENT_QUIZ_STATS_COLUMN_GRADE + " IS NOT NULL";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            topicGrade = cursor.getDouble(0);
            cursor.close();
        }

        db.close();
        return topicGrade;
    }

    public double getClassAverageForQuizHandler(String quizName) {
        double classAverage = 0;
        String query = "SELECT AVG(s." + STUDENT_QUIZ_STATS_COLUMN_GRADE +") FROM " +
                STUDENT_QUIZ_STATS_TABLE_NAME + " AS s JOIN " + QUIZ_TABLE_NAME + " AS q ON q." +
                QUIZ_COLUMN_ID + " = s." + STUDENT_QUIZ_STATS_COLUMN_QUIZ_ID + " WHERE q." +
                QUIZ_COLUMN_NAME + " = '" + quizName + "' AND s." + STUDENT_QUIZ_STATS_COLUMN_GRADE + " IS NOT NULL";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            classAverage = cursor.getDouble(0);
            cursor.close();
        }

        db.close();
        return classAverage;
    }

    public double getHighestClassScoreForQuizHandler(String quizName) {
        double classAverage = 0;
        String query = "SELECT MAX(s." + STUDENT_QUIZ_STATS_COLUMN_GRADE +") FROM " +
                STUDENT_QUIZ_STATS_TABLE_NAME + " AS s JOIN " + QUIZ_TABLE_NAME + " AS q ON q." +
                QUIZ_COLUMN_ID + " = s." + STUDENT_QUIZ_STATS_COLUMN_QUIZ_ID + " WHERE q." +
                QUIZ_COLUMN_NAME + " = '" + quizName + "' AND s." + STUDENT_QUIZ_STATS_COLUMN_GRADE + " IS NOT NULL";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            classAverage = cursor.getDouble(0);
            cursor.close();
        }

        db.close();
        return classAverage;
    }

    public double getLowestClassScoreForQuizHandler(String quizName) {
        double classAverage = 0;
        String query = "SELECT MIN(s." + STUDENT_QUIZ_STATS_COLUMN_GRADE +") FROM " +
                STUDENT_QUIZ_STATS_TABLE_NAME + " AS s JOIN " + QUIZ_TABLE_NAME + " AS q ON q." +
                QUIZ_COLUMN_ID + " = s." + STUDENT_QUIZ_STATS_COLUMN_QUIZ_ID + " WHERE q." +
                QUIZ_COLUMN_NAME + " = '" + quizName + "' AND s." + STUDENT_QUIZ_STATS_COLUMN_GRADE + " IS NOT NULL";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            classAverage = cursor.getDouble(0);
            cursor.close();
        }

        db.close();
        return classAverage;
    }

}
