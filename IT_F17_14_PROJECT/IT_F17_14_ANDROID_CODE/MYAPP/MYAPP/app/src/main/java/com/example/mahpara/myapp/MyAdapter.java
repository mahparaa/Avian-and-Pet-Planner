package com.example.mahpara.myapp;

/**
 * Created by Mahpara on 3/11/2018.
 */
import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;
public class MyAdapter extends ArrayAdapter<Person> {

    Activity activity;
    int resource;
    List<Person> list;

    public MyAdapter(Activity activity, int resource, List<Person> list) {
        super(activity, resource,list);
        this.activity = activity;
        this.resource = resource;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = activity.getLayoutInflater();

        View view = layoutInflater.inflate(resource,null);

        ImageView imageView = (ImageView) view.findViewById(R.id.getImages);
        TextView name = (TextView) view.findViewById(R.id.username_view);
        TextView petname = (TextView) view.findViewById(R.id.ptname_view);
        TextView email = (TextView) view.findViewById(R.id.email_view);
        TextView age = (TextView) view.findViewById(R.id.ptage_view);
        TextView specie = (TextView) view.findViewById(R.id.ptspecie_view);
        TextView bred = (TextView) view.findViewById(R.id.ptbreed_view);
        TextView timg = (TextView) view.findViewById(R.id.ptTimings_view);
        TextView fvoit = (TextView) view.findViewById(R.id.ptfavfood_view);

        name.setText("Pet Owner: "+list.get(position).getUsername_class());
        petname.setText("Pet Name: "+list.get(position).getPetname_class());
        email.setText("Owner Email: "+list.get(position).getUseremail_class());
        age.setText("Pet Specie: "+list.get(position).getPetspecie_class());
        specie.setText("Pet Breed: "+list.get(position).getPetbreed_class());
        bred.setText("Pet Age: "+list.get(position).getPetage_class());
        timg.setText("Pet Food Timings: "+list.get(position).getPettimings_class());
        fvoit.setText("Pet Favourite Food: "+list.get(position).getPetfood_class());
        Glide.with(activity).load(list.get(position).getPetimage_class()).into(imageView);

        return view;
    }
}


