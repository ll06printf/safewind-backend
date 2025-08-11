package com.safewind.domain.bo;

import lombok.Builder;
import lombok.Data;

/**
 * @Author: Darven
 * @CreateTime: 2025-05-29  20:13
 * @Description: 用户部门
 */
@Data
@Builder
public class UserDeptBO {
    /**
     * 部门id
     * */
    private Long deptId;
    /**
     * 部门名字
     */
    private String name;
}
