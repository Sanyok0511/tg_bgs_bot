package com.elite.dangerous.tg.processor;

import com.elite.dangerous.db.ApplicationSettingName;
import com.elite.dangerous.db.dao.ApplicationSetttingsDao;
import com.elite.dangerous.db.entity.ApplicationSetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommandMessageProcessor {
    private final static String ANSWER_DELETING_FACTION = "Отправьте ответом название добавляемой фракции";
    private final static String ANSWER_ADDING_FACTION = "Отправьте ответом название добавляемой фракции";

    private ApplicationSetttingsDao applicationSetttingsDao;

    @Autowired
    public CommandMessageProcessor(ApplicationSetttingsDao applicationSetttingsDao) {
        this.applicationSetttingsDao = applicationSetttingsDao;
    }

    public SendMessage answerToIncomingMessage(Message message) {
        String textMessage = message.getText();
        System.out.println(textMessage);
        SendMessage outgoingMessage = null;
        if (textMessage.startsWith("/settings")) {
            outgoingMessage = new SendMessage();

            outgoingMessage.setText("Выберите действие");
            outgoingMessage.setChatId(message.getChatId());
            if (message.getMessageThreadId() != null) {
                outgoingMessage.setMessageThreadId(message.getMessageThreadId());
            }

            List<InlineKeyboardButton> listEditFactionActionButtons = new ArrayList<>();
            listEditFactionActionButtons.add(new InlineKeyboardButton("Добавить фракцию",null, "addFaction", null, null, null, null, null, null));
            listEditFactionActionButtons.add(new InlineKeyboardButton("Удалить фракцию",null, "deleteFaction", null, null, null, null, null, null));

            List<InlineKeyboardButton> listEditMessageFactionButtons = new ArrayList<>();
            listEditMessageFactionButtons.add(new InlineKeyboardButton("Добавить статистику влияния",null, "addInfluenceMessage", null, null, null, null, null, null));
            listEditMessageFactionButtons.add(new InlineKeyboardButton("Удалить статистику влияния",null, "deleteInfluenceMessage", null, null, null, null, null, null));

            List<InlineKeyboardButton> listEditMessageMissionButtons = new ArrayList<>();
            listEditMessageMissionButtons.add(new InlineKeyboardButton("Показать миссии",null, "addMissionMessage", null, null, null, null, null, null));
            listEditMessageMissionButtons.add(new InlineKeyboardButton("Удалить миссии",null, "deleteMissionMessage", null, null, null, null, null, null));

            InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup(List.of(listEditFactionActionButtons, listEditMessageFactionButtons, listEditMessageMissionButtons));

            outgoingMessage.setReplyMarkup(keyboardMarkup);
        } else if (message.getReplyToMessage() != null) {
            Message repliedMessage = message.getReplyToMessage();
            String repliedMessageText = repliedMessage.getText();
            if (ANSWER_ADDING_FACTION.startsWith(repliedMessageText)) {
                ApplicationSetting applicationSetting = new ApplicationSetting();
                applicationSetting.setName(ApplicationSettingName.FACTION);
                applicationSetting.setValue(textMessage);
                applicationSetting.setChatId(repliedMessage.getChatId());
                if (repliedMessage.getMessageThreadId() != null) {
                    applicationSetting.setMessageThreadId(repliedMessage.getMessageThreadId());
                }
                applicationSetttingsDao.save(applicationSetting);
            }
        }
        return outgoingMessage;
    }

    public AnswerCallbackQuery processCallback(CallbackQuery callbackQuery) {
        Message message = (Message) callbackQuery.getMessage();
        String command = callbackQuery.getData();
        AnswerCallbackQuery query = new AnswerCallbackQuery();

        switch (command) {
            case "addInfluenceMessage":

            case "addFaction":
                query.setCallbackQueryId(callbackQuery.getId());
                query.setText(ANSWER_ADDING_FACTION);
                break;
            case "deleteFaction":
                query.setText(ANSWER_DELETING_FACTION);
                query.setCallbackQueryId(callbackQuery.getId());
                break;
            default:
        }

        return query;
    }
}
