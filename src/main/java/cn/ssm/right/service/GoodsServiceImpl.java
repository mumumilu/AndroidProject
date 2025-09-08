package cn.ssm.right.service;

import cn.ssm.right.mapper.GoodsMapper;
import cn.ssm.right.mapper.LoginMapper;
import cn.ssm.right.po.Goodsinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService{
    private GoodsMapper goodsMapper;
    @Autowired
    public void setGoodsMapper(GoodsMapper goodsMapper){
        this.goodsMapper = goodsMapper;
    }
    @Override
    public void insertLoss(Goodsinfo goodsinfo) {
        goodsMapper.insertLoss(goodsinfo);
    }

    @Override
    public void insertPick(Goodsinfo goodsinfo) {
        goodsMapper.insertPick(goodsinfo);
    }

    @Override
    public List<Goodsinfo> getLostAll() {
        return goodsMapper.getLostAll();
    }

    @Override
    public List<Goodsinfo> getPickAll() {
        return goodsMapper.getPickAll();
    }

    @Override
    public List<Goodsinfo> getLostAllByName(String name) {
        return goodsMapper.getLostAllByName(name);
    }

    @Override
    public List<Goodsinfo> getPickAllByName(String name) {
        return goodsMapper.getPickAllByName(name);
    }

    @Override
    public void updateName(Goodsinfo goodsinfo) {
        goodsMapper.updateName(goodsinfo);
    }

    @Override
    public void insertphoto(Goodsinfo goodsinfo) {
        goodsMapper.insertphoto(goodsinfo);
    }
}
