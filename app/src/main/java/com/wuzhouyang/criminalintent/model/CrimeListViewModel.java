package com.wuzhouyang.criminalintent.model;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Date;


public class CrimeListViewModel {
    private final ArrayList<Crime> crimes;


    public CrimeListViewModel() {
        crimes = new ArrayList<>();
        int i;
        for (i = 0; i < 100; i++) {
            Crime crime = new Crime("Crime " + i, i % 2 == 0, new Date());
            crimes.add(crime);
        }
    }

    public ArrayList<Crime> getCrimes() {
        return crimes;
    }
}
