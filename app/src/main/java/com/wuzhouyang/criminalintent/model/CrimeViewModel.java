package com.wuzhouyang.criminalintent.model;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.wuzhouyang.criminalintent.database.CrimeDatabase;
import com.wuzhouyang.criminalintent.repository.CrimeRepository;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class CrimeViewModel extends AndroidViewModel {
    private final CrimeRepository crimeRepository;

    public CrimeViewModel(@NonNull Application application) {
        super(application);
        crimeRepository = new CrimeRepository(application);
    }


    public void insertCrimeData() {
        int i;
        for (i = 0; i < 100; i++) {
            this.crimeRepository.insert(new Crime("啦啦啦" + i, i % 2 == 0, getDate(i)));
        }
    }

    public LiveData<List<Crime>> getCrimes() {
        return crimeRepository.getCrimes();
    }

    private Date getDate(int i) {
        Date date = new Date(); //取时间
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, i); //把日期往后增加一天,整数  往后推,负数往前移动
        date = calendar.getTime(); //这个时间就是日期往后推一天的结果
        return date;
    }
}
