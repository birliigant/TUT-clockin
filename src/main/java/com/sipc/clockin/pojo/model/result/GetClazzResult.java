package com.sipc.clockin.pojo.model.result;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetClazzResult {
    @JsonProperty("class_id")
    private Integer clazzId;
    @JsonProperty("class_name")
    private String clazzName;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private boolean isResponsible;
}
