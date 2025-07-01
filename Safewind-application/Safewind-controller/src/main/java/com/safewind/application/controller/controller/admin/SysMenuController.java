package com.safewind.application.controller.controller.admin;

import com.safewind.application.controller.converter.MenuConverter;
import com.safewind.application.controller.dto.MenuDTO;
import com.safewind.application.controller.dto.MenuQueryDTO;
import com.safewind.application.controller.vo.MenuVO;
import com.safewind.common.annotation.ApiOperationLog;
import com.safewind.common.enums.MenuExceptionEnum;
import com.safewind.common.exception.BizException;
import com.safewind.common.page.PageResult;
import com.safewind.common.utils.Result;
import com.safewind.domain.bo.MenuBO;
import com.safewind.domain.bo.MenuListBO;
import com.safewind.domain.service.MenuDomainService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * @Author: Darven
 * @CreateTime: 2025-06-26  18:54
 * @Description: 动态菜单控制层
 */
@RestController
@RequestMapping("/menu")
public class SysMenuController {

    @Autowired
    private MenuDomainService menuDomainService;

    /**
    * @Description: 添加菜单，这里controller专门用来处理参数异常问题
    * @param menuDTO 参数
    * @return com.safewind.common.utils.Result<com.safewind.application.controller.vo.MenuVO> 返回值
    * @author Darven
    * @Date 2025/6/27
    */
    @ApiOperationLog(description = "添加菜单")
    @PostMapping("/addMenu")
    public Result<Boolean> addMenu(@RequestBody MenuDTO menuDTO){
        // 参数校验
        paramCheck(menuDTO);
        // 实体转化
        MenuBO menuBO = MenuConverter.INSTANCE.menuDTOToBO(menuDTO);
        // 业务处理
        MenuBO b = menuDomainService.addMenu(menuBO);
        if(Objects.isNull(b)){
            return Result.fail("添加失败");
        }
        return Result.success();
    }

    /**
     * @param: menuDTO
     * @return Result<Boolean>
     * @author Darven
     * @date 2025/6/27 13:02
     * @description: 修改菜单
     */
    @ApiOperationLog(description = "修改菜单")
    @PostMapping("/updateMenu")
    public Result<Boolean> updateMenu(@RequestBody MenuDTO menuDTO){
        // 参数校验
        Objects.requireNonNull(menuDTO.getMenuId(), "菜单id不能为空");
        paramCheck(menuDTO);

        MenuBO menuBO = MenuConverter.INSTANCE.menuDTOToBO(menuDTO);
        return menuDomainService.updateMenu(menuBO) ?Result.success("更新成功"):Result.fail("更新失败");
    }

    /**
     * @param: menuDTO
     * @return Result<Boolean>
     * @author Darven
     * @date 2025/6/27 13:02
     * @description: 删除菜单
     */
    @ApiOperationLog(description = "删除菜单")
    @PostMapping("/deleteMenu")
    public Result<Boolean> deleteMenu(@RequestBody MenuDTO menuDTO){
        // 参数校验
        Objects.requireNonNull(menuDTO.getMenuId(),"菜单id不能为空");
        Long menuId = menuDTO.getMenuId();
        return menuDomainService.deleteMenu(menuId)?Result.success():Result.fail("删除失败");
    }

    /**
     * @param: menuQueryDTO
     * @return Result<PageResult<MenuVO>>
     * @author Darven
     * @date 2025/6/27 13:02
     * @description: 查询菜单列表，暂时弃用
     */
    @Deprecated
    @ApiOperationLog(description = "查询菜单列表")
    @PostMapping("/queryMenu")
    public Result<PageResult<MenuVO>> queryMenu(@RequestBody MenuQueryDTO menuQueryDTO){
        MenuListBO menuListBO = MenuConverter.INSTANCE.menuQueryDTOToBO(menuQueryDTO);
        PageResult<MenuListBO> menuBOResult = menuDomainService.queryMenu(menuListBO);
        PageResult<MenuVO> pageResult = MenuConverter.INSTANCE.pageMenuBOListToMenuVOList(menuBOResult);
        return Result.success(pageResult);
    }

    /**
     * @return Result<List<MenuVO>>
     * @author Darven
     * @date 2025/6/27 19:17
     * @description: 查询菜单树
     */
    @ApiOperationLog(description = "查询菜单树")
    @PostMapping("/queryMenuTree")
    public Result<List<MenuVO>> queryMenuTree(@RequestBody MenuQueryDTO menuQueryDTO){
        MenuListBO menuListBO = MenuConverter.INSTANCE.menuQueryDTOToBO(menuQueryDTO);
        List<MenuListBO> menuBOList = menuDomainService.queryMenuTree(menuListBO);
        List<MenuVO> menuVOS = MenuConverter.INSTANCE.menuBOListToMenuVOList(menuBOList);
        return Result.success(menuVOS);
    }

    /**
     * @param: menuDTO
     * @author Darven
     * @date 2025/6/27 13:02
     * @description: 统一参数校验
     */
    private void paramCheck(MenuDTO menuDTO){
        if(StringUtils.isBlank(menuDTO.getMenuName())){
            throw new BizException(MenuExceptionEnum.NAME_NOT_NULL);
        }
        if(StringUtils.isBlank(menuDTO.getMenuType())){
            throw new BizException(MenuExceptionEnum.TYPE_NOT_NULL);
        }
    }
}
