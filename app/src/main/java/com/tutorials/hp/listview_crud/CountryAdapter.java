package com.tutorials.hp.listview_crud;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class CountryAdapter extends ArrayAdapter<Country> {
    private List<Country> countryList;
    private Context context;

    public CountryAdapter(List<Country> countryList, Context context) {
        super(context, R.layout.row_layout, countryList);

        this.countryList = countryList;
        this.context = context;
    }

    @Nullable
    @Override
    public Country getItem(int position) {
        return super.getItem(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // row_layout.xml file for list items
            convertView = inflater.inflate(R.layout.row_layout, parent, false);
        }
        Country country = countryList.get(position);
        TextView textName = (TextView) convertView.findViewById(R.id.nameRow);
        TextView textCapital = (TextView) convertView.findViewById(R.id.capitalRow);

        textName.setText(country.getName());
        textCapital.setText(country.getCapital());

        return convertView;
    }
}
