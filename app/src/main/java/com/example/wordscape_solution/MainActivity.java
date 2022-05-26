package com.example.wordscape_solution;

import static android.graphics.Color.rgb;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity{

    EditText editText;
    ListView listView;
    ImageButton imageButton;
    Button button;
    ArrayList<String> arrayList = new ArrayList<String>();
    ArrayAdapter<String> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        listView = findViewById(R.id.listview1);
        button = findViewById(R.id.button);
        imageButton = findViewById(R.id.search);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                try {
                    fill();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                try {
                    fill();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void fill() throws IOException {

//        arrayList.add("a");
        BufferedReader br = new BufferedReader(
                new InputStreamReader(getAssets().open("words.txt")));
        Finder finder = new Finder();
        String strings = editText.getText().toString();
        arrayList = finder.caller(strings.toLowerCase(), br);
        for(String s : arrayList){
            Log.i("Logss","The word is " + s);
        }
        arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(arrayAdapter);
        Random rand = new Random();
        int rand_int1 = rand.nextInt(255);
        int rand_int2 = rand.nextInt(255);
        int rand_int3 = rand.nextInt(255);
        listView.setBackgroundColor(rgb(rand_int1,rand_int2,rand_int3));

    }

}