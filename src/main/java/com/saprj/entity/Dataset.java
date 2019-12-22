package com.saprj.entity;

import lombok.Data;

@Data
public class Dataset {
    private int did;
    //数据集名称
    private String name;
    //数据集类型
    private int type;
    //数据集描述
    private String description;
    //数据集大小
    private long size;
    //数据集上传者
    private int uid;
    //数据集标签
    private String tags;
    //数据集下载地址
    private String url;
    //下载次数
    private int downnum;
    //图片
    private String img;
}
