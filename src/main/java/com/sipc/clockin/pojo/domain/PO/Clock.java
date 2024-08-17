package com.sipc.clockin.pojo.domain.PO;

import cn.hutool.core.date.DateTime;
import lombok.Data;

import java.util.Date;

@Data
public class Clock{
    private Integer id;
    private Integer messageId;
    private Integer studentId;
    private Integer classId;
    private String type;
    private Integer isPass;
    private Date time;
    private Integer isRead;
    private Integer isAgreed;
    private String reason;
}
