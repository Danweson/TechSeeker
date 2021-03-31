package com.example.techseeker;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DomainFragment extends Fragment {

    View v;
    private RecyclerView myRecyclerView;
    private List<Domain> lstDomain;
    LinearLayout dom;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_domain, container, false);
        myRecyclerView = v.findViewById(R.id.recycler_view_domain);
        dom = v.findViewById(R.id.id_dom);
        ListAdapter listAdapter = new com.example.techseeker.ListAdapter();

        myRecyclerView.setAdapter(listAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        myRecyclerView.setLayoutManager(layoutManager);
            dom.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openTech();
                }
            });

        return  v;

    }

    public void openTech(){
        Intent intent = new Intent(getActivity(),TechnicianActivity.class);
        startActivity(intent);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }



}
