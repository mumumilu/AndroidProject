package cn.ssm.right.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
@Data
public class Goodsinfo {
    private String title;
    private String phone;
    private String author;
    private String content;
    private String WeChat;
    private String path;
    private String oldname;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date creattime;
    private byte[] photo;
    private String photo1;
    private String time;
}
