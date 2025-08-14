package com.safewind.infra.basic.entity;

import lombok.Data;

/**
 * @author: Darven
 * @createTime: 2025-08-13  00:23
 * @description: 弹幕墙查询实体
 */
@Data
public class WsQueryWall {
    /**
     * 用户名字（模糊查询）
     */
    private String name;

    /**
     * 弹幕内容（模糊查询）
     */
    private String content;
}
