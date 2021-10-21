package com.wuzhouyang.criminalintent.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

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

        Navigation.findNavController(this,R.id.nav_host_fragment).navigate(R.id.crimeListFragment);

    }

    @Override
    public boolean onSupportNavigateUp() {
        return Navigation.findNavController(this, R.id.nav_host_fragment).navigateUp();
    }
}