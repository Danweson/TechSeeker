package com.example.techseeker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ListAdapter extends RecyclerView.Adapter {
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_domain, parent,false);
       return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ListViewHolder) holder).bindView(position);
    }

    @Override
    public int getItemCount() {
        return MyData.title.length;
    }

    private class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView tv_name;
        private ImageView img_domain;

        public ListViewHolder(View view){
            super(view);
            tv_name = itemView.findViewById(R.id.my_domain_name);
            img_domain = itemView.findViewById(R.id.img_domain);
        }

        public void bindView(int position){
            tv_name.setText(MyData.title[position]);
            img_domain.setImageResource(MyData.picturePath[position]);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
