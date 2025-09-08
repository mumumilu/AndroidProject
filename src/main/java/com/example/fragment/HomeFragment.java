package com.example.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.addpage.AddLostPage;
import com.example.addpage.AddPickPage;
import com.example.connectivitymanagerdemo.MainActivity;
import com.example.connectivitymanagerdemo.R;

public class HomeFragment extends Fragment {
    Button lost,pick;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fragment_home,container,false);
        lost=rootView.findViewById(R.id.home_lost);
        lost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(rootView.getContext(), AddLostPage.class);
                startActivity(intent);
            }
        });
        pick=rootView.findViewById(R.id.home_pick);
        pick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(rootView.getContext(), AddPickPage.class);
                startActivity(intent);
            }
        });
        return rootView;
    }


}
