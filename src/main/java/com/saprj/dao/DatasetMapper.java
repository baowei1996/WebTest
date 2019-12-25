package com.saprj.dao;

import com.saprj.entity.Dataset;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DatasetMapper {
    Dataset selectByPrimaryKey(@Param(value = "did")int did,@Param(value = "uid")int uid);

    void insert(Dataset dataset);

    void insertSelective(Dataset dataset);

    List<Dataset> select(@Param(value = "keyWord") String keyWord, @Param(value = "uid") int uid);

    void updateByPrimaryKeySelective(Dataset dataset);
}
