package com.wuzhouyang.criminalintent.database;

import androidx.room.TypeConverter;

import java.util.Date;
import java.util.UUID;

public class CrimeTypeConverters {

    @TypeConverter
    public Long fromDate(Date date) {
        return date.getTime();
    }

    @TypeConverter
    public Date toDate(Long sinceEpoch) {
        return new Date(sinceEpoch);
    }

    @TypeConverter
    public UUID toUUID(String uuid) {
        return UUID.fromString(uuid);
    }

    @TypeConverter
    public String fromUUID(UUID uuid) {
        return uuid.toString();
    }



}
