package cn.ssm.right.service;

import cn.ssm.right.mapper.LoginMapper;
import cn.ssm.right.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    private LoginMapper loginMapper;
    @Autowired
    public void setUserInfoMapper(LoginMapper loginMapper){
        this.loginMapper = loginMapper;
    }
    @Override
    public User Login(User user) {
        return loginMapper.Login(user);
    }

    @Override
    public int isexists(String login_name) {
        int i = loginMapper.isexists(login_name);
        return i;
    }

    @Override
    public void insertLogin(User user) {
        loginMapper.insertLogin(user);
    }

    @Override
    public void updateLogin(User user) {
        loginMapper.updateLogin(user);
    }

}
