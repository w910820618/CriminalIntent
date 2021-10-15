package com.wuzhouyang.criminalintent.model;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.wuzhouyang.criminalintent.repository.CrimeRepository;

import java.util.Date;
import java.util.List;

public class CrimeViewModel extends ViewModel {
    private final CrimeRepository crimeRepository;

    public CrimeViewModel(CrimeRepository crimeRepository) {
        this.crimeRepository = crimeRepository;
    }

    public void insertData() {
        int i;
        for (i = 0; i < 100; i++) {
//            this.crimeRepository.insertCrime(new Crime("数据" + i, i % 2 == 0, new Date()));
            this.crimeRepository.getCrimeDatabase().crimeDao().insert(new Crime("数据" + i, i % 2 == 0, new Date()));
        }
    }

    public LiveData<List<Crime>> getCrimes() {
        return crimeRepository.getCrimeDatabase().crimeDao().getCrimes();
    }


}
