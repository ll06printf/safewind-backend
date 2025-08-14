package com.safewind.application.controller.controller.admin;

import com.safewind.application.controller.converter.ActivityConverter;
import com.safewind.application.controller.dto.ActivityDTO;
import com.safewind.application.controller.dto.ActivityQueryDTO;
import com.safewind.application.controller.vo.WsActivityVO;
import com.safewind.common.annotation.ApiOperationLog;
import com.safewind.common.enums.ActivityExceptionEnum;
import com.safewind.common.exception.BizException;
import com.safewind.common.page.PageResult;
import com.safewind.common.utils.Result;
import com.safewind.domain.bo.ActivityBO;
import com.safewind.domain.bo.ActivityQueryBO;
import com.safewind.domain.service.ActivityDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * 活动管理控制器
 * 提供活动相关的增删改查功能，支持分页查询、添加、修改、删除和详情获取。
 *
 * @author: Darven
 * @createTime: 2025-08-13  00:11
 */
@RestController
@RequestMapping("/admin-activity")
public class ActivityAdminController {

    @Autowired
    private ActivityDomainService activityDomainService; // 活动领域服务，处理核心业务逻辑

    /**
     * 分页查询活动列表
     *
     * @param queryDTO 查询条件封装对象，包含分页信息和筛选条件
     * @return 包含分页结果的活动视图对象列表
     */
    @ApiOperationLog(description = "活动列表-分页查询")
    @PostMapping("/getActivity")
    public Result<PageResult<WsActivityVO>> getActivity(@RequestBody ActivityQueryDTO queryDTO) {
        // 将 DTO 转换为 BO 对象，用于传递给领域服务
        ActivityQueryBO queryBO = ActivityConverter.INSTANCE.queryDTOToQueryBO(queryDTO);

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
     * 添加活动
     *
     * @param activityDTO 活动数据传输对象，包含活动的基本信息
     * @return 新增活动的视图对象
     * @throws BizException 如果传入的活动数据为空，则抛出业务异常
     */
    @ApiOperationLog(description = "添加活动")
    @PostMapping("/addActivity")
    public Result<WsActivityVO> addActivity(@RequestBody ActivityDTO activityDTO) {
        // 校验传入的活动数据是否为空
        if (Objects.isNull(activityDTO)) {
            throw new BizException(ActivityExceptionEnum.ACTIVITY_IS_NULL);
        }

        // 将 DTO 转换为 BO 对象
        ActivityBO activityBO = ActivityConverter.INSTANCE.dtoToBO(activityDTO);

        // 调用领域服务保存活动数据
        ActivityBO savedActivity = activityDomainService.addActivity(activityBO);

        // 将保存后的 BO 转换为 VO 并返回
        WsActivityVO activityVO = ActivityConverter.INSTANCE.boToVO(savedActivity);
        return Result.success(activityVO);
    }

    /**
     * 修改活动
     *
     * @param activityDTO 活动数据传输对象，包含需要修改的活动信息
     * @return 修改后的活动视图对象
     */
    @ApiOperationLog(description = "修改活动")
    @PostMapping("/updateActivity")
    public Result<WsActivityVO> updateActivity(@RequestBody ActivityDTO activityDTO) {
        // 校验传入的活动数据是否为空
        if (Objects.isNull(activityDTO)) {
            throw new BizException(ActivityExceptionEnum.ACTIVITY_IS_NULL);
        }
        if (Objects.isNull(activityDTO.getId())) {
            throw new BizException(ActivityExceptionEnum.ACTIVITY_ID_NOT_NULL);
        }
        // 将 DTO 转换为 BO 对象
        ActivityBO activityBO = ActivityConverter.INSTANCE.dtoToBO(activityDTO);

        // 调用领域服务更新活动数据
        ActivityBO updatedActivity = activityDomainService.updateActivity(activityBO);

        // 将更新后的 BO 转换为 VO 并返回
        WsActivityVO activityVO = ActivityConverter.INSTANCE.boToVO(updatedActivity);
        return Result.success(activityVO);
    }

    /**
     * 删除活动
     *
     * @param activityDTO 活动数据传输对象，需包含活动 ID
     * @return 删除结果（布尔值）
     * @throws BizException 如果传入的活动数据或活动 ID 为空，则抛出业务异常
     */
    @ApiOperationLog(description = "删除活动")
    @PostMapping("/deleteActivity")
    public Result<Boolean> deleteActivity(@RequestBody ActivityDTO activityDTO) {
        // 校验传入的活动数据是否为空
        if (Objects.isNull(activityDTO)) {
            throw new BizException(ActivityExceptionEnum.ACTIVITY_IS_NULL);
        }

        // 获取活动 ID 并校验是否为空
        Long id = activityDTO.getId();
        if (Objects.isNull(id)) {
            throw new BizException(ActivityExceptionEnum.ACTIVITY_ID_NOT_NULL);
        }

        // 调用领域服务删除活动
        Boolean result = activityDomainService.deleteActivity(id);
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
