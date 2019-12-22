package com.saprj.util;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

public class FileUtil {

    //TODO 保存文件
    public static String uploadFile(MultipartFile content,String subPath) {
        //定义上传文件服务器地址
        String path = "http://localhost:9090/File_Server_war/"+subPath+"/";
        //获取文件的名字
        String fileName = content.getOriginalFilename();
        //把文件的名字设置成唯一值
        String uuid = UUID.randomUUID().toString().replace("-","");
        fileName = uuid+"_"+fileName;
        // 创建客户端对象
        Client client =Client.create();
        //和图片服务器连接
        WebResource resource = client.resource(path+fileName);
        //上传文件
        try {
            resource.put(content.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return path+fileName;
    }
}
