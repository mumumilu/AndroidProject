package cn.ssm.right.mapper;

import cn.ssm.right.po.User;
import org.apache.ibatis.jdbc.SQL;

public class LoginSql {
    public String verify(final User user ){
        return new SQL(){
            {
               SELECT("*");
               FROM("userinfo");
               WHERE("WeChat=#{login_name}");
            }
        }.toString();
    }

    public String isexists(final String login_name){
        return new SQL(){
            {
                SELECT("count(*)");
                FROM("userinfo");
                WHERE("login_name=#{login_name}");
            }
        }.toString();
    }
    public String insertLogin(final User user){
        return new SQL(){
            {
                INSERT_INTO("userinfo");
                VALUES("login_name","#{login_name}");

                VALUES("password","#{password}");
                VALUES("phone","#{phone}");
                VALUES("path","#{path}");
                VALUES("WeChat","#{WeChat}");
            }
        }.toString();
    }
    public String updateLogin(final User user){
        return new SQL(){
            {
                UPDATE("userinfo");
                SET("login_name=#{login_name}");
                SET("password=#{password}");
                SET("phone=#{phone}");
                SET("path=#{path}");
                SET("WeChat=#{WeChat}");
                WHERE("login_name=#{oldname}");
            }
        }.toString();
    }
}
