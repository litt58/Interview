package com.jzli.face;

import cn.hutool.core.io.FileUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import org.junit.Test;

import java.io.File;

/**
 * =======================================================
 *
 * @Company 产品技术部
 * @Date ：2018/5/23
 * @Author ：李金钊
 * @Version ：0.0.1
 * @Description ：
 * ========================================================
 */
public class FacePlusPlusTest {
    private String idCard = "https://api-cn.faceplusplus.com/cardpp/v1/ocridcard";
    private String detect="https://api-cn.faceplusplus.com/facepp/v3/detect";
    private String appKey = "tBncqQU1X45wY66BAedTX9acTLhBbr-w";
    private String appSecret = "sd1GDfSz4azXA9qE4hvOaOBz3xYmerr7";


    public void execute(String filePath,String url){
        File file = FileUtil.file(filePath);
        HttpRequest request = HttpRequest.post(url)
                .form("image_file",file)
                .form("api_key",appKey)
                .form("api_secret",appSecret)
                .form("return_attributes","gender,age,smiling,headpose,facequality,blur,eyestatus,emotion,ethnicity,beauty,mouthstatus,eyegaze,skinstatus");
        HttpResponse response = request.execute();
        System.out.println(response.body());
    }

    @Test
    public void idCard(){
        String filePath="D:/2.jpg";
        execute(filePath,idCard);
    }

    @Test
    public void detect(){
        String filePath="D:/1.png";
        execute(filePath,detect);
    }

}
