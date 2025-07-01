package com.safewind.application.controller.vo;

import lombok.Data;

/**
 * @author: Darven
 * @createTime: 2025-06-28  02:04
 * @description: 前端路由VO
 */
@Data
public class RouterVO {
    /**
     * 路由名字(name: "Apply")
     */
    private String name;

    /**
     * 路由地址(path: "apply")
     */
    private String path;

    /**
     * 是否隐藏路由，当设置 true 的时候该路由不会再侧边栏出现
     */
    private boolean hidden;

    /**
     * 重定向地址，当设置 noRedirect 的时候该路由在面包屑导航中不可被点击
     */
    private String redirect;

    /**
     * 组件地址(component: () => import("@/views/apply/index.vue"))
     */
    private String component;

    /**
     * 路由参数：如 {"id": 1, "name": "ry"}
     */
    private String query;

    /**
     * 当你一个路由下面的 children 声明的路由大于1个时，自动会变成嵌套的模式--如组件页面
     */
    private Boolean alwaysShow;

    /**
     * 其他元素
     */
    private MetaVo meta;
}
