package com.wuzhouyang.criminalintent.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.wuzhouyang.criminalintent.database.CrimeTypeConverters;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity(tableName = "crime")
public class Crime implements Serializable {

    @PrimaryKey
    @NonNull
    private UUID id;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "date")
    private Date date;

    @ColumnInfo(name = "isSoloved")
    private Boolean isSolved;

    public Crime(String title, Boolean isSolved, Date date) {
        this.title = title;
        this.isSolved = isSolved;
        this.id = UUID.randomUUID();
        this.date = date;
    }

    @Ignore
    public Crime() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setSolved(Boolean solved) {
        isSolved = solved;
    }

    public String getTitle() {
        return title;
    }

    public Date getDate() {
        return date;
    }

    public Boolean getSolved() {
        return isSolved;
    }

    @Override
    public String toString() {
        return "Crime{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", date=" + date +
                ", isSolved=" + isSolved +
                '}';
    }
}
