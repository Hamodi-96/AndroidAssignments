package com.example.androidassignments;

import android.content.DialogInterface;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class TestToolbar extends AppCompatActivity {
    EditText text_msg;
    View v;
    String text_snackBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        text_snackBar="you selected item 1";
        setContentView(R.layout.activity_test_toolbar);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "ToolBar is checked", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    public boolean onCreateOptionsMenu (Menu m){
        getMenuInflater().inflate(R.menu.toolbar_menu, m );
        return true;


    }
    public boolean onOptionsItemSelected(MenuItem mi){
        switch(mi.getItemId()) {
            case R.id.action_one:
                Log.d("Toolbar", text_snackBar);
                Snackbar.make(getWindow().getDecorView().findViewById(R.id.toolbar), text_snackBar, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                break;
            case R.id.action_two:
                Log.d("Toolbar", "action_two Selected");
                AlertDialog.Builder builder = new AlertDialog.Builder(TestToolbar.this);
                builder.setTitle(R.string.alert);
// Add the buttons
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK button
                        finish();
                    }
                });
                builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
// Create the AlertDialog
                AlertDialog dialog = builder.create();
                dialog.show();



                //Start an activity…
                break;
            case R.id.action_three:
                Log.d("Toolbar", "action_three Selected");
                AlertDialog.Builder build = new AlertDialog.Builder(TestToolbar.this);
                build.setTitle("set new text");
                LayoutInflater inflater = this.getLayoutInflater();
                v = inflater.inflate(R.layout.custom_dialoug, null);
                build.setView(v);
                build.setPositiveButton(R.string.text, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK button
                        text_msg= v.findViewById(R.id.msgInput);
                        text_snackBar = text_msg.getText().toString();
                    }
                });
                build.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
// Create the AlertDialog
                AlertDialog customDialog = build.create();
                customDialog.show();
                break;
            default:
                Log.d("Toolbar", "about item selected");

                Toast toast = Toast.makeText(this, " Version 1.0 by Ahmad Hussein ", Toast.LENGTH_LONG);
                toast.show();
        }

        return false;
    }
}