package com.tyrone.blog.domain.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

/**
 * @author yingxiu.zty
 * @createDate on 2025/6/8
 * @createTime on 17:54
 */
@Data
public class SysConfigBatchUpdateDTO {

    @Valid
    @NotEmpty(message = "配置项不能为空")
    private List<ConfigItem> configs;

    @Data
    public static class ConfigItem {
        @NotBlank(message = "配置键不能为空")
        private String configKey;

        @NotBlank(message = "配置值不能为空")
        private String configValue;
    }
}
