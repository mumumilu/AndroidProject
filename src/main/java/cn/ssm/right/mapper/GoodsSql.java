package cn.ssm.right.mapper;

import cn.ssm.right.po.Goodsinfo;
import org.apache.ibatis.jdbc.SQL;

public class GoodsSql {
    public String insertLoss(final Goodsinfo goodsinfo){
        return new SQL(){
            {
                INSERT_INTO("goods");
                VALUES("title","#{title}");
                VALUES("phone","#{phone}");
                VALUES("author","#{author}");
                VALUES("path","#{path}");
                VALUES("content","#{content}");
                VALUES("WeChat","#{WeChat}");
                VALUES("isOver","2");
                VALUES("photo","#{photo}");
            }
        }.toString();
    }
    public String insertPick(final Goodsinfo goodsinfo){
        return new SQL(){
            {
                INSERT_INTO("goods");
                VALUES("title","#{title}");//物品名称
                VALUES("phone","#{phone}");
                VALUES("author","#{author}");//发布人
                VALUES("path","#{path}");//地方
                VALUES("content","#{content}");//物品描述
                VALUES("WeChat","#{WeChat}");
                VALUES("isOver","1");
                VALUES("photo","#{photo}");


            }
        }.toString();
    }
    public String insertphoto(final Goodsinfo goodsinfo){
        return new SQL(){
            {
                UPDATE("goods");
                SET("photo=#{photo}");
                WHERE("id=17");
            }
        }.toString();
    }

    public String getLostAll(){
        return new SQL(){
            {
               SELECT("*");
               FROM("goods");
               WHERE("isOver=2");
            }
        }.toString();
    }
    public String getLostAllByName(final String name){
        return new SQL(){
            {
                SELECT("*");
                FROM("goods");
                WHERE("isOver=2 and author=#{name}");
            }
        }.toString();
    }
    public String getPickAllByName(final String name){
        return new SQL(){
            {
                SELECT("*");
                FROM("goods");
                WHERE("isOver=1 and author=#{name}");
            }
        }.toString();
    }
    public String getPickAll(){
        return new SQL(){
            {
                SELECT("*");
                FROM("goods");
                WHERE("isOver=1");
            }
        }.toString();
    }

    public String updateName(final Goodsinfo goodsinfo){
        return new SQL(){
            {
                UPDATE("goods");
                SET("author=#{author}");
                SET("WeChat=#{WeChat}");
                SET("phone=#{phone}");
                WHERE("author=#{oldname}");

            }
        }.toString();
    }
}
