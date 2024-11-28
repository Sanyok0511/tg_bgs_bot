package org.example.tg.messageCreator;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface ConflictMessageCreator {
    SendMessage createMessage(String systemName, Long chatId);

}
