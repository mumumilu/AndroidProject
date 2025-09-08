package com.example.addpage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.connectivitymanagerdemo.MainActivity;
import com.example.connectivitymanagerdemo.R;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class AddLostPage extends Activity {
    Button ok,no,btn1;
    Handler handler;
    EditText goodsname,address,username,phone,wechat,desc;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_lost);
        handler = new Handler();
        goodsname = this.findViewById(R.id.add_lost_name);

        address = this.findViewById(R.id.add_lost_address);

        username = this.findViewById(R.id.add_lost_username);
        username.setText(MainActivity.user.getUsername());
        username.setEnabled(false);
        phone = this.findViewById(R.id.add_lost_phone);
        phone.setText(MainActivity.user.getPhone());
        phone.setEnabled(false);
        wechat = this.findViewById(R.id.add_lost_wechat);
        wechat.setText(MainActivity.user.getWechat());
        wechat.setEnabled(false);
        desc = this.findViewById(R.id.add_lost_desc);

        ok=this.findViewById(R.id.add_lost_ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String good_name = goodsname.getText().toString();
                String uaddress = address.getText().toString();
                String user_name = username.getText().toString();
                String uphone = phone.getText().toString();
                String uwechat = wechat.getText().toString();
                String udesc = desc.getText().toString();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        OkHttpClient client = new OkHttpClient();
                        RequestBody formBody = new FormBody.Builder()
                                .add("good_name", "" + good_name)
                                .add("address", "" + uaddress)
                                .add("user_name", "" + user_name)
                                .add("wechat", "" + uwechat)
                                .add("phone", "" + uphone)
                                .add("desc",""+udesc)
                                .build();
                        Request request = new Request.Builder()
                                .url("http://10.0.2.2:8080/SsmForRight_war_exploded/addLost")
                                .post(formBody)
                                .build();
                        Response response;
                        client.newCall(request).enqueue(new Callback() {
                            @Override
                            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                e.printStackTrace();
                                System.out.println("error");
                            }

                            @Override
                            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                if (!response.isSuccessful()) {
                                    return;
                                }
                                String connectionStatic;
                                String msg;
                                try {
                                    connectionStatic = response.body().string().trim();
                                    System.out.println(connectionStatic);
                                    if (connectionStatic.equals("200")) {
                                        msg = "发布成功";
                                        System.out.println("msg:"+msg);
                                    } else {
                                        msg = "发布失败";
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    return;
                                }
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        if(msg=="发布成功") {

                                        }

                                    }
                                });
                            }
                        });
                    }
                }).start();
            }
        });
        no=this.findViewById(R.id.add_lost_no);
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
            }
        });
        btn1=this.findViewById(R.id.browse);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image:/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent,1);
            }

        });
    }
}
