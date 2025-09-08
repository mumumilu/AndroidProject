package com.example.mainly;

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
import com.example.fragment.UserFragment;
import com.example.pojo.User;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class EditPage extends Activity {
    private EditText name,wechat,phone,address,password;
    private Button ok,no;
    Handler handler;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_edit);

        name=this.findViewById(R.id.ed_name);
        name.setText(MainActivity.user.getUsername());
        wechat=this.findViewById(R.id.ed_wechat);
        wechat.setText(MainActivity.user.getWechat());
        phone=this.findViewById(R.id.ed_phone);
        phone.setText(MainActivity.user.getPhone());
        address=this.findViewById(R.id.ed_address);
        address.setText(MainActivity.user.getAddress());
        password=this.findViewById(R.id.ed_password);
        password.setText(MainActivity.user.getPassword());

        ok=this.findViewById(R.id.ed_ok);
        no=this.findViewById(R.id.ed_doNo);
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right);
            }
        });
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handler = new Handler();
                String uname = name.getText().toString();
                String upassword = password.getText().toString();
                String uphone = phone.getText().toString();
                String uwechat = wechat.getText().toString();
                String uaddress = address.getText().toString();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        OkHttpClient client = new OkHttpClient();
                        RequestBody formBody = new FormBody.Builder()
                                .add("username", "" + uname)
                                .add("password", "" + upassword)
                                .add("phone", "" + uphone)
                                .add("wechat", "" + uwechat)
                                .add("address", "" + uaddress)
                                .add("oldname",""+MainActivity.user.getUsername())
                                .build();
                        Request request = new Request.Builder()
                                .url("http://10.0.2.2:8080/SsmForRight_war_exploded/androidUpdate")
                                .post(formBody)
                                .build();
                        Response response;
                        User user1 = new User();
                        user1.setAddress(uaddress);
                        user1.setUsername(uname);
                        user1.setPassword(upassword);
                        user1.setPhone(uphone);
                        user1.setWechat(uwechat);
                        MainActivity.user = user1;
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
                                        msg = "修改成功";
                                        System.out.println(msg);
                                    } else {
                                        msg = "修改失败";
                                        System.out.println(msg);
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    System.out.println("error");
                                    return;
                                }
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
//                                        onBackPressed();
//                                        overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right);
                                    }
                                });
                            }
                        });
                    }
                }).start();
            }
        });
    }
}
