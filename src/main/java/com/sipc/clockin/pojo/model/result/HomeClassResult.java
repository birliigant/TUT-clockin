package com.sipc.clockin.pojo.model.result;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HomeClassResult {
    private String name;
    private String dormitory;
    private Integer type;
}
