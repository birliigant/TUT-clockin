package com.sipc.clockin.pojo.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LoginRequest {
    @JsonProperty("work_id")
    private Integer workId;
    private String password;
    @JsonProperty("is_remembered")
    private Boolean isRemembered;
}
