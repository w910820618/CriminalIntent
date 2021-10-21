package com.wuzhouyang.criminalintent.adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.wuzhouyang.criminalintent.R;
import com.wuzhouyang.criminalintent.model.Crime;

import java.util.ArrayList;
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

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Toast.makeText(itemView.getContext(), crimeTitle.getText() + "被点击了", Toast.LENGTH_SHORT).show();
                    Bundle bundle = new Bundle();
                    bundle.putString("crimeTitle", crimeTitle.getText().toString());
                    bundle.putString("crimeDate", crimeDate.getText().toString());
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
            if (crime.getSolved()) {
                solvedImageView.setVisibility(View.VISIBLE);
            } else {
                solvedImageView.setVisibility(View.GONE);
            }
        }
    }
}
