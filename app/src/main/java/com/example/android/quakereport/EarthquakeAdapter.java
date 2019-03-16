package com.example.android.quakereport;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.android.quakereport.databinding.EathquakeListItemBinding;

import java.util.List;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {
    public EarthquakeAdapter(Context context, List<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.earthquake_activity, parent, false);
        }

        Earthquake earthquake = getItem(position);

        //See: https://github.com/nomanr/android-databinding-example/blob/master/app/src/main/java/com/databinding/example/databindingexample/adapters/SimpleAdapter.java
        LayoutInflater inflater = LayoutInflater.from(getContext());
        EathquakeListItemBinding binding = EathquakeListItemBinding.inflate(inflater,null, false);

        binding.date.setText(earthquake.getDate());
        binding.time.setText(earthquake.getTime());
        binding.location.setText(earthquake.getLocation());
        binding.magnitude.setText(earthquake.getMagnitude());

        return binding.getRoot();
    }
}
