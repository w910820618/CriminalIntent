package com.wuzhouyang.criminalintent.model;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.wuzhouyang.criminalintent.database.CrimeDatabase;
import com.wuzhouyang.criminalintent.repository.CrimeRepository;

import java.util.Date;
import java.util.List;

public class CrimeViewModel extends AndroidViewModel {
//    private final CrimeRepository crimeRepository;

    private CrimeRepository crimeRepository;

    public CrimeViewModel(@NonNull Application application) {
        super(application);
        crimeRepository = new CrimeRepository(application);
    }


    public void insertCrimeData() {
        int i;
        for (i = 0; i < 100; i++) {
            this.crimeRepository.insert(new Crime("啦啦啦" + i, i % 2 == 0, new Date()));
        }
    }

    public LiveData<List<Crime>> getCrimes() {
        return crimeRepository.getCrimes();
    }
}
