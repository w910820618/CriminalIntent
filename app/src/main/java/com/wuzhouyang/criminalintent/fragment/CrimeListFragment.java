package com.wuzhouyang.criminalintent.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wuzhouyang.criminalintent.R;
import com.wuzhouyang.criminalintent.adapter.CrimeAdapter;
import com.wuzhouyang.criminalintent.model.Crime;
import com.wuzhouyang.criminalintent.model.CrimeViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CrimeListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CrimeListFragment extends Fragment {

    private static final String TAG = "CrimeListFragment";

    //    private CrimeListViewModel crimeViewModel;
    private CrimeViewModel crimeViewModel;
    private RecyclerView crimeRecyclerView;

    public CrimeListFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static CrimeListFragment newInstance() {
        return new CrimeListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        crimeViewModel = new CrimeViewModel(getActivity().getApplication());
        crimeViewModel.insertCrimeData();
//        Log.d(TAG, "Total crimes: " + crimeViewModel.getCrimes().size());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crime_list, container, false);
        initView(view);
        CrimeViewModel crimeViewModel = new ViewModelProvider(
                this, ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(CrimeViewModel.class);

        crimeViewModel.getCrimes().observe(this, new Observer<List<Crime>>() {
            @Override
            public void onChanged(List<Crime> crimes) {
                CrimeAdapter crimeAdapter = new CrimeAdapter(crimes);
                crimeRecyclerView.setAdapter(crimeAdapter);
                crimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
            }
        });

        return view;
    }

    private void initView(View view) {
        crimeRecyclerView = (RecyclerView) view.findViewById(R.id.crime_recycler_view);
    }
}