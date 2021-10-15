package com.wuzhouyang.criminalintent.repository;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.wuzhouyang.criminalintent.database.CrimeDatabase;

public class CrimeRepository {
    @SuppressLint("StaticFieldLeak")
    private static CrimeRepository mInstance;

    private final CrimeDatabase crimeDatabase;

    private CrimeRepository(Context context) {
        this.crimeDatabase = Room.databaseBuilder(context, CrimeDatabase.class, "crime").build();
    }

    public static synchronized CrimeRepository getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new CrimeRepository(context);
        }
        return mInstance;
    }

    public CrimeDatabase getCrimeDatabase() {
        return crimeDatabase;
    }
}
