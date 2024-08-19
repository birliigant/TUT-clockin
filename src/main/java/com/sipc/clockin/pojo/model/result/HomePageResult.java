package com.sipc.clockin.pojo.model.result;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HomePageResult {
    private Integer classId;
    private String className;
    private Integer studentNum;
    private Integer messageId;
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
    private Integer completionNum;
    private List<StudentClockDetail> classClock;
}
