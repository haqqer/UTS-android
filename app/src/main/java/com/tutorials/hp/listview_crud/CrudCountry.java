package com.tutorials.hp.listview_crud;

import org.json.JSONArray;
import org.json.JSONObject;

public class CrudCountry {
    private JSONArray countries = new JSONArray();

    public void save(JSONObject country) {
        System.out.println(country);
        countries.put(country);
    }

    public Boolean update(int index, JSONObject country) {
        try {
//            countries.remove(index);
            countries.put(index, country);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public Boolean delete(int index) {
        try {
            countries.remove(index);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public JSONArray getCountries() {
        return countries;
    }
}
