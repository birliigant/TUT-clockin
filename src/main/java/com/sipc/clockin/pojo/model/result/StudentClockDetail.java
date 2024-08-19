package com.sipc.clockin.pojo.model.result;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class StudentClockDetail {
    private Integer workId;
    private String userName;
    private String dormitory;
    @JsonIgnore
    private Integer classId;
    @JsonIgnore
    private String className;
    @JsonIgnore
    private Integer studentNum;
    @JsonIgnore
    private Integer messageId;
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonIgnore
    private Date startTime;
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonIgnore
    private Date endTime;
    private Integer clockId;
    private String type;
    private Integer isPass;
    private String reason;

}
