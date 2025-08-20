package com.elite.dangerous.tg.processor;

import com.elite.dangerous.db.TypeMessage;
import com.elite.dangerous.db.entity.Faction;
import com.elite.dangerous.db.entity.TgMessage;
import com.elite.dangerous.db.repository.TgMessageRepository;
import com.elite.dangerous.tg.dto.NextAction;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.message.Message;

@Service
public class ActionProcessor {
    TgMessageRepository messageRepository;
    public ActionProcessor (TgMessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }
    public void processAction(NextAction action, Message message, Faction faction) {
        switch (action) {
            case CREATE_INFLUENCE_MESSAGE :
                TgMessage tgMessage = messageRepository.findByChatIdAndFactionAndTypeMessage(message.getChatId(), faction, TypeMessage.INFLUENCE_MESSAGE);
                if (tgMessage == null) {
                    tgMessage = new TgMessage();
                    tgMessage.setChatId(message.getChatId());
                }
                tgMessage.setMessageId(message.getMessageId());
                tgMessage.setFaction(faction);
                tgMessage.setTypeMessage(TypeMessage.INFLUENCE_MESSAGE);
                messageRepository.save(tgMessage);
                break;


            case CREATE_CONFLICT_MESSAGE :
                tgMessage = messageRepository.findByChatIdAndFactionAndTypeMessage(message.getChatId(), faction, TypeMessage.CONFLICT_MESSAGE);
                if (tgMessage == null) {
                    tgMessage = new TgMessage();
                    tgMessage.setChatId(message.getChatId());
                }
                tgMessage.setMessageId(message.getMessageId());
                tgMessage.setFaction(faction);
                tgMessage.setTypeMessage(TypeMessage.CONFLICT_MESSAGE);
                messageRepository.save(tgMessage);
                break;
            case CREATE_MISSION_MESSAGE:
                tgMessage = messageRepository.findByChatIdAndFactionAndTypeMessage(message.getChatId(), faction, TypeMessage.MISSION_MESSAGE);
                if (tgMessage == null) {
                    tgMessage = new TgMessage();
                    tgMessage.setChatId(message.getChatId());
                    tgMessage.setMessageId(message.getMessageId());
                    tgMessage.setFaction(faction);
                    tgMessage.setTypeMessage(TypeMessage.MISSION_MESSAGE);
                    messageRepository.save(tgMessage);
                }
                break;
        }
    }
}
