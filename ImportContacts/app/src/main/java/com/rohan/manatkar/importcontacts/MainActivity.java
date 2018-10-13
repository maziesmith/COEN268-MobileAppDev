package com.rohan.manatkar.importcontacts;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private MyAdapter myAdapter;
    private ArrayList<DataClass> dataClassArrayList;
    public static final int RequestPermissionCode = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void LoadContacts(View view) {

        listView = (ListView) findViewById(R.id.list_view);

        dataClassArrayList = new ArrayList<>();

        Cursor phoneData = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null,null,null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME+" ASC");

        while (phoneData.moveToNext()) {
            String name = phoneData.getString(phoneData.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String number = phoneData.getString(phoneData.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

            DataClass dataClass = new DataClass();
            dataClass.setName(name);
            dataClass.setNumber(number);
            dataClassArrayList.add(dataClass);
        }
        phoneData.close();

        myAdapter = new MyAdapter(this, dataClassArrayList);
        listView.setAdapter(myAdapter);
    }
}
