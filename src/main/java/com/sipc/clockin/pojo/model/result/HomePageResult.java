package com.sipc.clockin.pojo.model.result;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HomePageResult {
    @JsonProperty("class_id")
    private Integer classId;
    private String className;
    @JsonProperty("student_num")
    private Integer studentNum;
    @JsonIgnore
    private Integer messageId;
    @TableField(exist = false)
    private Integer completionNum;
    @TableField(exist = false)
    private List<StudentClockDetail> classClock;
}
