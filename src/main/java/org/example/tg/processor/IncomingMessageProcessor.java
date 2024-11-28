package org.example.tg.processor;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Component
public class IncomingMessageProcessor {
    public SendMessage processMessage(Message message) {
        SendMessage outgoingMsg = new SendMessage();
        String textMessage = message.getText();
        if (textMessage != null) {
            String systemName = null;
            if (textMessage.startsWith("/война")) {
                systemName = textMessage.substring(textMessage.indexOf("war") + 4);

                List<InlineKeyboardButton> listButtons = new ArrayList<>();
                listButtons.add(new InlineKeyboardButton("Низкая",null, "low", null, null, null, null, null, null));
                listButtons.add(new InlineKeyboardButton("Средняя",null, "med", null, null, null, null, null, null));
                listButtons.add(new InlineKeyboardButton("Высокая",null, "hi", null, null, null, null, null, null));

                InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup(List.of(listButtons));

                outgoingMsg.setReplyMarkup(keyboardMarkup);
            }
            if (textMessage.startsWith("/выборы")) {
                systemName = textMessage.substring(textMessage.indexOf("election") + 9);

                List<InlineKeyboardButton> listButtons = new ArrayList<>();
                for (int i = 1; i <= 5; i++) {
                    listButtons.add(new InlineKeyboardButton(Integer.toString(i),null, Integer.toString(i), null, null, null, null, null, null));
                }

                InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup(List.of(listButtons));
            }
            if (systemName == null) {
                systemName = "Unknown system";
            }
            outgoingMsg.setText(systemName);
        }
        outgoingMsg.setChatId(message.getChatId());
        return outgoingMsg;
    }
}
