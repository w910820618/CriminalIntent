package com.wuzhouyang.criminalintent.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.wuzhouyang.criminalintent.model.Crime;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Crime.class}, version = 1,exportSchema = false)
@TypeConverters(value = CrimeTypeConverters.class)
public abstract class CrimeDatabase extends RoomDatabase {
    abstract public CrimeDao crimeDao();
}
