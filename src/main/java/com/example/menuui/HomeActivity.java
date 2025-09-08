package com.example.menuui;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.connectivitymanagerdemo.R;

public class HomeActivity extends Activity {
    private TextView homePage;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);

//        homePage=this.findViewById(R.id.text_home);
//        String uname=getIntent().getStringExtra("wechat");
//        homePage.setText(uname+" 欢迎光临");
    }


//    public void inIt_Value(){
//        homePage=this.findViewById(R.id.text_home);
//        SharedPreferences preferences=getSharedPreferences("Sesson", Context.MODE_PRIVATE);
//        String wechat=preferences.getString("wechat", "NULL");
//        homePage.setText(wechat+" 欢迎光临！");
//    }
}
