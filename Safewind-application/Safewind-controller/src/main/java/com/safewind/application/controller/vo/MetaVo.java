package com.safewind.application.controller.vo;

import lombok.Data;

/**
 * @author: Darven
 * @createTime: 2025-06-28  02:05
 * @description: 路由显示元素
 */
@Data
public class MetaVo {
    /**
     * 设置该路由在侧边栏和面包屑中展示的名字
     */
    private String title;

    /**
     * 设置该路由的图标，对应路径src/assets/icons/svg
     */
    private String icon;

    /**
     * 设置为true，则不会被 <keep-alive>缓存
     */
    private boolean noCache;

    /**
     * 内链地址（http(s)://开头）
     */
    private String link;
}
