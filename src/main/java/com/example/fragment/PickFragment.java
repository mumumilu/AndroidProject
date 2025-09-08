package com.example.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.adapter.LostAdapter;
import com.example.adapter.PickAdapter;
import com.example.addpage.PickDescPage;
import com.example.connectivitymanagerdemo.R;
import com.example.pojo.Lost;
import com.example.pojo.Pick;
import com.fasterxml.jackson.databind.ObjectMapper;

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

public class PickFragment extends Fragment {
    View rootview;
    private GridView gridView;
    private List<Pick> picks;

    Handler handler;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview=inflater.inflate(R.layout.fragment_pick,container,false);
        initData();
//        initUI();
//        initAdapter();
        return rootview;
    }
    private void initAdapter() {
        gridView.setAdapter(new PickAdapter(rootview.getContext(),picks));
    }

    private void initData() {
        List<Pick> list=new ArrayList<>();
        handler=new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                RequestBody formBody = new FormBody.Builder()
                        .build();

                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("http://10.0.2.2:8080/SsmForRight_war_exploded/getPickAll")
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
                                Pick pick = new Pick();
                                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                                pick.setPickPhone(jsonObject.getString("phone"));
                                pick.setPickAddress(jsonObject.getString("path"));
                                String time1 = jsonObject.getString("time").split("T")[0];
                                pick.setTime(time1);
                                pick.setPickWechat(jsonObject.getString("weChat"));
                                pick.setPickUserAddress(jsonObject.getString("author"));
                                pick.setPickName(jsonObject.getString("title"));
                                pick.setPhoto1(jsonObject.getString("photo1"));
//                                pick.setPhoto(jsonObject.getString("photo").getBytes());
//                                System.out.println(jsonObject.getString("photo").getBytes()==null);
//                                bm= BitmapFactory.decodeByteArray(jsonObject.getString("photo").getBytes(),0,jsonObject.getString("photo").getBytes().length);
                                list.add(pick);

                            }

//        list.add(new Pick("雨伞","111","111","13400232212",
//                "111","111","13400232212","五分钟前"));
                        }catch(Exception e){
                            e.printStackTrace();
                            return;
                        }
                        handler.post(new Runnable() {
                            @Override
                            public void run() {

                                picks = list;
                                initUI();
                                initAdapter();
                            }

                        });
                    }

                });
            }
        }).start();

//        list.add(new Pick("雨伞","111","111","13400232212",
//                "111","111","13400232212","五分钟前"));
//        list.add(new Pick("钥匙","111","111","18400119812",
//                "111","111","18400119812","一个小时前"));
//        list.add(new Pick("一卡通","16677901234","111","16677901234",
//                "111","111","700009","三个小时前"));
//        picks = list;
    }

    private void initUI() {
        gridView=rootview.findViewById(R.id.pick_gridItem);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ObjectMapper mapper=new ObjectMapper();
                String data=null;
                try{
                    data=mapper.writeValueAsString(picks.get(position));
                }catch (Exception e){
                    e.printStackTrace();
                }
                Intent intent = new Intent(rootview.getContext(), PickDescPage.class);
                intent.putExtra("result",data);
                startActivity(intent);
            }
        });
    }
}