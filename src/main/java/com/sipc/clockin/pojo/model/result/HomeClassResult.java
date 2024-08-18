package com.sipc.clockin.pojo.model.result;

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
    private Integer type;
    private String reason;
}
