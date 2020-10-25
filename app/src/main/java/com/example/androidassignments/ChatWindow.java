package com.example.androidassignments;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ChatWindow extends AppCompatActivity {
    ListView chatV;
    EditText textEdt;
    Button sendButton;
    ArrayList<String> strArray;
    ChatAdapter messageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);
        chatV=findViewById(R.id.chatView);
        textEdt=findViewById(R.id.text1);
        sendButton=findViewById(R.id.sendbtn);
        strArray=new ArrayList<String>();
        messageAdapter =new ChatAdapter( this );
        chatV.setAdapter (messageAdapter);


        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = textEdt.getText().toString();
                strArray.add(value);
                messageAdapter.notifyDataSetChanged();
                textEdt.setText("");




            }

        });

    }
    private class ChatAdapter extends ArrayAdapter<String> {
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
