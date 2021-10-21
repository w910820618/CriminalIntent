package com.wuzhouyang.criminalintent.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.wuzhouyang.criminalintent.R;
import com.wuzhouyang.criminalintent.model.Crime;

import java.util.logging.Logger;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class CrimeFragment extends Fragment {

    private static final String TAG = "CrimeFragment";

    private Crime crime;
    private EditText crimeTitle;
    private Button crimeDate;
    private CheckBox crimeSolved;

    public CrimeFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        crime = new Crime();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_crime, container, false);
        initView(view);
        getFragmentArgument();
        return view;
    }

    private void getFragmentArgument() {
        if (getArguments() != null) {
            Log.d(TAG, "getFragmentArgument: ");
            crimeTitle.setText(getArguments().getString("crimeTitle"));
            crimeDate.setText(getArguments().getString("crimeDate"));
        }
    }


    @Override
    public void onStart() {
        super.onStart();
    }

    private void initView(View view) {
        crimeTitle = (EditText) view.findViewById(R.id.crime_title);
        crimeDate = (Button) view.findViewById(R.id.crime_date);
        crimeSolved = (CheckBox) view.findViewById(R.id.crime_solved);
    }


}