package com.example.addpage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.adapter.UserLostAdapter;
import com.example.adapter.UserPickAdapter;
import com.example.connectivitymanagerdemo.MainActivity;
import com.example.connectivitymanagerdemo.R;
import com.example.pojo.Lost;
import com.example.pojo.Pick;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LostRecordPage extends Activity {
    private ListView listView;
    private List<Lost> losts;
    Handler handler;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_lostrecord);
        initData();
    }
    private void initAdapter() {
        listView.setAdapter(new UserLostAdapter(this,losts));
    }

    private void initData() {
        List<Lost> list=new ArrayList<>();
        handler=new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                RequestBody formBody = new FormBody.Builder()
                        .add("username", MainActivity.user.getUsername())
                        .build();

                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("http://10.0.2.2:8080/SsmForRight_war_exploded/getLostAllByName")
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
                            connectionStatic=response.body().string();
                            System.out.println("后端数据"+connectionStatic);


                            JSONArray jsonArray = new JSONArray(connectionStatic);
                            for(int i = 0;i < jsonArray.length();i++){
                                Lost lost1 =new Lost();
                                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                                lost1.setLostName(jsonObject.getString("title"));
                                lost1.setLostAddress(jsonObject.getString("path"));
                                lost1.setLostUseName("author");
                                lost1.setLostUserPhone(jsonObject.getString("phone"));
                                lost1.setLostWechat(jsonObject.getString("weChat"));
                                lost1.setLostDesc(jsonObject.getString("content"));
                                lost1.setTime(jsonObject.getString("time"));
                                lost1.setPicUrl("13400232212");
                                list.add(lost1);
                            }

                        }catch(Exception e){
                            e.printStackTrace();
                            return;
                        }
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                losts = list;
                                initUI();
                                initAdapter();
                            }

                        });
                    }

                });
            }
        }).start();

    }

    private void initUI() {
        listView=this.findViewById(R.id.lost_record);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right);
    }
}
