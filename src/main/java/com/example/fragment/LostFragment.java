package com.example.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.adapter.LostAdapter;
import com.example.addpage.LostDescPage;
import com.example.connectivitymanagerdemo.MainActivity;
import com.example.connectivitymanagerdemo.R;
import com.example.mainly.Context_Main;
import com.example.pojo.Lost;
import com.example.pojo.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LostFragment extends Fragment {
    View rootview;
    private GridView gridView;
    Handler handler;
    private List<Lost> lost;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview=inflater.inflate(R.layout.fragment_lost,container,false);
        initData();


        return rootview;
    }
    private void initAdapter() {
        gridView.setAdapter(new LostAdapter(rootview.getContext(),lost));
    }

    private void initData() {
        List<Lost> list = new ArrayList<>();
        handler=new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                RequestBody formBody = new FormBody.Builder()
                        .build();

                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("http://10.0.2.2:8080/SsmForRight_war_exploded/getLostAll")
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
                                System.out.println("电话"+jsonObject.getString("phone"));
                                System.out.println("姓名"+jsonObject.getString("author"));
                                System.out.println("微信"+jsonObject.getString("weChat"));
                                System.out.println("创建时间"+jsonObject.getString("time"));
                                lost1.setLostAddress(jsonObject.getString("path"));
                                lost1.setLostDesc(jsonObject.getString("content"));
                                lost1.setLostName(jsonObject.getString("title"));
                                lost1.setLostWechat(jsonObject.getString("weChat"));
                                lost1.setLostUserPhone(jsonObject.getString("phone"));
                                lost1.setLostUseName(jsonObject.getString("author"));
                                lost1.setPicUrl("2131165348");
                                lost1.setPhoto1(jsonObject.getString("photo1"));
//                                lost1.setPhoto(jsonObject.getString("photo").getBytes());
                                String time1 = jsonObject.getString("time").split("T")[0];
                                lost1.setTime(time1);
                                list.add(lost1);
                            }


                        }catch(Exception e){
                            e.printStackTrace();
                            return;
                        }
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                lost = list;
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
        gridView=rootview.findViewById(R.id.lost_gridItem);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ObjectMapper mapper=new ObjectMapper();
                String data=null;
                try{
                    data=mapper.writeValueAsString(lost.get(position));
                }catch (Exception e){
                    e.printStackTrace();
                }
                Intent intent = new Intent(rootview.getContext(), LostDescPage.class);
                intent.putExtra("result",data);
                startActivity(intent);
            }
        });
    }



}