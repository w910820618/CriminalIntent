package com.wuzhouyang.criminalintent.adapter;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.wuzhouyang.criminalintent.R;
import com.wuzhouyang.criminalintent.model.Crime;

import java.util.List;
import java.util.Objects;

public class CrimeAdapter extends RecyclerView.Adapter<CrimeAdapter.ViewHolder> {

    private final List<Crime> crimes;

    public CrimeAdapter(List<Crime> crimes) {
        this.crimes = crimes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View contactView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_crime, parent, false);
        return new ViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Crime crime = Objects.requireNonNull(crimes.get(position));
        holder.bind(crime);
    }

    @Override
    public int getItemCount() {
        return crimes.size();
    }


    // 连接视图
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView crimeTitle;
        private final TextView crimeDate;
        private final ImageView solvedImageView;
        private Crime curCrime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("Crime",curCrime);
                    NavController contorller = Navigation.findNavController(v);
                    contorller.navigate(R.id.action_crimeListFragment_to_crimeFragment, bundle);
                }
            });
            crimeTitle = itemView.findViewById(R.id.crime_title);
            crimeDate = itemView.findViewById(R.id.crime_date);
            solvedImageView = itemView.findViewById(R.id.crime_solved);
        }

        public void bind(Crime crime) {
            crimeTitle.setText(crime.getTitle());
            crimeDate.setText(crime.getDate().toString());
            curCrime = crime;
            if (crime.getSolved()) {
                solvedImageView.setVisibility(View.VISIBLE);
            } else {
                solvedImageView.setVisibility(View.GONE);
            }
        }
    }
}
