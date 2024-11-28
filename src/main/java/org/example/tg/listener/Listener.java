package org.example.tg.listener;

import org.example.tg.processor.IncomingMessageProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

@Service
public class Listener extends TelegramLongPollingBot {
    Logger logger = LoggerFactory.getLogger(Listener.class);

    @Value("${telegram.configuration.name}")
    private String botName;
    @Value("${telegram.configuration.token}")
    private String token;

    @Autowired
    IncomingMessageProcessor incomingMessageProcessor;

    @Override
    public void onUpdateReceived(Update update) {
        Message incomingMsg = update.getMessage();
        if (incomingMsg != null) {
            try {
                SendMessage outgoingMsg = incomingMessageProcessor.processMessage(incomingMsg);
                execute(outgoingMsg);
            } catch (TelegramApiException ex) {
                logger.error("Failed send message", ex);
            }
        } else if (update.getCallbackQuery() != null) {
            CallbackQuery callbackQuery = update.getCallbackQuery();
            callbackQuery.getMessage();
        }
    }

    @Override
    public void onUpdatesReceived(List<Update> updates) {
        super.onUpdatesReceived(updates);
    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void onRegister() {
        super.onRegister();
    }
}
