package com.changing.customer.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 全局属性获取工具
 *
 * @author chenjun
 * @version V1.0
 * @since 2020-03-18 20:18
 */
@Component
public class PropertyUtils {

    @Value("${security.oauth2.client.access-token-uri}")
    public String authTokenUrl;

    @Value("${security.oauth2.client.client-id}")
    public String authClientId;

    @Value("${security.oauth2.client.client-secret}")
    public String authClientSecret;

}