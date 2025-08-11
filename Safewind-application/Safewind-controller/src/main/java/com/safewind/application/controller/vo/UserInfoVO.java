package com.safewind.application.controller.vo;

import lombok.Builder;
import lombok.Data;

/**
 * @author: Darven
 * @createTime: 2025-08-09  12:09
 * @description: 用户详细信息
 */
@Data
@Builder
public class UserInfoVO {
    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 头像，程序层默认一张图
     */
    private String avatar;

    /**
     * 年级，形如 '2025' 的字符串
     */
    private String grade;

    /**
     * 专业
     */
    private String speciality;

    /**
     * 学院
     */
    private String faculty;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别，"1，男"，"0，女"
     */
    private Integer sex;

    /**
     * 班级
     */
    private String className;
}
