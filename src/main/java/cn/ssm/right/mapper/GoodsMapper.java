package cn.ssm.right.mapper;

import cn.ssm.right.po.Goodsinfo;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import java.util.List;

public interface GoodsMapper {
    @InsertProvider(type = GoodsSql.class, method = "insertLoss")
    void insertLoss(Goodsinfo goodsinfo);//增加失物数据

    @InsertProvider(type = GoodsSql.class,method = "insertPick")
    void insertPick(Goodsinfo goodsinfo);//增加拾物数据

    @SelectProvider(type = GoodsSql.class,method = "getLostAll")
    List<Goodsinfo> getLostAll();//得到全部失物

    @SelectProvider(type = GoodsSql.class,method = "getLostAllByName")
    List<Goodsinfo> getLostAllByName(String name);//通过姓名得到全部失物

    @SelectProvider(type = GoodsSql.class,method = "getPickAll")
    List<Goodsinfo> getPickAll();//得到全部拾物

    @SelectProvider(type = GoodsSql.class,method = "getPickAllByName")
    List<Goodsinfo> getPickAllByName(String name);//通过姓名得到全部拾物

    @UpdateProvider(type =GoodsSql.class, method = "updateName")
    void updateName(Goodsinfo goodsinfo);//更新姓名的同时更新失物拾物的姓名

    @UpdateProvider(type = GoodsSql.class,method = "insertphoto")
    void insertphoto(Goodsinfo goodsinfo);//加入图片
}
