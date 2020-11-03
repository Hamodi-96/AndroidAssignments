package com.example.androidassignments;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    protected static final String ACTIVITY_NAME = "MainActivity";
    Button chatBtn;
    Button testToolBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(ACTIVITY_NAME, "In onCreate()");
        Button change=findViewById(R.id.mainButton);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListItemsActivity.class);
                startActivityForResult(intent,10);
            }
        });
        testToolBar=findViewById(R.id.test_toolBar);
        testToolBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(MainActivity.this, TestToolbar.class);
                startActivity(intent2);

            }
        });
    }
    public void start_chat_click(View v){
        Log.i(ACTIVITY_NAME, "User clicked Start Chat");
        Intent intent = new Intent(MainActivity.this,ChatWindow.class);
        startActivity(intent);


    }
    protected void onActivityResult(int requestCode, int responseCode, Intent data) {


        super.onActivityResult(requestCode, responseCode, data);
        if (requestCode==10){
            Log.i(ACTIVITY_NAME,"Hello");
        }
        if (requestCode== Activity.RESULT_OK){
            String str=data.getStringExtra("Ahmad");
            Toast toast = Toast.makeText(this, " ListItemsActivity passed: " + str +" My information to share", Toast.LENGTH_LONG);
            toast.show();
        }

    }
    protected void onResume(){
        super.onResume();
        Log.i(ACTIVITY_NAME, "In onResume()");

    }
    protected void onStart(){
        super.onStart();
        Log.i(ACTIVITY_NAME, "In onStart()");
    }
    protected void onPause(){
        super.onPause();
        Log.i(ACTIVITY_NAME, "In onPause()");
    }
    protected void onStop(){
        super.onStop();
        Log.i(ACTIVITY_NAME, "In onStop()");
    }
    protected void onDestroy(){
        super.onDestroy();
        Log.i(ACTIVITY_NAME, "In onDestroy()");
    }
}