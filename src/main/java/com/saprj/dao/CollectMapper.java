package com.saprj.dao;

import com.saprj.entity.Collect;
import com.saprj.entity.Dataset;

import java.util.List;

public interface CollectMapper {
    void insert(Collect collect);

    List<Dataset> selectByUid(int uid);

    void delete(Collect collect);
}
