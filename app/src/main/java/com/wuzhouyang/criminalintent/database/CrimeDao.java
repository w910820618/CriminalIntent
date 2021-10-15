package com.wuzhouyang.criminalintent.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.wuzhouyang.criminalintent.model.Crime;

import java.util.List;
import java.util.UUID;

@Dao
public interface CrimeDao {

    @Query("SELECT * from crime")
    List<Crime> getCrimes();

    @Query("SELECT * FROM crime where id=(:id)")
    LiveData<Crime> getCrime(UUID id);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Void insertCrime(Crime crime);
}
