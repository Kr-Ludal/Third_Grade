package com.example.gugudan201707041;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private static final int MENU_START=Menu.FIRST;

    ListView list;
    ArrayList<String> arrlist=new ArrayList<>();
    ArrayAdapter aradp=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list=(ListView)findViewById(R.id.list);


}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(menu.NONE,MENU_START,Menu.NONE,"START");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        startActivityForResult(new Intent(this,GameActivity.class),0);



        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        arrlist.add(data.getStringExtra("point"));
        aradp = new ArrayAdapter(this, android.R.layout.simple_list_item_1,arrlist);
        aradp.notifyDataSetChanged();
        list.setAdapter(aradp);
        super.onActivityResult(requestCode, resultCode, data);
    }


}