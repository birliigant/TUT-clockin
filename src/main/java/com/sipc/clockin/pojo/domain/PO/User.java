package com.sipc.clockin.pojo.domain.PO;

import lombok.Data;

import java.util.Date;

@Data
public class User{
    private Integer id;
    private String name;
    private Integer workId;
    private String email;
    private String password;
    private Integer collegeId;
    private Integer majorId;
    private Integer classId;
    private Integer grade;
    private String dormitory;
    private String icon;
    private Integer role;
    private String classIds;
    private Date createTime;
}