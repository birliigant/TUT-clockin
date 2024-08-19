package com.sipc.clockin.pojo.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author 王泽
 */
@Data
public class UpdateStudentRequest {
    private Integer grade;
    @JsonProperty(value = "college_id")
    private Integer collegeId;
    @JsonProperty(value = "major_id")
    private Integer majorId;
    @JsonProperty(value = "class_id")
    private Integer classId;
    private String dormitory;
}
