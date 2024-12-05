package com.elite.dangerous.tg.processor;

import com.elite.dangerous.db.ApplicationSettingName;
import com.elite.dangerous.db.dao.ApplicationSetttingsDao;
import com.elite.dangerous.db.entity.ApplicationSetting;
import com.elite.dangerous.tg.preview.InfluencePreviewMaker;
import com.elite.dangerous.tg.dto.InfluenceDisplay;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
public class TopicMessageProcessor {
    private ApplicationSetttingsDao applicationSetttingsDao;
    private InfluencePreviewMaker preparator;

    public SendMessage createInfluenceMessage(Message message) {
        SendMessage outgoingMessage = new SendMessage();
        Long messageId = message.getChatId();
        ApplicationSetting setting = applicationSetttingsDao.findByNameAndChatId(ApplicationSettingName.FACTION, messageId);
        InfluenceDisplay influenceDisplayList = preparator.prepareDataToDisplayInfluence(setting.getValue());
        outgoingMessage.setText(influenceDisplayList.toString());
        outgoingMessage.setChatId(messageId);
        return null;
    }

    public EditMessageText updateInfluenceMessage() {
        EditMessageText editMessageText = new EditMessageText();

        return null;
    }

    public SendMessage createConflictMessage() {
        return null;
    }

    public EditMessageText updateConflictMessage() {
        return null;
    }
}
