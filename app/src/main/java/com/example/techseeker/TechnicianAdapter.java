package com.example.techseeker;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class TechnicianAdapter extends RecyclerView.Adapter<TechnicianAdapter.UserAdapterVH> {
    private static final String Tag = "RecyclerView";

    private ArrayList<Technician> technicianList;
    private Context mContext;

    Dialog dialog;

    public TechnicianAdapter(Context mContext, ArrayList<Technician> technicianList) {
        this.technicianList = technicianList;
        this.mContext = mContext;
    }

    private ClickedItem clickedItem;

    public TechnicianAdapter(ClickedItem clickedItem) {
        this.clickedItem = clickedItem;
    }


    public void setData(List<Technician> technicianList) {
        this.technicianList.addAll(technicianList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TechnicianAdapter.UserAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_technician,parent,false);
        UserAdapterVH userAdapterVH = new UserAdapterVH(view);

        dialog = new Dialog(mContext);
        dialog.setContentView(R.layout.dialog_technician);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));



        userAdapterVH.item_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tv_contact_name = dialog.findViewById(R.id.contact_name);
                TextView tv_phone_number = dialog.findViewById(R.id.contact_phone_number);
                ImageView img_contact = dialog.findViewById(R.id.img_contact);
                tv_contact_name.setText(technicianList.get(userAdapterVH.getAdapterPosition()).getFullName());
                tv_phone_number.setText(technicianList.get(userAdapterVH.getAdapterPosition()).getPhone());
                img_contact.setImageResource(Integer.parseInt(technicianList.get(userAdapterVH.getAdapterPosition()).getPhoto()));
                Toast.makeText(mContext,"Test click"+String.valueOf(userAdapterVH.getAdapterPosition()),Toast.LENGTH_SHORT).show();
                dialog.show();
            }
        });
        return userAdapterVH;
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapterVH holder, int position) {

        holder.fullName.setText(technicianList.get(position).getFullName());
        holder.phoneNumber.setText(technicianList.get(position).getPhone());
        holder.location.setText(technicianList.get(position).getLocation());

        Glide.with(mContext)
               .load(technicianList.get(position).getPhoto())
                .into(holder.imgTech);

    }

    public interface ClickedItem{
        public void ClickedUser(Technician technician);
    }

    @Override
    public int getItemCount() {
        return technicianList.size();
    }

    public class UserAdapterVH extends RecyclerView.ViewHolder {

        LinearLayout item_contact;
        TextView fullName;
        TextView phoneNumber;
        TextView location;
        ImageView imgTech;

        public UserAdapterVH(@NonNull View itemView) {
            super(itemView);
            item_contact = itemView.findViewById(R.id.technician_item_id);
            fullName = itemView.findViewById(R.id.tech_full_name);
            phoneNumber = itemView.findViewById(R.id.tech_phone_number);
            location = itemView.findViewById(R.id.tech_location);
            imgTech = itemView.findViewById(R.id.tech_img);


        }
    }
}
