package com.sipc.clockin.pojo.model.result;

import lombok.Data;

@Data
public class TokenResult {
    public String token;
    private String role;
}
