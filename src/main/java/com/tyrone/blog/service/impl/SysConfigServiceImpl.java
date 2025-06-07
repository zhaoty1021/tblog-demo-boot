package com.tyrone.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tyrone.blog.converter.SysConfigConverter;
import com.tyrone.blog.domain.dto.SysConfigDTO;
import com.tyrone.blog.domain.pojo.SysConfig;
import com.tyrone.blog.domain.vo.SysConfigVO;
import com.tyrone.blog.enums.CodeEnum;
import com.tyrone.blog.exceptions.BizException;
import com.tyrone.blog.mapper.SysConfigMapper;
import com.tyrone.blog.service.SysConfigService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author tyronechiao
 * @description 针对表【t_sys_config(系统配置表)】的数据库操作Service实现
 * @createDate 2025-06-08 01:23:26
 */
@Service
public class SysConfigServiceImpl extends ServiceImpl<SysConfigMapper, SysConfig> implements SysConfigService {

    @Resource
    private SysConfigConverter sysConfigConverter;

    @Override
    public boolean createConfig(SysConfigDTO dto) {
        SysConfig po = sysConfigConverter.sysConfigDTOToSysConfig(dto);
        return save(po);
    }

    @Override
    public boolean updateConfigByKey(SysConfigDTO dto) {
        LambdaQueryWrapper<SysConfig> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysConfig::getConfigKey, dto.getConfigKey());
        SysConfig po = getOne(wrapper);

        if (po == null) {
            throw new BizException(CodeEnum.DATA_NOT_EXIST, "配置不存在");
        }

        sysConfigConverter.updateSysConfigFromDTO(po, dto);
        return updateById(po);
    }

    @Override
    public SysConfigVO getConfigDetail(Long id) {
        SysConfig po = getById(id);
        return sysConfigConverter.sysConfigToSysConfigVO(po);
    }

    @Override
    public List<SysConfigVO> getConfigList() {
        List<SysConfig> list = list();
        return sysConfigConverter.sysConfigListToSysConfigVOList(list);
    }

    @Override
    public List<SysConfigVO> getConfigsByGroup(String groupName) {
        LambdaQueryWrapper<SysConfig> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysConfig::getGroupName, groupName);
        List<SysConfig> list = list(wrapper);
        return sysConfigConverter.sysConfigListToSysConfigVOList(list);
    }

    @Override
    public List<String> getAllGroups() {
        LambdaQueryWrapper<SysConfig> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(SysConfig::getGroupName).groupBy(SysConfig::getGroupName);
        return list(wrapper).stream()
                .map(SysConfig::getGroupName)
                .collect(Collectors.toList());
    }

    @Override
    public String getConfigValueByKey(String configKey) {
        LambdaQueryWrapper<SysConfig> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysConfig::getConfigKey, configKey);
        SysConfig config = getOne(wrapper);
        return config != null ? config.getConfigValue() : null;
    }

    @Override
    public Map<String, String> getConfigValuesByKeys(List<String> keys) {
        if (CollectionUtils.isEmpty(keys)) {
            return Collections.emptyMap();
        }

        LambdaQueryWrapper<SysConfig> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(SysConfig::getConfigKey, keys);
        List<SysConfig> configs = list(wrapper);

        return configs.stream()
                .collect(Collectors.toMap(
                        SysConfig::getConfigKey,
                        SysConfig::getConfigValue,
                        (v1, v2) -> v1));
    }
}