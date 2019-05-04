package com.rohan.manatkar.webviewapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> cities;
    private ArrayAdapter<String> cityAdapter;
    private ListView lvCities;
    public final static String EXTRA_MESSAGE = "com.rohan.manatkar.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvCities = (ListView) findViewById(R.id.list_of_cities);
        cities = new ArrayList<String>();
        cityAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, cities);
        lvCities.setAdapter(cityAdapter);
        cities.add("San Francisco");
        cities.add("Santa Clara");
        cities.add("Milpitas");
        cities.add("Fremont");
        cities.add("Cupertino");
        cities.add("Palo Alto");
        cities.add("San Mateo");
        cities.add("San Diego");
        cities.add("Santa Cruz");
        cities.add("Berkeley");
        cities.add("Los Angeles");
        cities.add("Sacramento");
        cities.add("Fresno");
        cities.add("Long Beach");
        cities.add("Santa Barbara");
        cities.add("Irvine");
        cities.add("Pasadena");
        cities.add("Mountain View");
        cities.add("Sunnyvale");

        lvCities.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, WebActivity.class);
                String city = (String) lvCities.getItemAtPosition(i);
                intent.putExtra(EXTRA_MESSAGE, city);
                startActivity(intent);
            }
        });
    }
}
