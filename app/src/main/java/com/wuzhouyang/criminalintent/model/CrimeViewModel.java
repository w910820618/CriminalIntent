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

    public void insertCrimeData() {
        int i;
        for (i = 0; i < 100; i++) {
//            this.crimeRepository.insertCrimeCrime(new Crime("数据" + i, i % 2 == 0, new Date()));
            this.crimeRepository.getCrimeDatabase().crimeDao().insertCrime(new Crime("啦啦啦" + i, i % 2 == 0, new Date()));
        }
    }

    public List<Crime> getCrimes() {
        return crimeRepository.getCrimeDatabase().crimeDao().getCrimes();
    }


}
