package org.example.tg.messageCreator;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class WarSendMessageCreator implements ConflictMessageCreator {

    @Override
    public SendMessage createMessage(String systemName, Long chatId) {
        return null;
    }
}
