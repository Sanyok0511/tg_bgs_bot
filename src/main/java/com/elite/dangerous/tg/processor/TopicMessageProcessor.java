package com.elite.dangerous.tg.processor;

import com.elite.dangerous.db.ApplicationSettingName;
import com.elite.dangerous.db.entity.ApplicationSetting;
import com.elite.dangerous.db.repository.ApplicationSettingsRepository;
import com.elite.dangerous.tg.preview.InfluencePreviewMaker;
import com.elite.dangerous.tg.dto.InfluenceDisplay;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.message.Message;

@Component
public class TopicMessageProcessor {
    //TODO: весь сервис проверить!!!
    private ApplicationSettingsRepository applicationSettingsRepository;
    private InfluencePreviewMaker preparator;

    public SendMessage createInfluenceMessage(Message message) {
        Long messageId = message.getChatId();
        ApplicationSetting setting = applicationSettingsRepository.findByNameAndChatId(ApplicationSettingName.FACTION, messageId);
        InfluenceDisplay influenceDisplayList = preparator.prepareDataToDisplayInfluence(setting.getValue());
        return new SendMessage(Long.toString(messageId), influenceDisplayList.toString());
    }

    public EditMessageText updateInfluenceMessage() {
        //EditMessageText editMessageText = new EditMessageText();

        return null;
    }

    public SendMessage createConflictMessage() {
        return null;
    }

    public EditMessageText updateConflictMessage() {
        return null;
    }
}
