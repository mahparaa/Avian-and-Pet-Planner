package com.mahpara.pet_lover_nevigation_fragments;

/**
 * Created by cell  spot on 8/12/2017.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Belal on 18/09/16.
 */


public class DIET_DETAILS extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        View view = null;
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("DIET_PLAN");
        //change R.layout.yourlayoutfilename for each of your fragments
        view= inflater.inflate(R.layout.diet_plan, container, false);
        //Initilizing Views

        return view;
    }

}
