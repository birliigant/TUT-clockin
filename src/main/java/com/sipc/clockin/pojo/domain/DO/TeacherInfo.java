package com.sipc.clockin.pojo.domain.DO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class TeacherInfo {
    private String name;
    @JsonProperty("work_id")
    private int workId;
    private String email;
    @JsonProperty("college_name")
    private String collegeName;
    @JsonProperty("class_names")
    private List<String> classNames;
}
