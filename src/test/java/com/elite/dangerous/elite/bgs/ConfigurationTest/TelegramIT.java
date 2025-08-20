package com.elite.dangerous.elite.bgs.ConfigurationTest;

import com.elite.dangerous.elite.bgs.annotations.IT;
import com.elite.dangerous.tg.configuration.TelegramParams;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@IT
public class TelegramIT {
    @Autowired
    private TelegramParams telegramParams;
    @Test
    public void checkTelegramParams() {
        assertThat(telegramParams.getName()).isNotEmpty();
        assertThat(telegramParams.getToken()).isNotEmpty();
    }
}
