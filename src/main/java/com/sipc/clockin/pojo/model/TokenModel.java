package com.sipc.clockin.pojo.model;

import lombok.Data;

@Data
public class TokenModel {
    private Integer workId;
    private String email;
    private String name;
    private String role;
}
