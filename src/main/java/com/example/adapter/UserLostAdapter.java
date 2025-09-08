package com.example.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.connectivitymanagerdemo.R;
import com.example.pojo.Lost;
import com.example.pojo.Pick;

import java.util.List;


public class UserLostAdapter extends BaseAdapter {
    private List<Lost> losts;
    private Context context;
    public UserLostAdapter(Context context, List<Lost> losts){
        this.context=context;
        this.losts=losts;
    }

    @Override
    public int getCount() {
        return losts.size();
    }

    @Override
    public Object getItem(int i) {
        return losts.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHander hander;
        if (view==null){
            hander = new ViewHander();
            view = View.inflate(context, R.layout.user_lost_item, null);
            hander.user_lost_name = (TextView) view.findViewById(R.id.user_lost_name);
            hander.user_lost_time = (TextView) view.findViewById(R.id.user_lost_time);
            hander.user_lost_place = (TextView) view.findViewById(R.id.user_lost_place);
            view.setTag(hander);
        }else{
            hander = (ViewHander) view.getTag();
        }
        Lost item = (Lost) getItem(i);
        hander.user_lost_name.setText(item.getLostName());
        hander.user_lost_time.setText(item.getTime());
        hander.user_lost_place.setText(item.getLostAddress());
        return view;
    }
    private static class ViewHander {
        TextView user_lost_name;
        TextView user_lost_time;
        TextView user_lost_place;
    }
}
