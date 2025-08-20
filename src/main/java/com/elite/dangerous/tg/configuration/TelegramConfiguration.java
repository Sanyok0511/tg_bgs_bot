package com.elite.dangerous.tg.configuration;

import com.elite.dangerous.tg.TelegramBotReceiver;
import com.elite.dangerous.tg.TelegramBotSender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.longpolling.TelegramBotsLongPollingApplication;
import org.telegram.telegrambots.longpolling.util.DefaultGetUpdatesGenerator;
import org.telegram.telegrambots.meta.TelegramUrl;
import org.telegram.telegrambots.meta.api.methods.updates.AllowedUpdates;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

import java.util.List;

@Configuration
public class TelegramConfiguration {
    @Bean
    public TelegramClient telegramClient(TelegramParams params) {
        return new OkHttpTelegramClient(params.getToken());
    }

    @Bean(destroyMethod = "close")
    public TelegramBotsLongPollingApplication telegramBotsApplication(TelegramParams params, TelegramBotSender sender) {
        var application = new TelegramBotsLongPollingApplication();
        try {
            var updateGenerator = new DefaultGetUpdatesGenerator(List.of(AllowedUpdates.MESSAGEREACTION, AllowedUpdates.MESSAGE));

            application.registerBot(
                    params.getToken(),
                    () -> TelegramUrl.DEFAULT_URL,
                    updateGenerator,
                    new TelegramBotReceiver(sender));

        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
        return application;
    }

    private void close(TelegramBotsLongPollingApplication telegramBotsApplication) throws Exception {
        telegramBotsApplication.close();
    }
}
