package com.sipc.clockin.pojo.domain.PO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Clock{
    private Integer id;
    private Integer messageId;
    private Integer studentId;
    private Integer classId;
    private String type;
    private Integer isPass;
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date time;
    private Integer isRead;
    private Integer isAgreed;
    private String reason;
}
