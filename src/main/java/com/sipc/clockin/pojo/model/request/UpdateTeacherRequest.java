package com.sipc.clockin.pojo.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UpdateTeacherRequest {
    @JsonProperty("college_id")
    private int collegeId;
    private String icon;
}
