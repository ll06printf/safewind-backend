package com.safewind.application.controller.vo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author: Darven
 * @createTime: 2025-08-01  18:41
 * @description: 官网弹幕墙展示实体
 */
@Data
public class WsWallVO {
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

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
