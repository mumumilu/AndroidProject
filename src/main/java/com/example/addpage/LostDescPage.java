package com.example.addpage;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.adapter.LostAdapter;
import com.example.connectivitymanagerdemo.R;
import com.example.pojo.Lost;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LostDescPage extends Activity {
    TextView name,place,uname,wechat,desc,time;
    ImageView picture;
    Button ok,no;
    Lost lost;
    public Bitmap bm;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lost_desc);
        ObjectMapper mapper=new ObjectMapper();
        try {
            lost=(Lost)mapper.readValue(getIntent().getStringExtra("result"),Lost.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        picture=findViewById(R.id.lost_desc_picture);
        String base64 = lost.getPhoto1();
        byte [] byteArray=android.util.Base64.decode(base64,android.util.Base64.DEFAULT);
        bm= BitmapFactory.decodeByteArray(byteArray,0,byteArray.length);
        picture.setImageBitmap(bm);
        name=findViewById(R.id.lost_desc_name);
        name.setText(lost.getLostName());
        place=findViewById(R.id.lost_desc_place);
        place.setText(lost.getLostAddress());
        uname=findViewById(R.id.lost_desc_uname);
        uname.setText(lost.getLostUseName());
        wechat=findViewById(R.id.lost_desc_wechat);
        wechat.setText(lost.getLostWechat());
        desc=findViewById(R.id.lost_desc_thing);
        desc.setText(lost.getLostDesc());
        time=findViewById(R.id.lost_desc_time);
        time.setText(lost.getTime());

        no=findViewById(R.id.lost_desc_no);
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right);
            }
        });
        ok=findViewById(R.id.lost_desc_ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right);
    }
}
