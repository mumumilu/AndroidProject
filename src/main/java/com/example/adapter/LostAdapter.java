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

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

public class LostAdapter extends BaseAdapter {
    private List<Lost> lostS;
    private Context context;
    public Bitmap bm;
    public LostAdapter(Context context,List<Lost> lost){
        this.context=context;
        this.lostS=lost;
    }
    @Override
    public int getCount() {
        return lostS.size();
    }

    @Override
    public Object getItem(int i) {
        return lostS.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view==null){
            holder = new ViewHolder();
            view = View.inflate(context, R.layout.lost_item, null);
            holder.lost_picture = (ImageView) view.findViewById(R.id.ap_lost_picture);
            holder.lost_name = (TextView) view.findViewById(R.id.ap_lost_name);
            holder.lost_phone = (TextView) view.findViewById(R.id.ap_lost_phone);
            holder.lost_time = (TextView) view.findViewById(R.id.ap_lost_time);

            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }
        Lost item = (Lost) getItem(i);

        String base64 = item.getPhoto1();
        byte [] byteArray=android.util.Base64.decode(base64,android.util.Base64.DEFAULT);
//        bm= BitmapFactory.decodeByteArray(b,0,b.length);
//        holder.lost_picture.setImageBitmap(bm);
         bm= BitmapFactory.decodeByteArray(byteArray,0,byteArray.length);

        holder.lost_picture.setImageBitmap(bm);

//        holder.lost_picture.setImageResource(R.drawable.a_cart);
        holder.lost_name.setText(item.getLostName());
        holder.lost_phone.setText(item.getLostUserPhone());
        holder.lost_time.setText(item.getTime());
        return view;
    }
    private static class ViewHolder {
        ImageView lost_picture;
        TextView lost_name;
        TextView lost_phone;
        TextView lost_time;
    }

    public static Bitmap getBitmapFromByte(byte[] temp){
        if(temp != null){
            Bitmap bitmap = BitmapFactory.decodeByteArray(temp, 0, temp.length);
            return bitmap;
        }else{
            return null;
        }
    }




}
