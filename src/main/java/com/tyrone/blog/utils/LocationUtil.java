package com.tyrone.blog.utils;

import com.tyrone.blog.enums.CodeEnum;
import com.tyrone.blog.exceptions.BizException;
import lombok.Data;
import org.springframework.web.client.RestTemplate;

/**
 * @author yingxiu.zty
 * @createTime on 2024/9/22
 */
public class LocationUtil {
    public static String getLocationByIp(String ip) {
        String url = "http://ip-api.com/json/" + ip;
        RestTemplate restTemplate = new RestTemplate();
        try {
            // 调用第三方 API 获取地理位置
            IpLocation location = restTemplate.getForObject(url, IpLocation.class);
            if (location != null && "success".equals(location.getStatus())) {
                return location.getCountry() + ", " + location.getCity();
            }
        } catch (Exception e) {
            throw new BizException(CodeEnum.OUTSIDE_API_ERROR);
        }
        return "Unknown Location";
    }
}

@Data
class IpLocation {
    private String status;
    private String country;
    private String city;
    // getters and setters
}
