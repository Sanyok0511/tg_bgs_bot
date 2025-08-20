package com.elite.dangerous.tg;

import lombok.AllArgsConstructor;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.methods.reactions.SetMessageReaction;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.reactions.ReactionTypeEmoji;

import java.util.List;

@AllArgsConstructor
public class TelegramBotReceiver implements LongPollingSingleThreadUpdateConsumer {
    private TelegramBotSender sender;
    @Override
    public void consume(List<Update> updates) {
        LongPollingSingleThreadUpdateConsumer.super.consume(updates);
    }

    @Override
    public void consume(Update update) {
        //System.out.println(update);
        var reaction = ReactionTypeEmoji.builder().emoji("🤩").build();
        sender.sendMessage(new SetMessageReaction(update.getMessage().getChatId().toString(), update.getMessage().getMessageId(), List.of(reaction), false));
    }
}
