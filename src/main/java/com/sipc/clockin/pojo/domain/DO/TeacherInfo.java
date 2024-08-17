package com.sipc.clockin.pojo.domain.DO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TeacherInfo {
    private String name;
    @JsonProperty("work_id")
    private int workId;
    private String email;
    @JsonProperty("college_id")
    private int collegeId;
    @JsonProperty("class_ids")
    private int[] classIds;
}
