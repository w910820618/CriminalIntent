package com.wuzhouyang.criminalintent.fragment;

import static androidx.fragment.app.FragmentManager.TAG;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import com.wuzhouyang.criminalintent.R;

import java.util.Calendar;
import java.util.Date;


public class DatePickerFragment extends DialogFragment {

    private static final String ARG_DATE = "date";
    private Calendar calendar;

    public static final String EXTRA_DATE = "con.bingnerdranch.android.criminalintent.date";

    private DatePicker mDatePicker;

    @SuppressLint("RestrictedApi")
    public static  DatePickerFragment newInstance() {
        return new DatePickerFragment();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_date_picker, null);


        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .setTitle(R.string.date_picker_title)
                .setPositiveButton(android.R.string.ok, null)
                .create();
    }
}