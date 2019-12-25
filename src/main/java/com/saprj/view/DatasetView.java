package com.saprj.view;

import com.saprj.entity.Dataset;
import lombok.Data;

import java.util.Date;

@Data
public class DatasetView extends Dataset {
    private Date time;
}
