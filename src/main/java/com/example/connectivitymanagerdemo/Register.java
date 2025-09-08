package com.example.connectivitymanagerdemo;

import android.app.Activity;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Register extends Activity {
    private Button finish,doNo;
    Handler handler;
    public static String uname1;
    EditText name,password,phone,wechat,address;
    TextView textView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        this.doNo=this.findViewById(R.id.re_doNo);
        doNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginView=new Intent(Register.this,MainActivity.class);
                startActivity(loginView);
                overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right);
            }
        });
        handler = new Handler();
        this.finish=this.findViewById(R.id.re_login);
        this.name = this.findViewById(R.id.re_name);
        this.password = this.findViewById(R.id.re_password);
        this.phone = this.findViewById(R.id.re_phone);
        this.wechat = this.findViewById(R.id.re_wechat);
        this.address = this.findViewById(R.id.re_address);
        this.textView = this.findViewById(R.id.testText);

        finish.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (checkNetWorkState()!=true){
                    return;
                }
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
                                .build();
                        Request request = new Request.Builder()
                                .url("http://10.0.2.2:8080/SsmForRight_war_exploded/androidRegister")
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
                                        msg = "注册成功";
                                        System.out.println("msg:"+msg);
                                    } else {
                                        msg = "注册失败";
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    return;
                                }
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        if(msg=="注册成功"){
                                            Toast.makeText(Register.this,"注册成功!请登录",Toast.LENGTH_LONG).show();
                                            Intent loginView=new Intent(Register.this,MainActivity.class);
                                            startActivity(loginView);
                                            overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right);
                                        }

                                    }
                                });
                            }
                        });
                    }
                }).start();
            }
        });
    }

    /**
     * 重写手机返回键
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right);
    }
    private boolean checkNetWorkState(){
        ConnectivityManager cm= (ConnectivityManager) this.getSystemService(MainActivity.CONNECTIVITY_SERVICE);
        NetworkInfo ni=cm.getActiveNetworkInfo();
        if(ni==null||ni.isConnected()==false){
            return false;
        }
        return true;
    }
}
