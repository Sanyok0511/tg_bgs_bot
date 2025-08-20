package com.elite.dangerous.tg;

import com.elite.dangerous.tg.dto.OutgoingMessages;
import com.elite.dangerous.tg.processor.ActionProcessor;
import com.elite.dangerous.tg.processor.CommandMessageProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.Serializable;
import java.util.List;

@Slf4j
@Service
public class TelegramBot {
/*
    @Value("${telegram.configuration.name}")
    private String botName;
    @Value("${telegram.configuration.token}")
    private String token;
    private CommandMessageProcessor commandMessageProcessor;
    private ActionProcessor actionProcessor;

    @Autowired
    public TelegramBot(
            CommandMessageProcessor commandMessageProcessor,
            ActionProcessor actionProcessor) {
        this.commandMessageProcessor = commandMessageProcessor;
        this.actionProcessor = actionProcessor;
    }

    @Override
    public void onUpdateReceived(Update update) {
        //TODO: Пока не работает практически будет исправлено в следующих версиях
        Message incomingMsg = update.getMessage();
        if (incomingMsg != null && incomingMsg.getText() != null) {
            OutgoingMessages message = commandMessageProcessor.answerToIncomingMessage(incomingMsg);

            if (message.getMessageHolders() != null) {
                message.getMessageHolders().forEach(messageHolder -> {
                    try {
                        Message answerMessage = (Message) execute(messageHolder.getMessage());
                    } catch (TelegramApiException ex) {
                        log.error("Error execute command", ex);
                    }
                });
            }

        } else if (update.getCallbackQuery() != null) {
            CallbackQuery callbackQuery = update.getCallbackQuery();
            OutgoingMessages outgoingMessages = commandMessageProcessor.processCallback(callbackQuery);

            if (outgoingMessages.getMessageHolders() != null) {
                outgoingMessages.getMessageHolders().forEach(messageHolder -> {
                    try {
                        Serializable answerMessage = execute(messageHolder.getMessage());
                        if (outgoingMessages.getNextAction() != null) {
                            actionProcessor.processAction(outgoingMessages.getNextAction(), (Message) answerMessage, messageHolder.getFaction());
                        }
                    } catch (TelegramApiException ex) {
                        log.error("Error execute answerCallbackQuery", ex);
                    }
                });
            }
        }
    }

    public void sendMessage(BotApiMethod message) {
        try {
            execute(message);
        } catch (TelegramApiException ex) {
            log.error("Error send message: {} ", message);
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

 */
}
