package com.sipc.clockin.pojo.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ManageRequest {
    @JsonProperty("class_id")
    private int classId;
    @JsonProperty("student_ids")
    private int[] studentIds;
}
