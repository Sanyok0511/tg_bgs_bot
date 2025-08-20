package com.elite.dangerous.tg.processor;

import com.elite.dangerous.db.entity.StarSystem;
import com.elite.dangerous.service.TgMessageService;
import com.elite.dangerous.tg.TelegramBot;
import com.elite.dangerous.tg.messageCreator.InfluenceMessageCreator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MessageUpdater {
    private TgMessageService tgMessageService;
    private InfluenceMessageCreator influenceMessageCreator;
    private TelegramBot telegramBot;


    public void updateInfluence(StarSystem starSystem) {
//        List<TgMessage> messages = tgMessageService.findMessagesForUpdateInfluence(starSystem.getName());
//        messages.forEach(tgMessage -> {
//            BotApiMethod message = influenceMessageCreator.updateMessage(tgMessage);
//            //telegramBot.sendMessage(message);
//        });

    }
}
