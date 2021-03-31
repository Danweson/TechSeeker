package com.example.techseeker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> implements Filterable {

    private static final String TAG = "RecyclerAdapter";
    List<Technician> technicians;

    public RecyclerAdapter(List<Technician> technicians) {
        this.technicians = technicians;
        technicians = new ArrayList<>();
        technicians.addAll(technicians);
    }

    @Override
    public Filter getFilter() {
        return myFilter;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_technician, parent, false);
        RecyclerAdapter.ViewHolder myViewHolder = new RecyclerAdapter.ViewHolder(v);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Technician technician = technicians.get(position);
        String fullName = technician.getFullName();
        String phoneNumber = technician.getPhone();
    }

    @Override
    public int getItemCount() {
        return technicians.size();
    }

    Filter myFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Technician> filteredList = new ArrayList<>();

            if (charSequence == null || charSequence.length() == 0) {
                filteredList.addAll(technicians);
            } else {
                for (Technician technician: technicians) {
                    if (technician.getLocation().contains(charSequence.toString().toLowerCase())) {
                        filteredList.add(technician);
                    }
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults filterResults) {
            technicians.clear();
            technicians.addAll((Collection<? extends Technician>) filterResults.values);
            notifyDataSetChanged();
        }
    };

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView fullName;
        TextView phoneNumber;
        TextView location;
        ImageView imgTech;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            fullName = itemView.findViewById(R.id.tech_full_name);
            phoneNumber = itemView.findViewById(R.id.tech_phone_number);
            location = itemView.findViewById(R.id.tech_location);
            imgTech = itemView.findViewById(R.id.tech_img);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            Toast.makeText(view.getContext(), "OK ", Toast.LENGTH_SHORT).show();
        }
    }
}
