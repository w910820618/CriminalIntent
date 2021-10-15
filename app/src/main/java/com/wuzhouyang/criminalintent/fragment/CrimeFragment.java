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
 * Use the {@link CrimeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CrimeFragment extends Fragment {

    private Crime crime;
    private EditText crimeTitle;
    private Button crimeDate;
    private CheckBox crimeSolved;

    public CrimeFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static CrimeFragment newInstance() {
        CrimeFragment fragment = new CrimeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        crime = new Crime();
        if (savedInstanceState != null) {
            crime.setTitle(savedInstanceState.getString("title"));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_crime, container, false);
        initView(view);
        crimeSolved.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                crime.setSolved(isChecked);
            }
        });

        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString("title", crime.getTitle());
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onStart() {
        super.onStart();
        crimeTitle.addTextChangedListener(mInputMobileWatcher);
    }

    private void initView(View view) {
        crimeTitle = (EditText) view.findViewById(R.id.crime_title);
        crimeDate = (Button) view.findViewById(R.id.crime_date);
        crimeSolved = (CheckBox) view.findViewById(R.id.crime_solved);
    }

    // TextWatcher监听文本输入框变化
    public TextWatcher mInputMobileWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            //监听输入框变化之前做一些操作
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //监听输入框变化时（ing）做一些操作
            crime.setTitle(s.toString());
        }


        @Override
        public void afterTextChanged(Editable s) {
            //监听输入框变化结束（ed）做一些操作
        }
    };
}