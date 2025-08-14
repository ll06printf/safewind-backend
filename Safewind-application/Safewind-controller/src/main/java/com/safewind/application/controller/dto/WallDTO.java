package com.safewind.application.controller.dto;

import lombok.Data;

/**
 * @author: Darven
 * @createTime: 2025-08-13  00:09
 * @description: 弹幕墙数据传输对象
 */
@Data
public class WallDTO {
    /**
     * 弹幕ID
     */
    private Long id;

    /**
     * 用户名字
     */
    private String name;

    /**
     * 弹幕内容
     */
    private String content;

    /**
     * 用户头像
     */
    private String avatar;
}
