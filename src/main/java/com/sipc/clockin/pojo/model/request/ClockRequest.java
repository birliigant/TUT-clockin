package com.sipc.clockin.pojo.model.request;

import cn.hutool.core.date.DateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClockRequest {
    private Integer studentId;
    private Integer classId;
    private Integer messageId;
    private DateTime time;
    private String type;
    private String reason;
}
