package com.elite.dangerous.tg;

import com.elite.dangerous.tg.processor.CommandMessageProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

@Slf4j
@Service
public class TelegramBot extends TelegramLongPollingBot {

    @Value("${telegram.configuration.name}")
    private String botName;
    @Value("${telegram.configuration.token}")
    private String token;
    private CommandMessageProcessor commandMessageProcessor;

    @Autowired
    public TelegramBot(CommandMessageProcessor commandMessageProcessor) {
        this.commandMessageProcessor = commandMessageProcessor;
    }

    @Override
    public void onUpdateReceived(Update update) {
        //TODO: Пока не работает практически будет исправлено в следующих версиях
        Message incomingMsg = update.getMessage();
        if (incomingMsg != null) {
            String incomingMsgText = incomingMsg.getText();
            if (incomingMsgText != null) {
                BotApiMethod message = commandMessageProcessor.answerToIncomingMessage(incomingMsg);
                try {
                    if(message != null) {
                        Message message1 = (Message) execute (message);
                    }
                } catch (TelegramApiException ex) {
                    log.error("Error execute command", ex);
                }

            }
        } else if (update.getCallbackQuery() != null) {
            CallbackQuery callbackQuery = update.getCallbackQuery();
            BotApiMethod query = commandMessageProcessor.processCallback(callbackQuery);
            try {
                execute(query);
            } catch (TelegramApiException ex) {
                log.error("Error execute answerCallbackQuery", ex);
            }
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
