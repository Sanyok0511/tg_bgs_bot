package com.elite.dangerous.tg.messageCreator;

import com.elite.dangerous.api.FactionService;
import com.elite.dangerous.db.entity.ApplicationSetting;
import com.elite.dangerous.db.entity.Faction;
import com.elite.dangerous.db.entity.TgMessage;
import com.elite.dangerous.service.InfluenceServiceImpl;
import com.elite.dangerous.service.TgMessageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class InfluenceMessageCreator {
    private final static DateFormat formater = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
    private final static String HTML = "html";
    private InfluenceServiceImpl influenceServiceImpl;
    private FactionService factionService;
    private TgMessageService tgMessageService;


    public BotApiMethod createMessage(ApplicationSetting setting) {
        BotApiMethod outgoingMessage = null;
        Long chatId = setting.getChatId();
        Integer threadChatId = setting.getMessageThreadId();

        Faction faction = factionService.getOrCreateFaction(setting.getValue());

        String textMessage = generateText(faction);
        //TgMessage tgMessage = tgMessageService.findByChatIdAndFactionAndTypeMessage(chatId, faction, TypeMessage.INFLUENCE_MESSAGE);
        TgMessage tgMessage = new TgMessage(); // Заглушка
        if (tgMessage != null) {
            EditMessageText editMessageText = new EditMessageText(textMessage);
            editMessageText.setMessageId(tgMessage.getMessageId());
            editMessageText.setChatId(chatId);
            editMessageText.setParseMode(HTML);
            outgoingMessage = editMessageText;
        } else {
            SendMessage sendMessage = new SendMessage(chatId.toString(), textMessage);
            sendMessage.setParseMode(HTML);
            if (threadChatId != null) {
                sendMessage.setMessageThreadId(threadChatId);
            }
            outgoingMessage = sendMessage;
        }
        return outgoingMessage;
    }

    public BotApiMethod updateMessage(TgMessage tgMessage) {
        String text = generateText(tgMessage.getFaction());
        EditMessageText editMessageText = new EditMessageText(text);
        editMessageText.setChatId(tgMessage.getChatId());
        editMessageText.setMessageId(tgMessage.getMessageId());
        editMessageText.setParseMode(HTML);
        return editMessageText;
    }

    private String generateText(Faction faction) {
        FactionInfluence factionInfluence = new FactionInfluence(faction.getName());
//        List<Influence> influences = influenceDao.findAllByFaction(faction);
//        for (Influence influence : influences) {
//            factionInfluence.getInfluences()
//                    .add(new InfluenceInformation(
//                            influence.getStarSystem().getName(),
//                            String.format("%.2f", influence.getInfluence() * 100),
//                            formater.format(influence.getLastUpdate())
//                    ));
//        }
        return factionInfluence.toString();
    }

    class FactionInfluence {
        private String faction;
        private List<InfluenceInformation> influences = new ArrayList<>();

        private FactionInfluence(String faction) {
            this.faction = faction;
        }

        private List<InfluenceInformation> getInfluences() {
            return influences;
        }

        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder("<b>" + faction + "</b>\r\n");
            Collections.sort(influences, Comparator.comparing(i -> i.starSystem));
            for (InfluenceInformation influenceInformation : influences) {
                stringBuilder.append("\r\n");
                stringBuilder.append("- ");
                stringBuilder.append(influenceInformation);
            }
            return stringBuilder.toString();
        }
    }

    @AllArgsConstructor
    class InfluenceInformation {
        private String starSystem;
        private String influence;
        private String lastUpdate;

        @Override
        public String toString() {
            return starSystem + " - " + influence + " (" + lastUpdate + ")";
        }
    }
}
