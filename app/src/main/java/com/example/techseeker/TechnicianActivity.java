package com.example.techseeker;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TechnicianActivity extends AppCompatActivity implements TechnicianAdapter.ClickedItem {

    Toolbar toolbar;
    RecyclerView recyclerView;
    private Context mContext;
    Button btnCall, btnMessage;
    TextView contact_phone;

    TechnicianAdapter technicianAdapter;

    private DatabaseReference myRef;

    private ArrayList<Technician> technicianArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_technician);

        recyclerView = findViewById(R.id.recyclerview_tech);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        contact_phone = findViewById(R.id.contact_phone_number);
        btnMessage = findViewById(R.id.btnContactMessage);
        btnCall = findViewById(R.id.btnCall);


        myRef = FirebaseDatabase.getInstance().getReference();

        technicianArrayList = new ArrayList<>();

        clearAll();


        getDataFromFirebase();

    }




    private void getDataFromFirebase() {

        Query query = myRef.child("Technicians");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                clearAll();
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Technician technician = new Technician();
                    technician.setPhoto(dataSnapshot.child("photo").getValue().toString());
                    technician.setFullName(dataSnapshot.child("fullName").getValue().toString());
                    technician.setPhone(dataSnapshot.child("phoneNumber").getValue().toString());
                    technicianArrayList.add(technician);
                }

                technicianAdapter = new TechnicianAdapter(getApplicationContext(),technicianArrayList);
                recyclerView.setAdapter(technicianAdapter);
                technicianAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void clearAll(){
            if (technicianArrayList != null){
                technicianArrayList.clear();
                if (technicianAdapter != null){
                    technicianAdapter.notifyDataSetChanged();
                }
            }

            technicianArrayList = new ArrayList<>();
    }


    @Override
    public void ClickedUser(Technician technician) {

    }
}