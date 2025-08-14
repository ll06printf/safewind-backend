package com.safewind.application.controller.controller.web;

import com.safewind.application.controller.converter.ActivityConverter;
import com.safewind.application.controller.vo.WsActivityVO;
import com.safewind.common.annotation.ApiOperationLog;
import com.safewind.common.enums.ActivityExceptionEnum;
import com.safewind.common.exception.BizException;
import com.safewind.common.page.PageResult;
import com.safewind.common.utils.Result;
import com.safewind.domain.bo.ActivityBO;
import com.safewind.domain.bo.ActivityListBO;
import com.safewind.domain.bo.ActivityQueryBO;
import com.safewind.domain.service.ActivityDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

/**
 * 官网活动控制器
 * 提供官网活动模块的相关接口，包括最新活动查询、分页查询所有活动详情以及根据 ID 获取活动详情。
 *
 * @author: Darven
 * @createTime: 2025-08-01  18:32
 */
@RestController
@RequestMapping("/ws-activity")
public class ActivityController {

    @Autowired
    private ActivityDomainService activityDomainService; // 活动领域服务，处理核心业务逻辑

    /**
     * 首页查看最新活动
     *
     * @param limit 查询数量限制，默认值为 5
     * @return 包含最新活动的视图对象列表
     */
    @ApiOperationLog(description = "首页查看最新活动")
    @GetMapping("/getLatestActivities")
    public Result<List<WsActivityVO>> getLatestActivities(@RequestParam(defaultValue = "5") Integer limit) {
        // 调用领域服务获取最新活动数据
        List<ActivityListBO> latestActivities = activityDomainService.getLatestActivities(limit);

        // 将 BO 列表转换为 VO 列表，用于前端展示
        List<WsActivityVO> activityVOList = ActivityConverter.INSTANCE.listBOListToVOList(latestActivities);

        // 返回成功结果
        return Result.success(activityVOList);
    }

    /**
     * 所有活动详情-分页查询
     *
     * @param pageNum  当前页码，默认值为 1
     * @param pageSize 每页显示条数，默认值为 10
     * @return 包含分页结果的活动视图对象列表
     */
    @ApiOperationLog(description = "所有活动详情-分页查询")
    @GetMapping("/getAllActivity")
    public Result<PageResult<WsActivityVO>> getAllActivity(@RequestParam(defaultValue = "1") Long pageNum,
                                                           @RequestParam(defaultValue = "10") Long pageSize) {
        // 构造分页查询条件 BO 对象
        ActivityQueryBO queryBO = new ActivityQueryBO();
        queryBO.setPageNum(pageNum);
        queryBO.setPageSize(pageSize);

        // 调用领域服务进行分页查询，返回分页结果
        PageResult<ActivityBO> pageResult = activityDomainService.queryActivityPage(queryBO);

        // 将 BO 列表转换为 VO 列表，用于前端展示
        List<WsActivityVO> voList = ActivityConverter.INSTANCE.boListToVOList(pageResult.getData());

        // 构造分页结果的 VO 对象并返回
        PageResult<WsActivityVO> result = PageResult.<WsActivityVO>builder()
                .data(voList)
                .totalSize(pageResult.getTotalSize())
                .totalPages(pageResult.getTotalPages())
                .pageNum(pageResult.getPageNum())
                .pageSize(pageResult.getPageSize())
                .build();
        return Result.success(result);
    }

    /**
     * 根据 ID 获取活动详情
     *
     * @param id 活动 ID
     * @return 活动的视图对象
     * @throws BizException 如果传入的活动 ID 为空或未找到对应活动，则抛出业务异常
     */
    @ApiOperationLog(description = "根据ID获取活动详情")
    @GetMapping("/getActivityById")
    public Result<WsActivityVO> getActivityById(@RequestParam Long id) {
        // 校验活动 ID 是否为空
        if (Objects.isNull(id)) {
            throw new BizException(ActivityExceptionEnum.ACTIVITY_ID_NOT_NULL);
        }

        // 调用领域服务获取活动数据
        ActivityBO activityBO = activityDomainService.getActivityById(id);

        // 校验活动数据是否存在
        if (Objects.isNull(activityBO)) {
            throw new BizException(ActivityExceptionEnum.ACTIVITY_NOT_EXIST);
        }

        // 将 BO 转换为 VO 并返回
        WsActivityVO activityVO = ActivityConverter.INSTANCE.boToVO(activityBO);
        return Result.success(activityVO);
    }
}
