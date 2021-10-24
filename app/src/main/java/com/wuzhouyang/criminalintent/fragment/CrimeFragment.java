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
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.wuzhouyang.criminalintent.R;
import com.wuzhouyang.criminalintent.model.Crime;

import java.util.Calendar;
import java.util.Date;
import java.util.logging.Logger;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class CrimeFragment extends Fragment {

    private static final String TAG = "CrimeFragment";

    private static final String DIALOG_DATE = "DialogDate";

    private EditText crimeTitle;
    private Button crimeDate;
    private CheckBox crimeSolved;
    private Crime crime;
    private DatePickerFragment dialog;

    public CrimeFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 监听子fragment的返回值
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_crime, container, false);
        initView(view);
        getFragmentArgument();
        crimeDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = DatePickerFragment.getInstance(crime.getDate());
                getChildFragmentManager().setFragmentResultListener("requestKey", dialog, new FragmentResultListener() {
                    @Override
                    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                        Calendar crimeDate = (Calendar) result.getSerializable("bundleKey");
                        Date date = crimeDate.getTime();
                        Log.d("CrimeFragment", date.toString());
                    }
                });
                dialog.show(getChildFragmentManager(), "Date");
            }

        });
        return view;
    }

    private void getFragmentArgument() {
        if (getArguments() != null) {
            crime = (Crime) getArguments().getSerializable("Crime");
            crimeTitle.setText(crime.getTitle());
            crimeDate.setText(crime.getDate().toString());
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