package com.saprj.controller;

import com.saprj.entity.Dataset;
import com.saprj.entity.User;
import com.saprj.service.DatasetServicer;
import com.saprj.util.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@CrossOrigin
@RequestMapping(value = "dataset")
public class DatesetController {
    @Autowired
    private DatasetServicer datasetServicer;

    //数据集上传
    @ResponseBody
    @RequestMapping(value = "upload",method = RequestMethod.POST)
    public ResponseData upload(
            User user,
            @RequestParam(value = "imgFile") MultipartFile cover,
            @RequestParam(value = "content") MultipartFile content,
            Dataset dataset){
        datasetServicer.upload(user,cover,content,dataset);
        return ResponseData.ok();
    }

    //数据集下载
    @RequestMapping(value = "download",method = RequestMethod.GET)
    public void download(
            User user,
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "did") int did) {
        datasetServicer.download(request,response,user,did);
    }

    //按照关键字搜索数据集
    @ResponseBody
    @RequestMapping(value = "search",method = RequestMethod.GET)
    public ResponseData search(User user,String keyWord){
        List<Dataset> datasets = datasetServicer.search(keyWord,user.getUid());
        ResponseData responseData = ResponseData.ok();
        responseData.putDataValue("datasets",datasets);
        return responseData;
    }

    //收藏数据集
    @ResponseBody
    @RequestMapping(value = "collect",method = RequestMethod.POST)
    public ResponseData collect(User user,int did){
        datasetServicer.collect(user,did);
        return ResponseData.ok();
    }

    //取消收藏
    @ResponseBody
    @RequestMapping(value = "uncollect",method = RequestMethod.POST)
    public ResponseData uncollect(User user,int did){
        datasetServicer.uncollect(user,did);
        return ResponseData.ok();
    }

    //根据id获取对应数据集
    @ResponseBody
    @RequestMapping(value = "getDSById",method = RequestMethod.GET)
    public ResponseData getDSById(User user,int did){
        ResponseData responseData = ResponseData.ok();
        responseData.putDataValue("dataset",datasetServicer.getDSById(did,user.getUid()));
        return responseData;
    }

}
