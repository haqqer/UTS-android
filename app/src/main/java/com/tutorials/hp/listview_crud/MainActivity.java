package com.tutorials.hp.listview_crud;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    ArrayAdapter<String> adapter;
    List<Country> countryList;
    JSONArray jsonArray;
    JSONObject jsonObject;
    CRUD crud=new CRUD();
    CrudCountry crud2 = new CrudCountry();
    Dialog d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lv= (ListView) findViewById(R.id.lv);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(d != null) {
                    if(!d.isShowing())
                    {
                        displayInputDialog(i);
                    }else
                    {
                        d.dismiss();
                    }
                }
            }
        });

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                displayInputDialog(-1);
            }
        });
    }
    private void displayInputDialog(final int pos)
    {
        d=new Dialog(this);
        d.setTitle("LISTVIEW CRUD");
        d.setContentView(R.layout.input_dialog);
        final EditText nameEditTxt= (EditText) d.findViewById(R.id.nameEditText);
        final EditText capitalEditTxt= (EditText) d.findViewById(R.id.capitalEditText);
        Button addBtn= (Button) d.findViewById(R.id.addBtn);
        Button updateBtn= (Button) d.findViewById(R.id.updateBtn);
        Button deleteBtn= (Button) d.findViewById(R.id.deleteBtn);

        if(pos== -1)
        {
            addBtn.setEnabled(true);
            updateBtn.setEnabled(false);
            deleteBtn.setEnabled(false);
        }else
        {
            addBtn.setEnabled(true);
            updateBtn.setEnabled(true);
            deleteBtn.setEnabled(true);
//            nameEditTxt.setText(crud.getNames().get(pos));
            try {
                jsonObject = crud2.getCountries().getJSONObject(pos);
                nameEditTxt.setText(jsonObject.getString("name"));
                capitalEditTxt.setText(jsonObject.getString("capital"));
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //GET DATA
                String name=nameEditTxt.getText().toString();
                String capital=capitalEditTxt.getText().toString();
                JSONObject item = new JSONObject();
                try {
                    item.put("name", name);
                    item.put("capital", capital);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //VALIDATE
                if(name.length()>0 && name != null)
                {
                    //save
//                    crud.save(name);
                    crud2.save(item);
                    countryList = Country.fromJson(crud2.getCountries());
                    CountryAdapter countryAdapter = new CountryAdapter(countryList, MainActivity.this);
                    nameEditTxt.setText("");
                    capitalEditTxt.setText("");
//                    adapter=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,crud.getNames());
                    lv.setAdapter(countryAdapter);

                }else
                {
                    Toast.makeText(MainActivity.this, "Name cannot be empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //GET DATA
                String newName=nameEditTxt.getText().toString();
                String newCapital=capitalEditTxt.getText().toString();
                JSONObject item = new JSONObject();
                try {
                    item.put("name", newName);
                    item.put("capital", newCapital);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                //VALIDATE
                if(newName.length()>0 && newName != null)
                {
                    //save
                    if(crud2.update(pos,item))
                    {
                        nameEditTxt.setText(newName);
                        capitalEditTxt.setText(newCapital);
                        countryList = Country.fromJson(crud2.getCountries());
                        CountryAdapter countryAdapter = new CountryAdapter(countryList, MainActivity.this);
//                        adapter=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,crud.getNames());
                        lv.setAdapter(countryAdapter);
                    }

                }else
                {
                    Toast.makeText(MainActivity.this, "Name cannot be empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //DELETE
                if( crud2.delete(pos))
                {
                    nameEditTxt.setText("");
                    capitalEditTxt.setText("");
                    countryList = Country.fromJson(crud2.getCountries());
                    CountryAdapter countryAdapter = new CountryAdapter(countryList, MainActivity.this);
//                        adapter=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,crud.getNames());
                    lv.setAdapter(countryAdapter);
                }
            }
        });

        d.show();
    }
}
