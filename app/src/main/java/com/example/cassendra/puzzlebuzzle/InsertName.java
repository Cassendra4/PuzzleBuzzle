package com.example.cassendra.puzzlebuzzle;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.SQLException;

import database.DBController;
import database.Login;


public class InsertName extends Activity {

    Button menu;
    Button play;
    int userID;
    Login login;
    private static final String TAG = "myApp";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        login = new Login();
        setContentView(R.layout.activity_insert_name);
        menu = (Button) findViewById(R.id.button3);
        play = (Button) findViewById(R.id.button);
        menu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                try {
                    userID=checkUserName();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                Intent i = new Intent(InsertName.this,MainMenu.class);
                startActivity(i);
            }
        });
        play.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent i = new Intent(InsertName.this,PlayGame.class);
                startActivity(i);
            }});

            }

    public void startButton(View v){
        if(v.getId()==R.id.button3){
            Intent i = new Intent(InsertName.this,MainMenu.class);
            startActivity(i);
        }
        else if(v.getId()==R.id.button){
            Intent i = new Intent(InsertName.this,PlayGame.class);
            startActivity(i);
        }
    }
    public String getName(){
        EditText mname;
        String userName=null;
        mname = (EditText)findViewById(R.id.Name);
        userName= mname.getText().toString();
        return userName;
    }
    public int checkUserName()throws SQLException{
        int userId=0;
        DBController dbController = new DBController(this);
        login.setUsername(getName());
        if (!dbController.IsUserNameExist(login)) {
            dbController.setUser(login);
                  }
            userId = dbController.getId(login);
        Log.w(TAG, dbController.getData());
        dbController.close();
        return userId;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_insert_name, menu);
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
