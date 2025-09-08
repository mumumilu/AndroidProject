package cn.ssm.right.controller;

import cn.ssm.right.http.Results;
import cn.ssm.right.po.Goodsinfo;
import cn.ssm.right.po.User;
import cn.ssm.right.service.GoodsService;
import cn.ssm.right.service.LoginService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.FileImageOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class goodsController {
    GoodsService goodsService;
    @Autowired
    public void setGoodsService(GoodsService goodsService){
        this.goodsService=goodsService;
    }

    @RequestMapping(value = "/addLost",method = RequestMethod.POST)
    @ResponseBody
    public  void  addLost(HttpServletResponse response, HttpServletRequest request) throws Exception {
        response.setContentType("text/html;charset=utf-8");

//        System.out.println("=======安卓客户端连接服务器成功=============");
//        System.out.println(request.getParameter("address"));
//        System.out.println(request.getParameter("phone"));
//        System.out.println(request.getParameter("desc"));
//        System.out.println(request.getParameter("wechat"));
//        System.out.println(request.getParameter("good_name"));
//        System.out.println(request.getParameter("user_name"));

        Goodsinfo goodsinfo = new Goodsinfo();
        goodsinfo.setPath(request.getParameter("address").trim());
        goodsinfo.setPhone(request.getParameter("phone").trim());
        goodsinfo.setContent(request.getParameter("desc").trim());
        goodsinfo.setWeChat(request.getParameter("wechat").trim());
        goodsinfo.setTitle(request.getParameter("good_name"));
        goodsinfo.setAuthor(request.getParameter("user_name").trim());

        goodsService.insertLoss(goodsinfo);
        PrintWriter out=response.getWriter();
        out.println("200");

    }
    @RequestMapping(value = "/addPick",method = RequestMethod.POST)
    @ResponseBody
    public  void  addPick(HttpServletResponse response, HttpServletRequest request) throws IOException, IOException {
        response.setContentType("text/html;charset=utf-8");

        System.out.println("=======安卓客户端连接服务器成功=============");
        Goodsinfo goodsinfo = new Goodsinfo();
        goodsinfo.setPath(request.getParameter("place").trim());
        goodsinfo.setPhone(request.getParameter("phone").trim());
        goodsinfo.setWeChat(request.getParameter("wechat").trim());
        goodsinfo.setTitle(request.getParameter("good_name"));//物品名称
        goodsinfo.setAuthor(request.getParameter("user_name").trim());//发布人
        goodsinfo.setContent(request.getParameter("desc").trim());

        goodsService.insertPick(goodsinfo);
        PrintWriter out=response.getWriter();
        out.println("200");

    }
    @RequestMapping(value = "/getLostAll",method = RequestMethod.POST)
    @ResponseBody
    public  void  getLostAll(HttpServletResponse response, HttpServletRequest request) throws IOException, IOException, ParseException {
        response.setContentType("text/html;charset=utf-8");

        System.out.println("=======安卓客户端连接服务器成功=============");
        List<Goodsinfo> list = new ArrayList<Goodsinfo>();
        list = goodsService.getLostAll();
        for(int i=0;i<list.size();i++){
            list.get(i).setTime(dateToString(list.get(i).getCreattime(),"yyyy-MM-dd'T'HH:mm:ss"));
            byte[] bit = list.get(i).getPhoto();
            String photo1 = DatatypeConverter.printBase64Binary(bit);
//            String base64 = Base64.getEncoder().encodeToString(list.get(i).getPhoto());
            list.get(i).setPhoto1(photo1);
            list.get(i).setPhoto(null);

        }
        String str= JSON.toJSON(list).toString();
        System.out.println(str);
        PrintWriter out=response.getWriter();
        out.println(str);

    }
    @RequestMapping(value = "/getLostAllByName",method = RequestMethod.POST)
    @ResponseBody
    public  void  getLostAllByName(HttpServletResponse response, HttpServletRequest request) throws IOException, IOException, ParseException {
        response.setContentType("text/html;charset=utf-8");

        System.out.println("=======安卓客户端连接服务器成功=============");
        List<Goodsinfo> list = new ArrayList<Goodsinfo>();
        list = goodsService.getLostAllByName(request.getParameter("username").trim());
        for(int i=0;i<list.size();i++){
            list.get(i).setTime(dateToString(list.get(i).getCreattime(),"yyyy-MM-dd'T'HH:mm:ss"));
            list.get(i).setPhoto(null);
        }
        String str= JSON.toJSON(list).toString();
        System.out.println(str);
        PrintWriter out=response.getWriter();
        out.println(str);

    }
    @RequestMapping(value = "/getPickAllByName",method = RequestMethod.POST)
    @ResponseBody
    public  void  getPickAllByName(HttpServletResponse response, HttpServletRequest request) throws IOException, IOException, ParseException {
        response.setContentType("text/html;charset=utf-8");

        System.out.println("=======安卓客户端连接服务器成功=============");
        List<Goodsinfo> list = new ArrayList<Goodsinfo>();
        list = goodsService.getPickAllByName(request.getParameter("username").trim());
        for(int i=0;i<list.size();i++){
            list.get(i).setTime(dateToString(list.get(i).getCreattime(),"yyyy-MM-dd'T'HH:mm:ss"));

            list.get(i).setPhoto(null);
        }
        String str= JSON.toJSON(list).toString();
        System.out.println(str);
        PrintWriter out=response.getWriter();
        out.println(str);

    }
    @RequestMapping(value = "/getPickAll",method = RequestMethod.POST)
    @ResponseBody
    public  void  getPickAll(HttpServletResponse response, HttpServletRequest request) throws IOException, IOException, ParseException {
        response.setContentType("text/html;charset=utf-8");
        System.out.println("=======安卓客户端连接服务器成功=============");
        List<Goodsinfo> list = new ArrayList<Goodsinfo>();

        list = goodsService.getPickAll();
        for(int i=0;i<list.size();i++){
            list.get(i).setTime(dateToString(list.get(i).getCreattime(),"yyyy-MM-dd'T'HH:mm:ss"));
            byte[] bit = list.get(i).getPhoto();
            String photo1 = DatatypeConverter.printBase64Binary(bit);
//            String base64 = Base64.getEncoder().encodeToString(list.get(i).getPhoto());
            list.get(i).setPhoto1(photo1);
            list.get(i).setPhoto(null);
        }
        String str= JSON.toJSON(list).toString();
        System.out.println(str);
        PrintWriter out=response.getWriter();
        out.println(str);

    }

    public static String dateToString(Date data, String formatType) {
        return new SimpleDateFormat(formatType).format(data);
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

    //byte数组到图片
    public static void byte2image(byte[] data, String path){
        if(data.length<3||path.equals("")) return;
        try{
            FileImageOutputStream imageOutput = new FileImageOutputStream(new File(path));
            imageOutput.write(data, 0, data.length);
            imageOutput.close();
            System.out.println("Make Picture success,Please find image in " + path);
        } catch(Exception ex) {
            System.out.println("Exception: " + ex);
            ex.printStackTrace();
        }
    }
}
