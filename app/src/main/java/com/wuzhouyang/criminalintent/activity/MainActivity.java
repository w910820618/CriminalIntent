package com.wuzhouyang.criminalintent.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.wuzhouyang.criminalintent.R;
import com.wuzhouyang.criminalintent.fragment.CrimeFragment;
import com.wuzhouyang.criminalintent.fragment.CrimeListFragment;
import com.wuzhouyang.criminalintent.model.Crime;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);

        if (currentFragment == null) {
//            CrimeFragment crimeFragment = new CrimeFragment();
            CrimeListFragment crimeListFragment = new CrimeListFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, crimeListFragment)
                    .commit();
        }

    }
}