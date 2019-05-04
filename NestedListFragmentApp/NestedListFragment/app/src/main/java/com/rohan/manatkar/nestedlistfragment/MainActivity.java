package com.rohan.manatkar.nestedlistfragment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> Animals;
    private ArrayAdapter<String> animalAdapter;
    private ListView lvAnimals;
    public final static String EXTRA_MESSAGE = "com.rohan.manatkar.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvAnimals = (ListView) findViewById(R.id.list_of_animals);
        Animals = new ArrayList<String>();
        animalAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Animals);
        lvAnimals.setAdapter(animalAdapter);
        Animals.add("Dogs");
        Animals.add("Cats");
        Animals.add("Birds");
        Animals.add("Fish");
        Animals.add("Reptiles");

        lvAnimals.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                //String animal = (String) lvAnimals.getItemAtPosition(i);
                intent.putExtra(EXTRA_MESSAGE, i);
                startActivity(intent);
            }
        });
    }
}
