package com.safewind.application.controller.dto;

import com.safewind.common.page.Page;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author: Darven
 * @createTime: 2025-08-13  00:10
 * @description: 弹幕墙查询数据传输对象
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WallQueryDTO extends Page {
    /**
     * 用户名字（模糊查询）
     */
    private String name;

    /**
     * 弹幕内容（模糊查询）
     */
    private String content;
}
