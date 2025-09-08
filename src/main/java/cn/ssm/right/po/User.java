package cn.ssm.right.po;
import lombok.Data;

@Data
public class User {
    private String login_name;
    private String password;
    private String phone;
    private String path;
    private String WeChat;
    private String oldname;
}
