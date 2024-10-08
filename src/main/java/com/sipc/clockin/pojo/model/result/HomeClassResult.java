package com.sipc.clockin.pojo.model.result;

import cn.hutool.core.date.DateTime;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HomeClassResult {

    private Integer workId;
    private String name;
    private String dormitory;

    @JsonProperty("message_id")
    private Integer messageId;
    @JsonProperty("start_time")
    private DateTime startTime;
    @JsonProperty("end_time")
    private DateTime endTime;
    private Integer type;
    private String reason;
}
