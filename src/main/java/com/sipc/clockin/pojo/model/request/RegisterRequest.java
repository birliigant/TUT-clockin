package com.sipc.clockin.pojo.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RegisterRequest {
    private String name;
    @JsonProperty("work_id")
    private Integer workId;
    private String email;
    private String password;
    private String code;
    @JsonProperty("is_remembered")
    private Boolean isRemembered;
    @JsonProperty("is_agreed")
    private Boolean isAgreed;
}
