package com.sipc.clockin.pojo.domain.PO;

import lombok.Data;

@Data
public class MessageToClass{
    private Integer id;
    private Integer messageId;
    private Integer classId;
    private Integer isDeleted;
}