package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cassendra on 8/9/2015.
 */
public class DBController {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 2;

    // Database Name
    private static final String DATABASE_NAME = "GameDB";

    //  table name
    private static final String TABLE = "user";

    // Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "username";
    private static final String KEY_SCORE = "score";

    private DbHelper databasehelp;
    private final Context context;
    List<String> userList = new ArrayList<>();
    private SQLiteDatabase database;
    private static final String TAG = "myApp";

    public static class DbHelper extends SQLiteOpenHelper {


        public DbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            String query;
            query = "CREATE TABLE " + TABLE + " ("
                    + KEY_ID + " INTEGER PRIMARY KEY, " + KEY_NAME + " TEXT NOT NULL,"
                    + KEY_SCORE + " INTEGER " + ")";
            db.execSQL(query);
            Log.w(TAG,"Created");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            String query;
            query = "DROP TABLE IF EXISTS User";
            db.execSQL(query);
            onCreate(db);
        }
    }
        public DBController(Context c) {

            context = c;

        }

        public DBController open() throws SQLException {

            databasehelp = new DbHelper(context);
            database = databasehelp.getWritableDatabase();
            return this;

        }

        public void close() {

            databasehelp.close();
        }

        public void removedb() {

            databasehelp.onUpgrade(database, DATABASE_VERSION, DATABASE_VERSION);
        }

        public boolean IsUserNameExist(Login loging) throws SQLException{
            DBController db = open();
            String query = "SELECT id FROM user WHERE username = '" + loging.getUsername() + "'";
            Cursor cursor = (db.database).rawQuery(query, null);
            if (cursor.getCount() > 0)
                return true;
            else
                return false;

        }
    public void test() throws SQLException {
        Login login = new Login();
        login.setUserScore ("Hasa",100);
        addScoreNUsername(login);
        login.setUserScore("sarath", 20);
        addScoreNUsername(login);
        login.setUserScore("Manisha", 200);
        addScoreNUsername(login);
        login.setUserScore("wije", 400);
        addScoreNUsername(login);
        login.setUserScore("KRV", 500);
        addScoreNUsername(login);
    }
    public String getData() {
        String[] columns = { KEY_ID, KEY_NAME, KEY_SCORE };
        Cursor read = database.query(TABLE,columns , null, null, null,
                null, null);
        Cursor c = database.rawQuery(
                "SELECT name FROM sqlite_master WHERE type='table'", null);
        String qury = "";

        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {

            qury += c.getString(c.getColumnIndex("name"));
        }
        int iRow=read.getColumnIndex(KEY_ID);
        int iName=read.getColumnIndex(KEY_NAME);
        int iScore=read.getColumnIndex(KEY_SCORE);
        for (read.moveToFirst(); !read.isAfterLast(); read.moveToNext()) {

            qury += read.getString(iRow) + " " + read.getString(iName) + " "
                    + read.getString(iScore) + "\n";
        }
        return qury;

    }

        public void setUser(Login login) throws SQLException{
            DBController dbController = open();
            ContentValues values = new ContentValues();
            values.put(Login.KEY_NAME, login.getUsername());

            // Inserting Row
            dbController.database.insert(Login.TABLE, null, values);

        }

        public int getId(Login login) throws SQLException{
            int id = 0;
            DBController dbController = open();
            String query = "SELECT id FROM user WHERE username = '" + login.getUsername() + "'";
            Cursor cursor = dbController.database.rawQuery(query, null);
            if (cursor.getCount() == 1) {
                cursor.moveToFirst();
                id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));
                return id;
            } else
                return id;
        }

        public void addScoreNUsername(Login login) throws SQLException{
            // Open database connection
            DBController dbController = open();
            // Define values for each field
            ContentValues values = new ContentValues();
            values.put(Login.KEY_NAME, login.getUsername());
            values.put(Login.KEY_SCORE, login.getScore());
            // Insert Row and get generated id
            long rowId = dbController.database.insertOrThrow(Login.TABLE, null, values);
            // Closing database connection
                   }



    public ArrayList<String> getScore() throws SQLException {

        //String[] str = new String[5];
        ArrayList<String> str = new ArrayList<>();

        String[] colm = { KEY_NAME, KEY_SCORE };
        Cursor read = database.query(TABLE, colm, null, null, null, null, KEY_SCORE + " desc");
        if (read == null)
            return null;
        int iName = read.getColumnIndex(KEY_NAME);
        int iScore = read.getColumnIndex(KEY_SCORE);
               int i =  0 ;


        for (read.moveToFirst(); i <  read.getCount()&& !read.isAfterLast(); read.moveToNext()) {
            str.add(read.getString(iName) + " " + read.getString(iScore));
            i++;
        }
        if (str.get(0).equals(""))
            str.add(0, "No data to show");
        return str;

    }

    public void addName(String name) throws SQLException{
            // Open database connection
            DBController dbController = open();
            // Define values for each field
            ContentValues values = new ContentValues();
            values.put(KEY_NAME, name);
            // Insert Row and get generated id
            long rowId = dbController.database.insertOrThrow(TABLE, null, values);
            // Closing database connection
                    }

        public void addScore(int score) throws SQLException{
            // Open database connection
            DBController dbController = open();
            // Define values for each field
            ContentValues values = new ContentValues();
            values.put(KEY_SCORE, score);
            // Insert Row and get generated id
            long rowId = dbController.database.insertOrThrow(TABLE, null, values);
            // Closing database connection
                   }

    }

