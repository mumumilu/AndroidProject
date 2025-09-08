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

import com.example.connectivitymanagerdemo.R;
import com.example.pojo.Lost;
import com.example.pojo.Pick;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PickDescPage extends Activity {
    TextView address,place,uname,wechat,phone,time;
    ImageView picture;
    Button ok,no;
    Pick pick;
    public Bitmap bm;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pick_desc);
        ObjectMapper mapper=new ObjectMapper();
        try {
            pick=(Pick) mapper.readValue(getIntent().getStringExtra("result"),Pick.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        picture=findViewById(R.id.pick_desc_picture);
        String base64 = pick.getPhoto1();
        byte [] byteArray=android.util.Base64.decode(base64,android.util.Base64.DEFAULT);
        bm= BitmapFactory.decodeByteArray(byteArray,0,byteArray.length);
        picture.setImageBitmap(bm);
        address=findViewById(R.id.pick_desc_address);
        address.setText(pick.getPickUserAddress());
        place=findViewById(R.id.pick_desc_place);
        place.setText(pick.getPickAddress());
        uname=findViewById(R.id.pick_desc_uname);
        uname.setText(pick.getPickName());
        wechat=findViewById(R.id.pick_desc_wechat);
        wechat.setText(pick.getPickWechat());
        phone=findViewById(R.id.pick_desc_phone);
        phone.setText(pick.getPickPhone());
        time=findViewById(R.id.pick_desc_time);
        time.setText(pick.getTime());

        no=findViewById(R.id.pick_desc_no);
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right);
            }
        });
        ok=findViewById(R.id.pick_desc_ok);
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
