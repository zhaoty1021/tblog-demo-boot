package com.tyrone.blog.converter;


import com.tyrone.blog.domain.dto.SysConfigDTO;
import com.tyrone.blog.domain.pojo.SysConfig;
import com.tyrone.blog.domain.vo.SysConfigVO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yingxiu.zty
 * @createDate on 2025/6/8
 * @createTime on 01:50
 */
@Component
public class SysConfigConverter {
    public static final SysConfigConverter INSTANCE = new SysConfigConverter();

    private SysConfigConverter() {}

    // SysConfig(PO) -> SysConfigDTO
    public SysConfigDTO sysConfigToSysConfigDTO(SysConfig sysConfig) {
        if (sysConfig == null) {
            return null;
        }
        SysConfigDTO dto = new SysConfigDTO();
        dto.setConfigKey(sysConfig.getConfigKey());
        dto.setConfigValue(sysConfig.getConfigValue());
        dto.setConfigType(sysConfig.getConfigType());
        dto.setGroupName(sysConfig.getGroupName());
        dto.setRemark(sysConfig.getRemark());
        dto.setIsSystem(sysConfig.getIsSystem());
        return dto;
    }

    // SysConfigDTO -> SysConfig(PO)
    public SysConfig sysConfigDTOToSysConfig(SysConfigDTO dto) {
        if (dto == null) {
            return null;
        }
        SysConfig sysConfig = new SysConfig();
        sysConfig.setConfigKey(dto.getConfigKey());
        sysConfig.setConfigValue(dto.getConfigValue());
        sysConfig.setConfigType(dto.getConfigType());
        sysConfig.setGroupName(dto.getGroupName());
        sysConfig.setRemark(dto.getRemark());
        sysConfig.setIsSystem(dto.getIsSystem());
        return sysConfig;
    }

    // SysConfigDTO -> SysConfigVO
    public SysConfigVO sysConfigDTOToSysConfigVO(SysConfigDTO dto) {
        if (dto == null) {
            return null;
        }
        SysConfigVO vo = new SysConfigVO();
        vo.setConfigKey(dto.getConfigKey());
        vo.setConfigValue(dto.getConfigValue());
        vo.setConfigType(dto.getConfigType());
        vo.setGroupName(dto.getGroupName());
        vo.setRemark(dto.getRemark());
        vo.setIsSystem(dto.getIsSystem());
        return vo;
    }

    // SysConfig(PO) -> SysConfigVO
    public SysConfigVO sysConfigToSysConfigVO(SysConfig sysConfig) {
        if (sysConfig == null) {
            return null;
        }
        SysConfigVO vo = new SysConfigVO();
        vo.setId(sysConfig.getId());
        vo.setConfigKey(sysConfig.getConfigKey());
        vo.setConfigValue(sysConfig.getConfigValue());
        vo.setConfigType(sysConfig.getConfigType());
        vo.setGroupName(sysConfig.getGroupName());
        vo.setRemark(sysConfig.getRemark());
        vo.setIsSystem(sysConfig.getIsSystem());
        vo.setCreateTime(sysConfig.getCreateTime());
        vo.setUpdateTime(sysConfig.getUpdateTime());
        return vo;
    }

    // List<SysConfig> -> List<SysConfigVO>
    public List<SysConfigVO> sysConfigListToSysConfigVOList(List<SysConfig> sysConfigList) {
        if (sysConfigList == null) {
            return null;
        }
        return sysConfigList.stream()
                .map(this::sysConfigToSysConfigVO)
                .collect(Collectors.toList());
    }

    // List<SysConfigDTO> -> List<SysConfigVO>
    public List<SysConfigVO> sysConfigDTOListToSysConfigVOList(List<SysConfigDTO> dtoList) {
        if (dtoList == null) {
            return null;
        }
        return dtoList.stream()
                .map(this::sysConfigDTOToSysConfigVO)
                .collect(Collectors.toList());
    }

    // 更新PO对象
    public void updateSysConfigFromDTO(SysConfig sysConfig, SysConfigDTO dto) {
        if (sysConfig == null || dto == null) {
            return;
        }
        sysConfig.setConfigKey(dto.getConfigKey());
        sysConfig.setConfigValue(dto.getConfigValue());
        sysConfig.setConfigType(dto.getConfigType());
        sysConfig.setGroupName(dto.getGroupName());
        sysConfig.setRemark(dto.getRemark());
        sysConfig.setIsSystem(dto.getIsSystem());
    }
}
