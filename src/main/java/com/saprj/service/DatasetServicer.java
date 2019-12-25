package com.saprj.service;

import com.saprj.dao.CollectMapper;
import com.saprj.dao.DatasetMapper;
import com.saprj.entity.Collect;
import com.saprj.entity.Dataset;
import com.saprj.entity.User;
import com.saprj.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

@Service
public class DatasetServicer {
    @Autowired
    private DatasetMapper datasetMapper;
    @Autowired
    private CollectMapper collectMapper;

    //收藏数据集
    public void collect(User user,int did){
        Collect collect = new Collect();
        collect.setTime(new Date());
        collect.setDid(did);
        collect.setUid(user.getUid());
        collectMapper.insert(collect);
    }

    public void uncollect(User user, int did) {
        Collect collect = new Collect();
        collect.setUid(user.getUid());
        collect.setDid(did);
        collectMapper.delete(collect);
    }

    public List<Dataset> search(String keyWord,int uid) {
        String kw = keyWord == ""?null:keyWord;
        return datasetMapper.select(kw,uid);
    }

    public void upload(User user, MultipartFile cover, MultipartFile content, Dataset dataset) {
        //存文件
        String imgPath = FileUtil.uploadFile(cover,"img");
        String filePath = FileUtil.uploadFile(content,"dataset");
        //存数据库
        dataset.setSize(content.getSize());
        dataset.setImg(imgPath);
        dataset.setUid(user.getUid());
        dataset.setUrl(filePath);
        dataset.setDownnum(0);
        datasetMapper.insertSelective(dataset);
    }

    public void download(HttpServletRequest request, HttpServletResponse response, User user, int did) {
        //TODO do something
        Dataset dataset = datasetMapper.selectByPrimaryKey(did);
        //更新下载数
        dataset.setDownnum(dataset.getDownnum()+1);
        datasetMapper.updateByPrimaryKeySelective(dataset);
        String url = dataset.getUrl();
        String filename = url.substring(url.indexOf('_'));
        try{
            response.setContentType("application/x-download");
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));
            //读取要下载的文件，保存到文件输入流
            InputStream in = new URL(url).openConnection().getInputStream();
            //创建输出流，注意这里使用的是response创建的输出流
            OutputStream out = response.getOutputStream();
            //创建缓冲区
            byte buffer[] = new byte[1024];
            int len = 0;
            //循环将输入流中的内容读取到缓冲区当中
            while((len=in.read(buffer))>0){
                //输出缓冲区的内容到浏览器，实现文件下载
                out.write(buffer, 0, len);
            }
            out.flush();
            out.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Dataset getDSById(int did) {
        return datasetMapper.selectByPrimaryKey(did);
    }
}
