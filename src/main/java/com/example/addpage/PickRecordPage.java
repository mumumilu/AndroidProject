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

import com.example.adapter.PickAdapter;
import com.example.adapter.UserPickAdapter;
import com.example.connectivitymanagerdemo.MainActivity;
import com.example.connectivitymanagerdemo.R;
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

public class PickRecordPage extends Activity {
    private ListView listView;
    private List<Pick> picks;
    Handler handler;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_pickrecord);
        initData();
//        initUI();
//        initAdapter();

    }
    private void initAdapter() {
        listView.setAdapter(new UserPickAdapter(this,picks));
    }

    private void initData() {
        List<Pick> list=new ArrayList<>();

        handler=new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                RequestBody formBody = new FormBody.Builder()
                        .add("username", MainActivity.user.getUsername())
                        .build();

                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("http://10.0.2.2:8080/SsmForRight_war_exploded/getPickAllByName")
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
                                pick.setTime(jsonObject.getString("time"));
                                pick.setPickWechat(jsonObject.getString("weChat"));
                                pick.setPickName(jsonObject.getString("title"));
                                list.add(pick);

                            }

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

//        list.add(new Pick("雨伞","111","111","东区13栋楼梯",
//                "111","111","13400232212","2021-5-31"));
//        list.add(new Pick("钥匙","111","111","操场厕所",
//                "111","111","18400119812","2021-6-01"));
//        list.add(new Pick("一卡通","16677901234","111","教学楼2101",
//                "111","111","700009","2021-6-11"));
//        list.add(new Pick("钥匙","111","111","实训楼B201",
//                "111","111","18400119812","2021-6-12"));
//        list.add(new Pick("一卡通","16677901234","111","实训楼C201",
//                "111","111","700009","2021-6-13"));
//        list.add(new Pick("钥匙","111","111","保安厅",
//                "111","111","18400119812","2021-6-14"));
//        list.add(new Pick("一卡通","16677901234","111","实训楼B601",
//                "111","111","700009","2021-6-15"));
//        picks = list;
    }

    private void initUI() {
        listView=this.findViewById(R.id.pick_record);

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
