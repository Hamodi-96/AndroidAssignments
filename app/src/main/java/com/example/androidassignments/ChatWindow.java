package com.example.androidassignments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ChatWindow extends AppCompatActivity {
    ListView chatV;
    EditText textEdt;
    Button sendButton;
    ArrayList<String> strArray;
    ChatAdapter messageAdapter;
    SQLiteDatabase database;
    ChatDatabaseHelper helper;
    FrameLayout frame_layout;
    Cursor cur;
    boolean flag=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);
        chatV = findViewById(R.id.chatView);
        textEdt = findViewById(R.id.text1);
        sendButton = findViewById(R.id.sendbtn);
        strArray = new ArrayList<String>();
        chatV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int j, long l) {
                String message = messageAdapter.getItem(j);
                Bundle bundle = new Bundle();
                bundle.putString(ChatDatabaseHelper.KEY_MESSAGE, message);
                bundle.putLong(ChatDatabaseHelper.KEY_ID,messageAdapter.getItemId(j));

                if(flag){
                    MessageFragment frag = new MessageFragment();
                    FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
                    frag.setArguments(bundle);
                    trans.replace(R.id.frame,frag);
                    trans.commit();
                } else{
                    Intent intent = new Intent(ChatWindow.this,MessageDetails.class);
                    intent.putExtra("Bundle",bundle);
                    startActivityForResult(intent,2);
                }
            }
        });


        frame_layout = findViewById(R.id.frame);
        if(frame_layout != null){
            flag = true;
        }else{
            flag=false;
        }

        helper = new ChatDatabaseHelper(this);
        database = helper.getWritableDatabase();

        cur = database.rawQuery("SELECT messages FROM Table_Message", null);
        cur.moveToFirst();
        while (!cur.isAfterLast()){
            Log.i("ChatWindow", "SQL MESSAGE: " + cur.getString(cur.getColumnIndex(ChatDatabaseHelper.KEY_MESSAGE)));
        strArray.add(cur.getString(cur.getColumnIndex(ChatDatabaseHelper.KEY_MESSAGE)));
        cur.moveToNext();

        }
        Log.i("ChatWindow" , "Cursor’s  column count =" + cur.getColumnCount() );
        for (int j=0;j<cur.getColumnCount();j++){
            Log.i("ChatWindow","table name: "+cur.getColumnName(j));

        }
        cur.close();

        messageAdapter =new ChatAdapter( this );
        chatV.setAdapter (messageAdapter);


        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues sql=new ContentValues();
                String value = textEdt.getText().toString();
                sql.put(ChatDatabaseHelper.KEY_MESSAGE,value);
                database.insert(ChatDatabaseHelper.TABLE_NAME,null,sql);

                strArray.add(value);
                messageAdapter.notifyDataSetChanged();
                textEdt.setText("");
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        database.close();
    }
    private class ChatAdapter extends ArrayAdapter<String> {
        public long getItemId(int position){
            long num_id;
            cur  = database.rawQuery("SELECT *  FROM Table_Message", null);
            cur.moveToPosition(position);
            num_id = cur.getLong(cur.getColumnIndex(ChatDatabaseHelper.KEY_ID));
            return num_id;

        }
        public ChatAdapter(Context ctx) {

            super(ctx, 0);
        }
        public int getCount(){
            return strArray.size();
        }
        public String getItem(int position){
            return strArray.get(position);
        }
        public View getView(int position , View convertView, ViewGroup parent){
            LayoutInflater inflater = ChatWindow.this.getLayoutInflater();
            View result = null ;
            if(position%2 == 0)
                result = inflater.inflate(R.layout.chat_row_incoming, null);
            else
                result = inflater.inflate(R.layout.chat_row_outgoing, null);

            TextView message = (TextView)result.findViewById(R.id.textMsg);
            message.setText(   getItem(position)  ); // get the string at position
            return result;

        }
    }

}
