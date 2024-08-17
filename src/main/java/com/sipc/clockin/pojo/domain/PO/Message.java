package com.sipc.clockin.pojo.domain.PO;

import cn.hutool.core.date.DateTime;
import lombok.Data;

import java.util.Date;

@Data
public class Message {
    private Integer id;
    private String location;
    private Date createTime;
    private Date startTime;
    private Date endTime;
}
