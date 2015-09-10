package database;

/**
 * Created by Cassendra on 9/1/2015.
 */
public class Login {
     //  table name
    public static final String TABLE = "user";

    // Table Columns names
    public static final String KEY_ID = "id";

    public Login() {

    }

    public static final String KEY_NAME = "username";
    public static final String KEY_SCORE = "score";

     // properties to keep data
     private int ID;
    private String username;
    private int score;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public void setUserScore(String username,int score){
        this.username = username;
        this.score = score;
    }
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
