package com.example.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.connectivitymanagerdemo.R;
import com.example.pojo.Lost;
import com.example.pojo.Pick;

import java.util.List;

public class PickAdapter extends BaseAdapter {
    private List<Pick> picks;
    private Context context;
    Bitmap bm;
    public PickAdapter(Context context,List<Pick> picks){
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
            view = View.inflate(context, R.layout.pick_item, null);
            hander.pick_picture = (ImageView) view.findViewById(R.id.ap_pick_picture);
            hander.pick_name = (TextView) view.findViewById(R.id.ap_pick_name);
            hander.pick_phone = (TextView) view.findViewById(R.id.ap_pick_phone);
            hander.pick_time = (TextView) view.findViewById(R.id.ap_pick_time);
            view.setTag(hander);
        }else{
            hander = (ViewHander) view.getTag();
        }
        Pick item = (Pick) getItem(i);
        String base64 = item.getPhoto1();
        byte [] byteArray=android.util.Base64.decode(base64,android.util.Base64.DEFAULT);
//        bm= BitmapFactory.decodeByteArray(b,0,b.length);
//        holder.lost_picture.setImageBitmap(bm);
        bm= BitmapFactory.decodeByteArray(byteArray,0,byteArray.length);

        hander.pick_picture.setImageBitmap(bm);

        hander.pick_name.setText(item.getPickName());
        hander.pick_phone.setText(item.getPickPhone());
        hander.pick_time.setText(item.getTime());
        return view;
    }
    private static class ViewHander {
        ImageView pick_picture;
        TextView pick_name;
        TextView pick_phone;
        TextView pick_time;
    }
}
