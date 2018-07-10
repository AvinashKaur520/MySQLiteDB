package com.example.mypc.mysqlitedb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText e1,e2,e3,e4;
    Button b1,b2,b3,b4,b5;

    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        e1 = (EditText) findViewById(R.id.editText);
        e2 = (EditText) findViewById(R.id.editText1);
        e3 = (EditText) findViewById(R.id.editText2);
        e4 = (EditText) findViewById(R.id.editText3);
        b1 = (Button) findViewById(R.id.button);
        b2 = (Button) findViewById(R.id.button2);
        b3 = (Button) findViewById(R.id.button3);
        b4 = (Button) findViewById(R.id.button4);
        b5 = (Button) findViewById(R.id.button5);

        db = new DatabaseHandler(this);

        b1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int id= Integer.parseInt(e1.getText().toString());      //type Conversion
                String name = e2.getText().toString();
                String disp = e3.getText().toString();
                String city = e4.getText().toString();

                CriminalRecord record= new CriminalRecord();             //object created of CriminalRecord
                record.setId(id);
                record.setName(name);
                record.setDisp(disp);
                record.setCity(city);

                db.addCriminalRecord(record);

                Toast.makeText(MainActivity.this,"Data Saved",Toast.LENGTH_SHORT).show();

                e1.setText("");
                e2.setText("");
                e3.setText("");
                e4.setText("");
            }
        });


        b2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int id = Integer.parseInt(e1.getText().toString());

                CriminalRecord record = db.getSingleRecord(id);

                Toast.makeText(MainActivity.this, "Id:- " +record.getId() + "\nName:- " +record.getName() + "\nDISP:- " +record.getDisp() + "\nCity:- " +record.getCity(), Toast.LENGTH_SHORT).show();


            }
        });

        b3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                List<CriminalRecord> list = db.getAllRecord();

                for(CriminalRecord record:list)
                {
                    Toast.makeText(MainActivity.this,"ID:- "+record.getId()
                            +"\nName:- "+record.getName()
                            +"\nDisp:- "+record.getDisp()
                            +"\nCity:- "+record.getCity(),Toast.LENGTH_SHORT).show();
                }


            }
        });


        b4.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
            int id = Integer.parseInt(e1.getText().toString());

                db.deleteCriminalRecord(id);

                Toast.makeText(MainActivity.this,"Record Deleted",Toast.LENGTH_SHORT).show();

                e1.setText("");
            }
        });

        b5.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int id= Integer.parseInt(e1.getText().toString());      //type Conversion
                String name = e2.getText().toString();
                String disp = e3.getText().toString();
                String city = e4.getText().toString();

                CriminalRecord record= new CriminalRecord();             //object created of CriminalRecord
                record.setId(id);
                record.setName(name);
                record.setDisp(disp);
                record.setCity(city);

                db.UpdateCriminalRecord(record);

                Toast.makeText(MainActivity.this,"Data Saved",Toast.LENGTH_SHORT).show();

                e1.setText("");
                e2.setText("");
                e3.setText("");
                e4.setText("");

            }
        });

    }
}
