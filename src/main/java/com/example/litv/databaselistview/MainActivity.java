package com.example.litv.databaselistview;

import android.database.sqlite.SQLiteDatabase;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //widget
    Button buttonajout=null;
    Button buttonviderlist=null;
    ListView listViewCompat=null;
    EditText editText=null;

    //base de données
    MyDataBAse db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonajout =(Button)findViewById(R.id.button);
        buttonviderlist=(Button)findViewById(R.id.button2);
        editText=(EditText)findViewById(R.id.editText);
        listViewCompat=(ListView)findViewById(R.id.listview);
        db=new MyDataBAse(getApplicationContext());

        buttonajout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.putdata(editText.getText().toString());
                editText.getText().clear();
                Toast.makeText(getApplicationContext(), "Prenom ajouté", Toast.LENGTH_LONG).show();
                synchronized (listViewCompat){listViewCompat.notifyAll();

                    setadapter();
                }


            }
        });

        buttonviderlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.clean_table();
                db.readdata();
                setadapter();
            }
        });
        setadapter();

    }
    public void setadapter(){
        ArrayList<String> arrayList=db.readdata();
        final ArrayAdapter<String> arrayAdapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList);
        listViewCompat.setAdapter(arrayAdapter);
        arrayAdapter.notifyDataSetChanged();
    }
    /* public void setadapter2(){
        ArrayList<String> arrayList=db.readdata();
        final ArrayAdapter<String> arrayAdapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList);
        listViewCompat.setAdapter(arrayAdapter);
        arrayAdapter.notifyDataSetChanged();
    }*/

}
