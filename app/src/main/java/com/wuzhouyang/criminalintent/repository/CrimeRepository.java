package com.wuzhouyang.criminalintent.repository;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.wuzhouyang.criminalintent.database.CrimeDao;
import com.wuzhouyang.criminalintent.database.CrimeDatabase;
import com.wuzhouyang.criminalintent.model.Crime;

import java.util.List;

public class CrimeRepository {
    private CrimeDao crimeDao;
    private LiveData<List<Crime>> crimes;

    public CrimeRepository(Application application) {
        CrimeDatabase crimeDatabase = CrimeDatabase.getDatabase(application);
        crimeDao = crimeDatabase.crimeDao();
        crimes = crimeDao.getCrimes();
    }


    public LiveData<List<Crime>> getCrimes() {
        return crimes;
    }

    public void insert(Crime crime) {
        CrimeDatabase.databaseWriteExecutor.execute(() -> {
            crimeDao.insertCrime(crime);
        });
    }
}
