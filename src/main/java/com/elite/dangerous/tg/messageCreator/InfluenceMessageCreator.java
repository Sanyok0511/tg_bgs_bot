package com.elite.dangerous.tg.messageCreator;

import com.elite.dangerous.db.dao.FactionDao;
import com.elite.dangerous.db.dao.InfluenceDao;
import com.elite.dangerous.db.entity.ApplicationSetting;
import com.elite.dangerous.db.entity.Faction;
import com.elite.dangerous.db.entity.Influence;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.*;

@Service
public class InfluenceMessageCreator {
    private InfluenceDao influenceDao;
    private FactionDao factionDao;

    public InfluenceMessageCreator(InfluenceDao influenceDao, FactionDao factionDao) {
        this.influenceDao = influenceDao;
        this.factionDao = factionDao;
    }
    public SendMessage createMessage(List<ApplicationSetting> settings) {
        Long chatId = null;
        Integer threadChatId = null;
        List<FactionInfluence> influenceList = new ArrayList<>();
        for (ApplicationSetting setting : settings) {
            if (chatId == null) {
                chatId = setting.getChatId();
            }
            if (threadChatId == null) {
                threadChatId = setting.getMessageThreadId();
            }
            Faction faction = factionDao.findFactionByName(setting.getValue());
            FactionInfluence factionInfluence = new FactionInfluence(faction.getName());
            List<Influence> influences = influenceDao.findAllByFaction(faction);
            for (Influence influence : influences) {
                factionInfluence.getStarSystemInfluences()
                        .put(
                                influence.getStarSystem().getName(),
                                String.format("%.2f", influence.getInfluence() * 100)
                        );

            }
            influenceList.add(factionInfluence);
        }

        SendMessage sendMessage = new SendMessage();
        StringBuilder stringBuilder = new StringBuilder();
        for (FactionInfluence factionInfluence : influenceList) {
            stringBuilder.append(factionInfluence.toString());
        }

        sendMessage.setText(stringBuilder.toString());
        sendMessage.setChatId(chatId);
        sendMessage.setParseMode("html");
        if (threadChatId != null) {
            sendMessage.setMessageThreadId(threadChatId);
        }
        return sendMessage;
    }

    class FactionInfluence {
        private String faction;
        private Map<String, String> starSystemInfluences = new TreeMap<>();

        private FactionInfluence(String faction) {
            this.faction = faction;
        }

        private Map<String, String> getStarSystemInfluences() {
            return starSystemInfluences;
        }

        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder("<b>" + faction + "</b>");
            for (Map.Entry<String, String> entry : starSystemInfluences.entrySet()) {
                stringBuilder.append("\r\n");
                stringBuilder.append("- ");
                stringBuilder.append(entry.getKey());
                stringBuilder.append(": ");
                stringBuilder.append(entry.getValue());
            }
            return stringBuilder.toString();
        }
    }
}
