package com.elite.dangerous.tg.dto;

import com.elite.dangerous.db.entity.Faction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethod;

import java.util.ArrayList;
import java.util.List;

@Data
public class OutgoingMessages {
    List<MessageHolder> messageHolders;
    @Getter
    private NextAction nextAction;


    public List<MessageHolder> getMessageHolders() {
        if (messageHolders == null) {
            messageHolders = new ArrayList<>();
        }
        return messageHolders;
    }

    public void setMessage(BotApiMethod message) {
        this.setMessage(message, null);
    }

    public void setMessage(BotApiMethod message, Faction faction) {
        getMessageHolders().add(new MessageHolder(message, faction));
    }

    @AllArgsConstructor
    @Data
    public static class MessageHolder {
        private BotApiMethod message;
        private Faction faction;
    }
}
