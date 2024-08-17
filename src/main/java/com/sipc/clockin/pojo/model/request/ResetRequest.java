package com.sipc.clockin.pojo.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ResetRequest {
    private String email;
    private String code;
    private String password;
}