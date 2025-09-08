package cn.ssm.right.service;


import cn.ssm.right.po.User;

public interface LoginService {
    User Login(User user);//判断用户密码
    int isexists(String login_name);//注册时判断用户是否存在
    void insertLogin(User user);//新增用户
    void updateLogin(User user);//更新用户信息

}
