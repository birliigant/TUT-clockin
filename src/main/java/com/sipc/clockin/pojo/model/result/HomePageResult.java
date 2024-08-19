package com.sipc.clockin.pojo.model.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HomePageResult {
    private Integer classId;
    private String className;
    private Integer studentNum;
    private Integer messageId;
    private Integer completionNum;
    private List<StudentClockDetail> classClock;
}
