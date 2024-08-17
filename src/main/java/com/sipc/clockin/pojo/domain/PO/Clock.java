package com.sipc.clockin.pojo.domain.PO;

import cn.hutool.core.date.DateTime;
import lombok.Data;

@Data
public class Clock{
    private Integer id;
    private Integer message_id;
    private Integer student_id;
    private Integer class_id;
    private String type;
    private Integer is_pass;
    private DateTime time;
    private Integer is_read;
    private Integer is_agreed;
    private String reason;
}
