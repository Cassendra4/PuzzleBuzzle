package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import org.json.JSONObject;
import org.json.JSONException;
import android.util.Log;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Cassendra on 8/9/2015.
 */
public class UserDataDBController {
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "GameDB";

    private DbHelper databasehelp;
    private final Context context;
    private SQLiteDatabase database;
    private static final String TAG = "myApp";

    //  table name
    private static final String TABLE = "Userdata";

    // Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_TIME = "time";
    private static final String KEY_PINCH= "noPinch";
    private static final String KEY_TRANSLATE= "noTranslate";



    public static class DbHelper extends SQLiteOpenHelper {


           public DbHelper(Context context) {
               super(context, DATABASE_NAME, null, DATABASE_VERSION);
           }

           @Override
           public void onCreate(SQLiteDatabase db) {
               String query;
               query = "CREATE TABLE " + TABLE + " ("
                       + KEY_ID + " INTEGER PRIMARY KEY, " + KEY_TIME + " TEXT NOT NULL,"
                       + KEY_TRANSLATE + " INTEGER ,"
                       + KEY_PINCH + " INTEGER " + ")";
               db.execSQL(query);
           }

           @Override
           public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
               String query;
               query = "DROP TABLE IF EXISTS Userdata";
               db.execSQL(query);
               onCreate(db);
           }
       }

    public UserDataDBController(Context context) {
        this.context = context;
    }
    public UserDataDBController open() throws SQLException {

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

    public String getData() {
        String[] columns = { KEY_ID, KEY_TIME, KEY_PINCH,KEY_TRANSLATE };
        Cursor read = database.query(TABLE,columns , null, null, null,
                null, null);
        Cursor c = database.rawQuery(
                "SELECT name FROM sqlite_master WHERE type='table'", null);
        String qury = "";

        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {

            qury += c.getString(c.getColumnIndex("name"));
        }
        int iRow=read.getColumnIndex(KEY_ID);
        int iTime=read.getColumnIndex(KEY_TIME);
        int iPinch=read.getColumnIndex(KEY_PINCH);
        int iTranslate=read.getColumnIndex(KEY_TRANSLATE);
        for (read.moveToFirst(); !read.isAfterLast(); read.moveToNext()) {

            qury += read.getString(iRow) + " " + read.getString(iTime) + " "
                    + read.getString(iPinch) + read.getString(iPinch) + " "
                    + read.getString(iTranslate) + " "
                    + "\n";
        }
        return qury;

    }
    public void test() throws SQLException {
        addData("20",10,10);
        addData("36",8,14);
        addData("25",6,54);
    }
    public void addData(String time,int pinch,int translate) throws SQLException{
        // Open database connection
        UserDataDBController userDataDBController = open();
        // Define values for each field
        ContentValues values = new ContentValues();
        values.put(KEY_TIME, time);
        values.put(KEY_PINCH, pinch);
        values.put(KEY_TRANSLATE, translate);
        // Insert Row and get generated id
        long rowId = userDataDBController.database.insertOrThrow(TABLE, null, values);
        // Closing database connection
    }

    /**
     * Get SQLite records that are yet to be Synced
     *
     */
    /*public int dbSyncCount() throws SQLException {
        int count = 0;
        String selectQuery = "SELECT  * FROM Userdata";
        UserDataDBController db  = open();
        Cursor cursor = db.database.rawQuery(selectQuery, null);
        count = cursor.getCount();
             return count;
    }*/


    /*public ArrayList<String> composeJSONfromSQLite() throws SQLException {
        ArrayList<String> wordList = new ArrayList<String>();
        String[] columns = {  KEY_TIME, KEY_PINCH,KEY_TRANSLATE };
        Cursor read = database.query(TABLE,columns , null, null, null,
                null, null);
        Cursor c = database.rawQuery(
                "SELECT name FROM sqlite_master WHERE type='table'", null);
        String qury = "";

        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {

            qury += c.getString(c.getColumnIndex("name"));
        }

        int iTime=read.getColumnIndex(KEY_TIME);
        int iPinch=read.getColumnIndex(KEY_PINCH);
        int iTranslate=read.getColumnIndex(KEY_TRANSLATE);
        for (read.moveToFirst(); !read.isAfterLast(); read.moveToNext()) {

            qury +=  read.getString(iTime) + " "
                    + read.getString(iPinch) + read.getString(iPinch) + " "
                    + read.getString(iTranslate) + " "
                    + "\n";
            wordList.add(qury);
        }

             database.close();
        return wordList;
        //Gson gson = new GsonBuilder().create();
        //Use GSON to serialize Array List to JSON
        //return gson.toJson(wordList);
    }*/
    public ArrayList<String> composeJSONfromSQLite() throws SQLException {

        //String[] str = new String[5];
        ArrayList<String> str = new ArrayList<>();

        String[] colm = { KEY_TIME, KEY_PINCH,KEY_TRANSLATE };
        Cursor read = database.query(TABLE, colm, null, null, null, null, null);
        int iTime=read.getColumnIndex(KEY_TIME);
        int iPinch=read.getColumnIndex(KEY_PINCH);
        int iTranslate=read.getColumnIndex(KEY_TRANSLATE);
        if (read == null)
            return null;

        int i =  0 ;


        for (read.moveToFirst(); i <  read.getCount()&& !read.isAfterLast(); read.moveToNext()) {
            str.add(read.getString(iTime) + " "
                    + read.getString(iPinch) + read.getString(iPinch) + " "
                    + read.getString(iTranslate));
            i++;
        }
        if (str.get(0).equals(""))
            str.add(0, "No data to show");
        return str;

    }

}
