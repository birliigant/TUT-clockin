package com.sipc.clockin.pojo.domain.DO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserSimple {
    @JsonProperty("work_id")
    private int workId;
    private String name;
}
