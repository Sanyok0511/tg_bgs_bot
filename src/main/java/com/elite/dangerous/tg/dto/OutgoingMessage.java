package com.elite.dangerous.tg.dto;

import lombok.Data;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

@Data
public class OutgoingMessage {
    BotApiMethod message;
    NextAction nextAction;
}
