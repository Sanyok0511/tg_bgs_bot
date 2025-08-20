package com.elite.dangerous.tg;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethod;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

import java.io.Serializable;

@AllArgsConstructor
@Service
public class TelegramBotSender {
    private TelegramClient telegramClient;

    public <T extends Serializable> T sendMessage(BotApiMethod<T> message) {
        try {
            return telegramClient.execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
