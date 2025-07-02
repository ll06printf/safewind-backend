package com.safewind.domain.bo;

import lombok.Data;

import java.util.List;

/**
 * @author: Darven
 * @createTime: 2025-07-02  16:33
 * @description: 用户角色关联
 */
@Data
public class RoleUserBO {
    /**
     * 角色id
     * */
    private Long roleId;
    /**
     * 用户id列表
     * */
    private List<Long> userId;
}
