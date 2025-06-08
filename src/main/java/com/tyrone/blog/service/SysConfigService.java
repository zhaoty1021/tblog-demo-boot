package com.tyrone.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tyrone.blog.domain.dto.SysConfigBatchUpdateDTO;
import com.tyrone.blog.domain.dto.SysConfigDTO;
import com.tyrone.blog.domain.pojo.SysConfig;
import com.tyrone.blog.domain.vo.SysConfigVO;
import java.util.List;
import java.util.Map;

/**
* @author tyronechiao
* @description 针对表【t_sys_config(系统配置表)】的数据库操作Service
* @createDate 2025-06-08 01:23:26
*/
public interface SysConfigService extends IService<SysConfig> {

    /**
     * 创建配置
     * @param dto 配置DTO
     * @return 创建结果
     */
    boolean createConfig(SysConfigDTO dto);

    /**
     * 根据配置键更新配置
     * @param dto 配置DTO
     * @return 更新结果
     */
    boolean updateConfigByKey(SysConfigDTO dto);

    /**
     * 批量更新配置
     * @param batchUpdateDTO 批量更新DTO
     * @return 更新结果
     */
    boolean batchUpdateByKey(SysConfigBatchUpdateDTO batchUpdateDTO);

    /**
     * 获取配置详情
     * @param id 配置ID
     * @return 配置VO
     */
    SysConfigVO getConfigDetail(Long id);

    /**
     * 获取配置列表
     * @return 配置VO列表
     */
    List<SysConfigVO> getConfigList();

    /**
     * 根据分组获取配置
     * @param groupName 分组名称
     * @return 分组配置VO列表
     */
    List<SysConfigVO> getConfigsByGroup(String groupName);

    /**
     * 获取所有配置分组
     * @return 分组名称列表
     */
    List<String> getAllGroups();

    /**
     * 根据配置键获取配置值
     * @param configKey 配置键
     * @return 配置值
     */
    String getConfigValueByKey(String configKey);

    /**
     * 批量获取配置值
     * @param keys 配置键列表
     * @return 键值对
     */
    Map<String, String> getConfigValuesByKeys(List<String> keys);
}
