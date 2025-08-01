package com.safewind.application.controller.controller.web;

import com.safewind.common.annotation.ApiOperationLog;
import com.safewind.common.utils.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

/**
 * @author: Darven
 * @createTime: 2025-08-01  18:35
 * @description: 官网-部门介绍
 */
@RestController
@RequestMapping("/ws-department")
public class DepartmentController {
    @ApiOperationLog(description = "首页展示部门介绍")
    @GetMapping("/getDepartment")
    public Result<List<Objects>> getDepartment(){
        return null;
    }
}
