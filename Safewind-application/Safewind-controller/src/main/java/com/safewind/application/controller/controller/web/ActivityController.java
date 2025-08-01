package com.safewind.application.controller.controller.web;

import com.safewind.application.controller.vo.WsActivityVO;
import com.safewind.common.annotation.ApiOperationLog;
import com.safewind.common.page.PageResult;
import com.safewind.common.utils.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: Darven
 * @createTime: 2025-08-01  18:32
 * @description: 官网-活动模块
 */
@RestController
@RequestMapping("/ws-activity")
public class ActivityController {

    @ApiOperationLog(description = "首页查看活动")
    @GetMapping("/getActivity")
    public Result<List<WsActivityVO>> getActivity(){
        return null;
    }

    @ApiOperationLog(description = "所有活动详情-分页查询")
    @GetMapping("/getAllActivity")
    public Result<PageResult<WsActivityVO>> getAllActivity(){
        return null;
    }
}
