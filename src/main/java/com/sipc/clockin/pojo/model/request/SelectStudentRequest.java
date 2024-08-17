package com.sipc.clockin.pojo.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SelectStudentRequest {
    @JsonProperty("class_id")
    private int classId;
}
