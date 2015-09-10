package com.example.cassendra.puzzlebuzzle;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import database.DBController;
import database.Login;


public class ScoreDisplay extends Activity {

    DBController db;
 // String[] str;
    ArrayList<String> str;

    Login login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_display);

        db = new DBController(this);
        try {
            db.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            //db.test();
            db.getData();
            str = db.getScore();
            assignVariables(str);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.close();
    }

public void assignVariables(ArrayList<String> str){
    TextView name1 = (TextView)findViewById(R.id.txtname1);
    TextView name2 = (TextView)findViewById(R.id.txtname2);
    TextView name3 = (TextView)findViewById(R.id.txtname3);
    TextView name4 = (TextView)findViewById(R.id.txtname4);
    TextView name5 = (TextView)findViewById(R.id.txtname5);
    name1.setText(str.get(0));
    name2.setText(str.get(1));
    name3.setText(str.get(2));
    name4.setText(str.get(3));
    name5.setText(str.get(4));
}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_score_display, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
