package database;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

/**
 * Created by Cassendra on 9/6/2015.
 */
public class UserData {
    //  table name
    public static final String TABLE = "User";

    // Table Columns names
    public static final String KEY_ID = "id";
    private static final String KEY_TIME = "time";
    private static final String KEY_PINCH= "noPinch";
    private static final String KEY_TRANSLATE= "noTranslate";

    // properties to keep data
    private int ID;
    private TimeUnit time;
    private int noPinch;
    private int noTranslate;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public TimeUnit getTime() {
        return time;
    }

    public void setTime(TimeUnit time) {
        this.time = time;
    }

    public int getNoPinch() {
        return noPinch;
    }

    public void setNoPinch(int noPinch) {
        this.noPinch = noPinch;
    }

    public int getNoTranslate() {
        return noTranslate;
    }

    public void setNoTranslate(int noTranslate) {
        this.noTranslate = noTranslate;
    }
}
