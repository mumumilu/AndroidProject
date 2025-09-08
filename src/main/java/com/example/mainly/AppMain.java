package com.example.mainly;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.example.connectivitymanagerdemo.R;

public class AppMain extends Activity {
    static final int r=0;
    private TextView testText;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);


//        testText=this.findViewById(R.id.m_testText);
//        Intent intent=getIntent();
//        String str=intent.getStringExtra("wechat");
//        testText.setText(str+" ,欢迎光临");
    }
}
