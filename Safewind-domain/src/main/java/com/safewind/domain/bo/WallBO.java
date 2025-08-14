package com.safewind.domain.bo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author: Darven
 * @createTime: 2025-08-13  00:13
 * @description: 弹幕墙业务对象
 */
@Data
public class WallBO {
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
