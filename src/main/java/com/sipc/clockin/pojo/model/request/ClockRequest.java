package com.sipc.clockin.pojo.model.request;

import cn.hutool.core.date.DateTime;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClockRequest {
    @JsonProperty("student_id")
    private Integer studentId;
    private DateTime time;
    private Integer type;
    private String reason;
}
