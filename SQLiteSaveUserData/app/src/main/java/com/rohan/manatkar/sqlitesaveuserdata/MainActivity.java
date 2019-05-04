package com.rohan.manatkar.sqlitesaveuserdata;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText editName;
    EditText editEmail;
    EditText editTVShow;
    EditText editID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);

        editName = (EditText) findViewById(R.id.name);
        editEmail = (EditText) findViewById(R.id.email);
        editTVShow = (EditText) findViewById(R.id.tv_show);
        editID = (EditText) findViewById(R.id.data_id);
    }

    public void AddData(View view) {
        boolean dataAdded = myDb.insertData(editName.getText().toString(), editEmail.getText().toString(), editTVShow.getText().toString());
        if(dataAdded == true)
            Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(MainActivity.this, "Data not Inserted", Toast.LENGTH_LONG).show();

    }

    public void ViewData(View view) {
        Cursor result = myDb.showData();
        if(result.getCount() == 0) {
            showMessage("Error", "Nothing found!");
            return;
        }

        StringBuffer buffer = new StringBuffer();
        while (result.moveToNext()) {
            buffer.append("ID :" + result.getString(0) + "\n");
            buffer.append("Name :" + result.getString(1) + "\n");
            buffer.append("Email :" + result.getString(2) + "\n");
            buffer.append("Favorite TV Show :" + result.getString(3) + "\n\n");
        }

        showMessage("All Stored Data", buffer.toString());
    }

    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void UpdateData(View view) {
        String checkID = editID.getText().toString();
        if(checkID == null) {
            Toast.makeText(MainActivity.this, "Please Enter ID", Toast.LENGTH_LONG).show();
            return;
        }
        else {
            boolean dataUpdated = myDb.updateData(editID.getText().toString(), editName.getText().toString(), editEmail.getText().toString(), editTVShow.getText().toString());
            if(dataUpdated == true)
                Toast.makeText(MainActivity.this, "Data Updated", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(MainActivity.this, "Data not Updated", Toast.LENGTH_LONG).show();
        }
    }

    public void DeleteData(View view) {
        String checkID = editID.getText().toString();
        if(checkID == "") {
            Toast.makeText(MainActivity.this, "Please Enter ID", Toast.LENGTH_LONG).show();
            return;
        }
        else {
            int dataDeleted = myDb.deleteData(editID.getText().toString());
            if(dataDeleted > 0)
                Toast.makeText(MainActivity.this, "Data Deleted", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(MainActivity.this, "Data not Deleted", Toast.LENGTH_LONG).show();
        }
    }
}
