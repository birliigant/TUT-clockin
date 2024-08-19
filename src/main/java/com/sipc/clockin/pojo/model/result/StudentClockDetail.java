package com.sipc.clockin.pojo.model.result;

import cn.hutool.core.date.DateTime;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentClockDetail {
    private Integer workId;
    private String name;
    private String dormitory;
    @JsonIgnore
    private Integer classId;
    @JsonIgnore
    private String className;
    @JsonIgnore
    private Integer num;
    @JsonIgnore
    private Integer messageId;
    @JsonIgnore
    private DateTime startTime;
    @JsonIgnore
    private DateTime endTime;
    private Integer type;
    private String reason;

}
