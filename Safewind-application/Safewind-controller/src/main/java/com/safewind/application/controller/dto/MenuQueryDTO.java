package com.safewind.application.controller.dto;

import com.safewind.common.page.Page;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author: Darven
 * @createTime: 2025-06-27  13:41
 * @description: 查询分业
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MenuQueryDTO extends Page {
    /**
     * 菜单名称
     */
    private String menuName;
    /**
     * 菜单状态（0正常 1停用）
     */
    private String status;
}
