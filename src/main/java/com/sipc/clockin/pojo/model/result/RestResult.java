package com.sipc.clockin.pojo.model.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestResult {
    public Integer workId;
    private String name;
    private String dormitory;
    private String reason;
}
