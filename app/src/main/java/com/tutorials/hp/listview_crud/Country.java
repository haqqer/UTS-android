package com.tutorials.hp.listview_crud;

import android.support.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Country {
    private String name;
    private String capital;

    public Country(JSONObject object) {
        try {
            this.name = object.getString("name");
            this.capital = object.getString("capital");;
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Country> fromJson(JSONArray jsonOjects) {
        ArrayList<Country> countries = new ArrayList<Country>();
        for(int i=0; i < jsonOjects.length(); i++ ){
            try {
                countries.add(new Country(jsonOjects.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return countries;
    }

    public String getName() {
        return name;
    }

    public String getCapital() {
        return capital;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }
}
