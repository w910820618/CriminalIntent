package com.wuzhouyang.criminalintent.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.wuzhouyang.criminalintent.model.Crime;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Crime.class}, version = 1, exportSchema = false)
@TypeConverters(value = CrimeTypeConverters.class)
public abstract class CrimeDatabase extends RoomDatabase {


    abstract public CrimeDao crimeDao();

    // marking the instance as volatile to ensure atomic access to the variable
    private static volatile CrimeDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static CrimeDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (CrimeDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            CrimeDatabase.class, "crime")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * Override the onCreate method to populate the database.
     * For this sample, we clear the database every time it is created.
     */
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                CrimeDao dao = INSTANCE.crimeDao();
                dao.deleteAll();
            });
        }
    };
}
