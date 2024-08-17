package com.sipc.clockin.pojo.model.result;

import cn.hutool.core.date.DateTime;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HomePageResult {
    @JsonProperty("class_id")
    private Integer classId;
    private String name;
    @JsonIgnore
    @JsonProperty("message_id")
    private Integer messageId;
    @JsonProperty("start_time")
    private DateTime startTime;
    @JsonProperty("end_time")
    private DateTime endTime;
    @JsonProperty("student_num")
    private Integer studentNum;
    private List<HomeClassResult> homeClassResults;
}
