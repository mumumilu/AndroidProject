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

import com.example.addpage.LostRecordPage;
import com.example.addpage.PickRecordPage;
import com.example.connectivitymanagerdemo.MainActivity;
import com.example.connectivitymanagerdemo.R;
import com.example.mainly.AppMain;
import com.example.mainly.EditPage;

public class UserFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fragment_user,container,false);
        System.out.println("-------->"+rootView);
        TextView uname=rootView.findViewById(R.id.user_name);
        uname.setText(MainActivity.user.getUsername());
        TextView wechat=rootView.findViewById(R.id.user_wechat);
        wechat.setText(MainActivity.user.getWechat());
        TextView phone=rootView.findViewById(R.id.user_phone);
        phone.setText(MainActivity.user.getPhone());
        TextView address=rootView.findViewById(R.id.user_address);
        address.setText(MainActivity.user.getAddress());
        Button user_lost=rootView.findViewById(R.id.user_lost);
        user_lost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(rootView.getContext(), LostRecordPage.class);
                startActivity(intent);
            }
        });
        Button user_pick=rootView.findViewById(R.id.user_pick);
        user_pick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(rootView.getContext(), PickRecordPage.class);
                startActivity(intent);
            }
        });
        Button button=rootView.findViewById(R.id.user_edit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(rootView.getContext(), EditPage.class);
                startActivity(intent);
            }
        });
        return rootView;
    }
}