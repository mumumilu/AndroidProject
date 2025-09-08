package cn.ssm.right.service;

import cn.ssm.right.po.Goodsinfo;

import java.util.List;

public interface GoodsService {
    void insertLoss(Goodsinfo goodsinfo);//增加失物数据
    void insertPick(Goodsinfo goodsinfo);//增加拾物数据
    List<Goodsinfo> getLostAll();//得到全部失物
    List<Goodsinfo> getPickAll();//得到全部拾物
    List<Goodsinfo> getLostAllByName(String name);//通过姓名得到全部失物
    List<Goodsinfo> getPickAllByName(String name);//通过姓名得到全部拾物
    void updateName(Goodsinfo goodsinfo);//更新姓名的同时更新失物拾物的姓名
    void insertphoto(Goodsinfo goodsinfo);//加入图片
}
