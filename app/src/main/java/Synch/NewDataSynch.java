package Synch;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONObject;
//import org.json.JSON;
import org.json.JSONException;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

import com.example.cassendra.puzzlebuzzle.R;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.UserDataDBController;

/**
 * Created by Cassendra on 9/9/2015.
 */
@TargetApi(Build.VERSION_CODES.CUPCAKE)
public class NewDataSynch extends AsyncTask<String, Void, String> {
    JSONObject as = new JSONObject();
Context context;
    JSONParser jsonParser = new JSONParser();
    UserDataDBController db;
    //url to create new product
    private static String url_create_product = "http://http://puzzlebuzzle.esy.es/create_newplayer.php";

    // JSON Node names
    private static final String TAG_SUCCESS = "success";

    public NewDataSynch(Context context) {
        this.context = context;
      db = new UserDataDBController(context);
    }


    @Override
        protected String doInBackground(String... params) {
           List<NameValuePair> param = new ArrayList<NameValuePair>();
            ArrayList<String> str = new ArrayList<>();
        try {
            str = db.composeJSONfromSQLite();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        param.add(new BasicNameValuePair("time",str.get(0) ));
        param.add(new BasicNameValuePair("pinch",str.get(1) ));
        param.add(new BasicNameValuePair("translate",str.get(2) ));

        // getting JSON Object
            // Note that create product url accepts POST method
            JSONObject json = jsonParser.makeHttpRequest(url_create_product,
                    "POST", param);

            // check log cat fro response
            Log.d("Create Response", json.toString());
            return null;
        }

}
