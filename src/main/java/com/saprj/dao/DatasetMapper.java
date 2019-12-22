package com.saprj.dao;

import com.saprj.entity.Dataset;

import java.util.List;

public interface DatasetMapper {
    Dataset selectByPrimaryKey(int did);

    void insert(Dataset dataset);

    void insertSelective(Dataset dataset);

    List<Dataset> select(String keyWord);

    void updateByPrimaryKeySelective(Dataset dataset);
}
