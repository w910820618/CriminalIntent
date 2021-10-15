package com.wuzhouyang.criminalintent.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.wuzhouyang.criminalintent.R;
import com.wuzhouyang.criminalintent.model.Crime;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CrimeAdapter extends RecyclerView.Adapter<CrimeAdapter.ViewHolder> {

    private LiveData<List<Crime>> crimes;

    public CrimeAdapter(LiveData<List<Crime>> crimes) {
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
        Crime crime = Objects.requireNonNull(crimes.getValue()).get(position);
        holder.bind(crime);
    }

    @Override
    public int getItemCount() {
        return Objects.requireNonNull(crimes.getValue()).size();
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
                    Toast.makeText(itemView.getContext(), "被点击了", Toast.LENGTH_SHORT).show();
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
