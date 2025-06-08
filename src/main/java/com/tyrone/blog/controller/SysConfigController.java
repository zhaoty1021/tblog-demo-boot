package com.tyrone.blog.controller;

import com.tyrone.blog.annotation.SysLog;
import com.tyrone.blog.converter.SysConfigConverter;
import com.tyrone.blog.domain.dto.SysConfigBatchUpdateDTO;
import com.tyrone.blog.domain.dto.SysConfigDTO;
import com.tyrone.blog.domain.response.ResultResponse;
import com.tyrone.blog.domain.vo.SysConfigVO;
import com.tyrone.blog.enums.CodeEnum;
import com.tyrone.blog.exceptions.BizException;
import com.tyrone.blog.service.SysConfigService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author yingxiu.zty
 * @createDate on 2025/6/8
 * @createTime on 02:04
 */
@RestController
@RequestMapping("/api/admin/config")
@Tag(name = "系统配置管理")
public class SysConfigController {

    @Resource
    private SysConfigService sysConfigService;

    @PostMapping("/create")
    @SysLog("创建系统配置")
    @Operation(summary = "创建系统配置")
    public ResultResponse<Boolean> createConfig(@RequestBody SysConfigDTO dto) {
        try {
            return ResultResponse.success(sysConfigService.createConfig(dto));
        } catch (BizException e) {
            return ResultResponse.fail(e.getCode(), e.getMessage());
        }
    }

    @PostMapping("/updateByKey")
    @SysLog("更新系统配置")
    @Operation(summary = "根据键更新系统配置")
    public ResultResponse<Boolean> updateConfigByKey(
            @RequestBody SysConfigDTO dto){
        try {
            return ResultResponse.success(sysConfigService.updateConfigByKey(dto));
        } catch (BizException e) {
            return ResultResponse.fail(e.getCode(), e.getMessage());
        }
    }

    @PostMapping("/update")
    @SysLog("批量更新系统配置")
    @Operation(summary = "批量更新系统配置")
    public ResultResponse<Boolean> batchUpdateConfigByKey(
            @Valid @RequestBody SysConfigBatchUpdateDTO batchUpdateDTO) {
        try {
            return ResultResponse.success(sysConfigService.batchUpdateByKey(batchUpdateDTO));
        } catch (BizException e) {
            return ResultResponse.fail(e.getCode(), e.getMessage());
        }
    }

    @GetMapping("/get/{id}")
    @Operation(summary = "获取配置详情")
    public ResultResponse<SysConfigVO> getConfigDetail(@PathVariable Long id) {
        try {
            SysConfigVO vo = sysConfigService.getConfigDetail(id);
            return ResultResponse.success(vo);
        } catch (BizException e) {
            return ResultResponse.fail(e.getCode(), e.getMessage());
        }
    }

    @GetMapping("/list")
    @Operation(summary = "获取所有配置列表")
    public ResultResponse<List<SysConfigVO>> getConfigList() {
        try {
            List<SysConfigVO> voList =  sysConfigService.getConfigList();
            return ResultResponse.success(voList);
        } catch (BizException e) {
            return ResultResponse.fail(e.getCode(), e.getMessage());
        }
    }

    @GetMapping("/group/{groupName}")
    @Operation(summary = "按分组获取配置")
    public ResultResponse<List<SysConfigVO>> getConfigsByGroup(@PathVariable String groupName) {
        try {
            List<SysConfigVO> voList = sysConfigService.getConfigsByGroup(groupName);
            return ResultResponse.success(voList);
        } catch (BizException e) {
            return ResultResponse.fail(e.getCode(), e.getMessage());
        }
    }

    @GetMapping("/groups")
    @Operation(summary = "获取所有分组名称")
    public ResultResponse<List<String>> getAllGroups() {
        try {
            return ResultResponse.success(sysConfigService.getAllGroups());
        } catch (BizException e) {
            return ResultResponse.fail(e.getCode(), e.getMessage());
        }
    }

    @GetMapping("/value/{configKey}")
    @Operation(summary = "根据键获取配置值")
    public ResultResponse<String> getConfigValueByKey(@PathVariable String configKey) {
        try {
            return ResultResponse.success(sysConfigService.getConfigValueByKey(configKey));
        } catch (BizException e) {
            return ResultResponse.fail(e.getCode(), e.getMessage());
        }
    }

    @PostMapping("/values")
    @Operation(summary = "批量获取配置值")
    public ResultResponse<Map<String, String>> getConfigValuesByKeys(@RequestBody List<String> keys) {
        try {
            return ResultResponse.success(sysConfigService.getConfigValuesByKeys(keys));
        } catch (BizException e) {
            return ResultResponse.fail(e.getCode(), e.getMessage());
        }
    }
}

