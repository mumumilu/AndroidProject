package com.example.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.connectivitymanagerdemo.R;
import com.example.pojo.Pick;

import java.util.List;


public class UserPickAdapter extends BaseAdapter {
    private List<Pick> picks;
    private Context context;
    public UserPickAdapter(Context context, List<Pick> picks){
        this.context=context;
        this.picks=picks;
    }

    @Override
    public int getCount() {
        return picks.size();
    }

    @Override
    public Object getItem(int i) {
        return picks.get(i);
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
            view = View.inflate(context, R.layout.user_pick_item, null);
            hander.user_pick_name = (TextView) view.findViewById(R.id.user_pick_name);
            hander.user_pick_time = (TextView) view.findViewById(R.id.user_pick_time);
            hander.user_pick_place = (TextView) view.findViewById(R.id.user_pick_place);
            view.setTag(hander);
        }else{
            hander = (ViewHander) view.getTag();
        }
        Pick item = (Pick) getItem(i);
        hander.user_pick_name.setText(item.getPickName());
        hander.user_pick_time.setText(item.getTime());
        hander.user_pick_place.setText(item.getPickAddress());
        return view;
    }
    private static class ViewHander {
        TextView user_pick_name;
        TextView user_pick_time;
        TextView user_pick_place;
    }
}
