package cn.ssm.right.mapper;

import cn.ssm.right.po.User;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.springframework.stereotype.Repository;


@Repository
public interface LoginMapper {
    @SelectProvider(type = LoginSql.class, method = "verify")
    User Login(User user);//判断用户密码

    @SelectProvider(type = LoginSql.class, method = "isexists")
    int isexists(String login_name);//注册时判断用户是否存在

    @InsertProvider(type = LoginSql.class,method = "insertLogin")
    void insertLogin(User user);//新增用户

    @UpdateProvider(type = LoginSql.class,method = "updateLogin")
    void updateLogin(User user);//更新用户信息


}
