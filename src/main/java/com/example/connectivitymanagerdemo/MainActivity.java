package com.example.connectivitymanagerdemo;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mainly.AppMain;
import com.example.mainly.Context_Main;
import com.example.pojo.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    public static User user;
    public static String uname;
    Handler handler;
    EditText useName,passWord;
    Button btn,reg;
    TextView test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler=new Handler();
        this.test=this.findViewById(R.id.testText);
        this.useName=this.findViewById(R.id.username);
        this.passWord=this.findViewById(R.id.password);
        this.btn=this.findViewById(R.id.btn_1);
        this.reg=this.findViewById(R.id.btn_2);
        /**
         * 登录按钮处理事件
         */
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkNetWorkState()!=true){
            Toast.makeText(MainActivity.this,"网络连接失败",Toast.LENGTH_LONG).show();
                return;
        }
                String unText=useName.getText().toString();
                String pswText=passWord.getText().toString();
                new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                RequestBody formBody = new FormBody.Builder()
                        .add("username", "" + unText)
                        .add("password", "" + pswText)
                        .build();
                Request request = new Request.Builder()
                        .url("http://10.0.2.2:8080/SsmForRight_war_exploded/androidLogin")
                        .post(formBody)
                        .build();

                Response response;
                //异步方式请求
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        if (!response.isSuccessful()){
                            return;
                        }
                        String connectionStatic;
                        String msg;
                        try{
                            connectionStatic=response.body().string().trim();
                            System.out.println("后端数据"+connectionStatic);
                            System.out.println(connectionStatic.equals("300"));
                            if(connectionStatic.equals("300")==false) {
                                msg="登录成功";
                                ObjectMapper json = new ObjectMapper();
                                Map<String, Object> userBaseMap = json.readValue(connectionStatic, new TypeReference<Map<String, Object>>() {
                                });
                                User user1 = new User();
                                user1.setAddress(String.valueOf(userBaseMap.get("path")));
                                user1.setUsername(String.valueOf(userBaseMap.get("login_name")));
                                user1.setPassword(String.valueOf(userBaseMap.get("password")));
                                user1.setPhone(String.valueOf(userBaseMap.get("phone")));
                                user1.setWechat(String.valueOf(userBaseMap.get("weChat")));
                                user = user1;
                            }
                            else{
                                msg="登录失败";
                                System.out.println(msg);
                            }
                        }catch(Exception e){
                            e.printStackTrace();
                            return;
                        }
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this,msg,Toast.LENGTH_LONG).show();
                                if(msg.equals("登录成功")){
                                    Intent intent=new Intent(MainActivity.this,Context_Main.class);
//                                    intent.putExtra("wechat",unText);
//                                    MainActivity.this.setResult(Activity.RESULT_OK,intent);
                                    uname=unText;
                                    startActivity(intent);
                                }
                            }
                        });
                    }

                });
            }
                }).start();
            }
        });
        /**
         * 注册按钮点击跳转注册页面
         */
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerView=new Intent(MainActivity.this,Register.class);
                startActivity(registerView);
                MainActivity.this.overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
            }
        });
    }

    /**
     * 检查网络连接状态
     * @return
     */
    private boolean checkNetWorkState(){
        ConnectivityManager cm= (ConnectivityManager) this.getSystemService(MainActivity.CONNECTIVITY_SERVICE);
        NetworkInfo ni=cm.getActiveNetworkInfo();
        if(ni==null||ni.isConnected()==false){
            return false;
        }
        return true;
    }

    }
