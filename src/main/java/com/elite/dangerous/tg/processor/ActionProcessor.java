package com.elite.dangerous.tg.processor;

import com.elite.dangerous.db.repository.TgMessageRepository;
import com.elite.dangerous.tg.dto.NextAction;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;

@Service
public class ActionProcessor {
    TgMessageRepository messageRepository;
    public ActionProcessor (TgMessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }
    public void processAction(NextAction action, Message message) {
        if (NextAction.CREATE_INFLUENCE_MESSAGE.equals(action)) {

        }
    }
}
