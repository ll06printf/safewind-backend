package com.safewind.application.controller.controller.admin;

import com.safewind.application.controller.converter.WallConverter;
import com.safewind.application.controller.dto.WallDTO;
import com.safewind.application.controller.dto.WallQueryDTO;
import com.safewind.application.controller.vo.WsWallVO;
import com.safewind.common.annotation.ApiOperationLog;
import com.safewind.common.enums.WallExceptionEnum;
import com.safewind.common.exception.BizException;
import com.safewind.common.page.PageResult;
import com.safewind.common.utils.Result;
import com.safewind.domain.bo.WallBO;
import com.safewind.domain.bo.WallQueryBO;
import com.safewind.domain.service.WallDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * 弹幕墙管理控制器
 * 提供弹幕墙相关的增删改查功能，支持分页查询、添加、修改、删除和详情获取。
 *
 * @author: Darven
 * @createTime: 2025-08-13  00:11
 */
@RestController
@RequestMapping("/admin-wall")
public class WallAdminController {

    @Autowired
    private WallDomainService wallDomainService; // 弹幕墙领域服务，处理核心业务逻辑

    /**
     * 分页查询弹幕列表
     *
     * @param queryDTO 查询条件封装对象，包含分页信息和筛选条件
     * @return 包含分页结果的弹幕视图对象列表
     */
    @ApiOperationLog(description = "弹幕列表-分页查询")
    @PostMapping("/getWall")
    public Result<PageResult<WsWallVO>> getWall(@RequestBody WallQueryDTO queryDTO) {
        // 将 DTO 转换为 BO 对象，用于传递给领域服务
        WallQueryBO queryBO = WallConverter.INSTANCE.queryDTOToQueryBO(queryDTO);

        // 调用领域服务进行分页查询，返回分页结果
        PageResult<WallBO> pageResult = wallDomainService.queryWallPage(queryBO);

        // 将 BO 列表转换为 VO 列表，用于前端展示
        List<WsWallVO> voList = WallConverter.INSTANCE.boListToVOList(pageResult.getData());

        // 构造分页结果的 VO 对象并返回
        PageResult<WsWallVO> result = PageResult.<WsWallVO>builder()
                .data(voList)
                .totalSize(pageResult.getTotalSize())
                .totalPages(pageResult.getTotalPages())
                .pageNum(pageResult.getPageNum())
                .pageSize(pageResult.getPageSize())
                .build();
        return Result.success(result);
    }

    /**
     * 添加弹幕
     *
     * @param wallDTO 弹幕数据传输对象，包含弹幕的基本信息
     * @return 新增弹幕的视图对象
     * @throws BizException 如果传入的弹幕数据为空，则抛出业务异常
     */
    @ApiOperationLog(description = "添加弹幕")
    @PostMapping("/addWall")
    public Result<WsWallVO> addWall(@RequestBody WallDTO wallDTO) {
        // 校验传入的弹幕数据是否为空
        if (Objects.isNull(wallDTO)) {
            throw new BizException(WallExceptionEnum.WALL_IS_NULL);
        }

        // 将 DTO 转换为 BO 对象
        WallBO wallBO = WallConverter.INSTANCE.dtoToBO(wallDTO);

        // 调用领域服务保存弹幕数据
        WallBO savedWall = wallDomainService.addWall(wallBO);

        // 将保存后的 BO 转换为 VO 并返回
        WsWallVO wallVO = WallConverter.INSTANCE.boToVO(savedWall);
        return Result.success(wallVO);
    }

    /**
     * 修改弹幕
     *
     * @param wallDTO 弹幕数据传输对象，包含需要修改的弹幕信息
     * @return 修改后的弹幕视图对象
     */
    @ApiOperationLog(description = "修改弹幕")
    @PostMapping("/updateWall")
    public Result<WsWallVO> updateWall(@RequestBody WallDTO wallDTO) {
        // 校验传入的弹幕数据是否为空
        if (Objects.isNull(wallDTO)) {
            throw new BizException(WallExceptionEnum.WALL_IS_NULL);
        }
        if (Objects.isNull(wallDTO.getId())) {
            throw new BizException(WallExceptionEnum.WALL_ID_NOT_NULL);
        }
        // 将 DTO 转换为 BO 对象
        WallBO wallBO = WallConverter.INSTANCE.dtoToBO(wallDTO);

        // 调用领域服务更新弹幕数据
        WallBO updatedWall = wallDomainService.updateWall(wallBO);

        // 将更新后的 BO 转换为 VO 并返回
        WsWallVO wallVO = WallConverter.INSTANCE.boToVO(updatedWall);
        return Result.success(wallVO);
    }

    /**
     * 删除弹幕
     *
     * @param wallDTO 弹幕数据传输对象，需包含弹幕 ID
     * @return 删除结果（布尔值）
     * @throws BizException 如果传入的弹幕数据或弹幕 ID 为空，则抛出业务异常
     */
    @ApiOperationLog(description = "删除弹幕")
    @PostMapping("/deleteWall")
    public Result<Boolean> deleteWall(@RequestBody WallDTO wallDTO) {
        // 校验传入的弹幕数据是否为空
        if (Objects.isNull(wallDTO)) {
            throw new BizException(WallExceptionEnum.WALL_IS_NULL);
        }

        // 获取弹幕 ID 并校验是否为空
        Long id = wallDTO.getId();
        if (Objects.isNull(id)) {
            throw new BizException(WallExceptionEnum.WALL_ID_NOT_NULL);
        }

        // 调用领域服务删除弹幕
        Boolean result = wallDomainService.deleteWall(id);
        return Result.success(result);
    }

    /**
     * 根据 ID 获取弹幕详情
     *
     * @param id 弹幕 ID
     * @return 弹幕的视图对象
     * @throws BizException 如果传入的弹幕 ID 为空或未找到对应弹幕，则抛出业务异常
     */
    @ApiOperationLog(description = "根据ID获取弹幕详情")
    @GetMapping("/getWallById")
    public Result<WsWallVO> getWallById(@RequestParam Long id) {
        // 校验弹幕 ID 是否为空
        if (Objects.isNull(id)) {
            throw new BizException(WallExceptionEnum.WALL_ID_NOT_NULL);
        }

        // 调用领域服务获取弹幕数据
        WallBO wallBO = wallDomainService.getWallById(id);

        // 校验弹幕数据是否存在
        if (Objects.isNull(wallBO)) {
            throw new BizException(WallExceptionEnum.WALL_NOT_EXIST);
        }

        // 将 BO 转换为 VO 并返回
        WsWallVO wallVO = WallConverter.INSTANCE.boToVO(wallBO);
        return Result.success(wallVO);
    }
}
