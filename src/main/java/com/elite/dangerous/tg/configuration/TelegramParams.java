package com.elite.dangerous.tg.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "telegram.configuration")
public class TelegramParams {
    private String name = "";
    private String token = "";
}
