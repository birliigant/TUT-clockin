package com.sipc.clockin.pojo.domain.PO;

import cn.hutool.core.date.DateTime;
import lombok.Data;

@Data
public class Message {
    private Integer id;
    private String location;
    private DateTime createTime;
    private DateTime startTime;
    private DateTime endTime;
}
