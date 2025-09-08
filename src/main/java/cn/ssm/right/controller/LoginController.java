package cn.ssm.right.controller;

import cn.ssm.right.po.Goodsinfo;
import cn.ssm.right.po.User;
import cn.ssm.right.service.GoodsService;
import cn.ssm.right.service.LoginService;
import com.alibaba.fastjson.JSONObject;
import com.sun.istack.internal.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;

@Controller
public class LoginController {
    LoginService loginService;
    @Autowired
    public void setLoginService(LoginService loginService){
        this.loginService=loginService;
    }
    GoodsService goodsService;
    @Autowired
    public void setGoodsService(GoodsService goodsService){
        this.goodsService=goodsService;
    }


    @RequestMapping(value = "/androidLogin",method = RequestMethod.POST)
    @ResponseBody
    public  void  androidLogin(HttpServletResponse response, HttpServletRequest request) throws Exception {
        response.setContentType("text/html;charset=utf-8");
        String userName = request.getParameter("username").trim();
        String passWord = request.getParameter("password").trim();
        System.out.println("=======安卓客户端连接服务器成功=============");
        PrintWriter out=response.getWriter();
        User user = new User();
        user.setLogin_name(userName);

//        //创建文件输入流
//        FileInputStream fis = new FileInputStream("F://a//xiangji.jpg");
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        BufferedImage read = ImageIO.read(fis);
//        ImageIO.write(read, "jpg", baos);
//        //将目标文件转换成byte数组
//        byte[] bytes = baos.toByteArray();
//        Goodsinfo goodsinfo = new Goodsinfo();
//        goodsinfo.setPhoto(bytes);
//        goodsService.insertphoto(goodsinfo);

        User user1 = loginService.Login(user);
        if(user1!=null&&user1.getPassword().equals(passWord)) {
            String json = JSONObject.toJSONString(user1);
            out.println(json);
            System.out.println("android端用户登陆成功");
        }
        else {
            out.println("300");
//            System.out.println("登录失败");
        }
    }


    @RequestMapping(value = "/androidRegister",method = RequestMethod.POST)
    @ResponseBody
    public  void  androidRegister( HttpServletResponse response, HttpServletRequest request) throws IOException, IOException {
        response.setContentType("text/html;charset=utf-8");
        String userName = request.getParameter("username").trim();
        String passWord = request.getParameter("password").trim();
        String phone = request.getParameter("phone").trim();
        String wechat = request.getParameter("wechat").trim();
        String address = request.getParameter("address").trim();

        System.out.println("=======安卓客户端连接服务器成功=============");
        PrintWriter out=response.getWriter();
        int i = loginService.isexists(userName);
        System.out.println(i);

        if(i==0&&userName.equals("")==false&&passWord.equals("")==false){
            User user = new User();
            user.setPassword(passWord);
            user.setPath(address);
            user.setPhone(phone);
            user.setWeChat(wechat);
            user.setLogin_name(userName);
            loginService.insertLogin(user);
            out.println("200");
            System.out.println("android端用户注册成功");
        }else{
            out.println("300");
            System.out.println("注册失败");
        }
    }


    @RequestMapping(value = "/androidUpdate",method = RequestMethod.POST)
    @ResponseBody
    public  void  androidUpdate(HttpServletResponse response, HttpServletRequest request,Model model) throws IOException, IOException {
        response.setContentType("text/html;charset=utf-8");
        String userName = request.getParameter("username").trim();
        String passWord = request.getParameter("password").trim();
        String phone = request.getParameter("phone").trim();
        String wechat = request.getParameter("wechat").trim();
        String address = request.getParameter("address").trim();
        String oldname = request.getParameter("oldname").trim();
        System.out.println("=======安卓客户端连接服务器成功=============");
        PrintWriter out=response.getWriter();
        if(userName.equals("")==false&&passWord.equals("")==false){
            Goodsinfo goodsinfo =new Goodsinfo();
            goodsinfo.setAuthor(userName);
            goodsinfo.setPhone(phone);
            goodsinfo.setWeChat(wechat);
            goodsinfo.setOldname(oldname);

            User user = new User();
            user.setPassword(passWord);
            user.setPath(address);
            user.setPhone(phone);
            user.setWeChat(wechat);
            user.setLogin_name(userName);
            user.setOldname(oldname);
            loginService.updateLogin(user);
            goodsService.updateName(goodsinfo);
            out.println("200");

        }else{
            out.println("300");
        }
//        int i = loginService.isexists(userName);
//        System.out.println(i);

//        if(i==0&&userName.equals("")==false&&passWord.equals("")==false){
//            User user = new User();
//            user.setPassword(passWord);
//            user.setPath(address);
//            user.setPhone(phone);
//            user.setWeChat(wechat);
//            user.setLogin_name(userName);
//            loginService.insertLogin(user);
//            out.println("注册成功！请登录");
//            System.out.println("android端用户注册成功");
//        }else{
//            out.println("注册失败！重新注册");
//            System.out.println("注册失败");
//        }
//
    }

    /**
     * @param imgPath
     * @return
     * @Title: GetImageStrFromPath
     * @Description: (将一张本地图片转化成Base64字符串)
     */
    public static String GetImageStrFromPath(String imgPath) {
        InputStream in = null;
        byte[] data = null;
        // 读取图片字节数组
        try {
            in = new FileInputStream(imgPath);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        // 返回Base64编码过的字节数组字符串
        return encoder.encode(data);
    }

    public static byte[] getBytesByFile(File file) {
        try {
            //获取输入流
            FileInputStream fis = new FileInputStream(file);

            //新的 byte 数组输出流，缓冲区容量1024byte
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1024);
            //缓存
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            //改变为byte[]
            byte[] data = bos.toByteArray();
            //
            bos.close();
            return data;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static byte[] fileToByte(File img) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] bytes;
        try {
            BufferedImage bi;
            bi = ImageIO.read(img);
            ImageIO.write(bi, "jpg", baos);
            bytes = baos.toByteArray();
            System.err.println(bytes.length);
            return bytes;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            baos.close();

        }

    }
    //图片到byte数组
    public byte[] image2byte(String path){
        byte[] data = null;
        FileImageInputStream input = null;
        try {
            input = new FileImageInputStream(new File(path));
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int numBytesRead = 0;
            while ((numBytesRead = input.read(buf)) != -1) {
                output.write(buf, 0, numBytesRead);
            }
            data = output.toByteArray();
            output.close();
            input.close();
        }
        catch (FileNotFoundException ex1) {
            ex1.printStackTrace();
        }
        catch (IOException ex1) {
            ex1.printStackTrace();
        }
        return data;
    }

}
