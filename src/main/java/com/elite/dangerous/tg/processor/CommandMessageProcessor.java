package com.elite.dangerous.tg.processor;

import com.elite.dangerous.db.ApplicationSettingName;
import com.elite.dangerous.db.dao.ApplicationSetttingsDao;
import com.elite.dangerous.db.entity.ApplicationSetting;
import com.elite.dangerous.tg.dto.NextAction;
import com.elite.dangerous.tg.dto.OutgoingMessage;
import com.elite.dangerous.tg.messageCreator.InfluenceMessageCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommandMessageProcessor {
    private final static String ANSWER_DELETING_FACTION = "Отправьте ответом название удаляемой фракции";
    private final static String ANSWER_ADDING_FACTION = "Отправьте ответом название добавляемой фракции";
    private final static String SETTINGS_SAVED = "Настройки сохранены. ";
    private final static String SELECT_NEXT_ACTION = "Выберите дальнейшее действие";
    private final static String SELECT_ACTION = "Выберите действие";
    private final static String FACTION_NOT_FOUND = "Ошибка удаления. Удаляемая фракция не найдена. ";


    private ApplicationSetttingsDao applicationSetttingsDao;
    private InfluenceMessageCreator influenceMessageCreator;

    @Autowired
    public CommandMessageProcessor(ApplicationSetttingsDao applicationSetttingsDao, InfluenceMessageCreator influenceMessageCreator) {
        this.applicationSetttingsDao = applicationSetttingsDao;
        this.influenceMessageCreator = influenceMessageCreator;
    }

    public OutgoingMessage answerToIncomingMessage(Message incomingMessage) {
        String textMessage = incomingMessage.getText();
        System.out.println(textMessage);
        OutgoingMessage outgoingMessage = new OutgoingMessage();
        if (textMessage.startsWith("/settings")) {
            SendMessage message = new SendMessage();

            message.setText(SELECT_ACTION);
            message.setChatId(incomingMessage.getChatId());
            if (incomingMessage.getMessageThreadId() != null) {
                message.setMessageThreadId(incomingMessage.getMessageThreadId());
            }
            message.setReplyMarkup(createButtons());
            outgoingMessage.setMessage(message);
        } else if (incomingMessage.getReplyToMessage() != null) {
            Message repliedMessage = incomingMessage.getReplyToMessage();
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
                EditMessageText editMessageText = new EditMessageText();
                editMessageText.setText(SETTINGS_SAVED + SELECT_NEXT_ACTION);
                editMessageText.setChatId(incomingMessage.getChatId());
                editMessageText.setReplyMarkup(createButtons());
                outgoingMessage.setMessage(editMessageText);
            } if (ANSWER_DELETING_FACTION.startsWith(repliedMessageText)) {
                ApplicationSetting applicationSetting = applicationSetttingsDao.findByNameAndValueAndChatId(ApplicationSettingName.FACTION, textMessage, incomingMessage.getChatId());
                if (applicationSetting != null) {
                    applicationSetttingsDao.remove(applicationSetting);
                    EditMessageText editMessageText = new EditMessageText();
                    editMessageText.setText(SETTINGS_SAVED + SELECT_NEXT_ACTION);
                    editMessageText.setChatId(incomingMessage.getChatId());
                    editMessageText.setReplyMarkup(createButtons());
                    outgoingMessage.setMessage(editMessageText);
                } else {
                    EditMessageText editMessageText = new EditMessageText();
                    editMessageText.setText(FACTION_NOT_FOUND + SELECT_NEXT_ACTION);
                    editMessageText.setChatId(incomingMessage.getChatId());
                    editMessageText.setReplyMarkup(createButtons());
                    outgoingMessage.setMessage(editMessageText);
                }
            }
        }// else if (incomingMessage)
        return outgoingMessage;
    }

    private InlineKeyboardMarkup createButtons() {
        List<InlineKeyboardButton> listEditFactionActionButtons = new ArrayList<>();
        listEditFactionActionButtons.add(new InlineKeyboardButton("Добавить фракцию",null, "addFaction", null, null, null, null, null, null));
        listEditFactionActionButtons.add(new InlineKeyboardButton("Удалить фракцию",null, "deleteFaction", null, null, null, null, null, null));

        List<InlineKeyboardButton> listEditMessageFactionButtons = new ArrayList<>();
        listEditMessageFactionButtons.add(new InlineKeyboardButton("Добавить статистику влияния",null, "addInfluenceMessage", null, null, null, null, null, null));
        listEditMessageFactionButtons.add(new InlineKeyboardButton("Удалить статистику влияния",null, "deleteInfluenceMessage", null, null, null, null, null, null));

        List<InlineKeyboardButton> listEditMessageMissionButtons = new ArrayList<>();
        listEditMessageMissionButtons.add(new InlineKeyboardButton("Показать миссии",null, "addMissionMessage", null, null, null, null, null, null));
        listEditMessageMissionButtons.add(new InlineKeyboardButton("Удалить миссии",null, "deleteMissionMessage", null, null, null, null, null, null));

        List<InlineKeyboardButton> closeButton = List.of(new InlineKeyboardButton("Удалить это сообщение",null, "deleteMessage", null, null, null, null, null, null));

        return new InlineKeyboardMarkup(List.of(listEditFactionActionButtons, listEditMessageFactionButtons, listEditMessageMissionButtons, closeButton));
    }

    public OutgoingMessage processCallback(CallbackQuery callbackQuery) {
        Message message = (Message) callbackQuery.getMessage();
        OutgoingMessage outgoingMessage = new OutgoingMessage();
        String command = callbackQuery.getData();


        switch (command) {
            case "addInfluenceMessage": {
                Long chatId = callbackQuery.getMessage().getChatId();
                List<ApplicationSetting> settings = applicationSetttingsDao.findFactionByChatId(chatId);
                if (!settings.isEmpty()) {
                    outgoingMessage.setMessage(influenceMessageCreator.createMessage(settings));
                    outgoingMessage.setNextAction(NextAction.CREATE_INFLUENCE_MESSAGE);
                } else {
                    AnswerCallbackQuery query = new AnswerCallbackQuery();
                    query.setCallbackQueryId(callbackQuery.getId());
                    query.setText("Для данного канала не выбрана отлеживаемая фракция");
                    outgoingMessage.setMessage(query);
                }
            }
            break;
            case "addFaction": {
                EditMessageText editMessageText = new EditMessageText();
                editMessageText.setMessageId(message.getMessageId());
                editMessageText.setText(ANSWER_ADDING_FACTION);
                editMessageText.setChatId(message.getChatId());
                outgoingMessage.setMessage(editMessageText);
            }
            break;
            case "deleteFaction": {
                EditMessageText editMessageText = new EditMessageText();
                editMessageText.setMessageId(message.getMessageId());
                editMessageText.setText(ANSWER_DELETING_FACTION);
                editMessageText.setChatId(message.getChatId());
                outgoingMessage.setMessage(editMessageText);
            }
            break;
            case "deleteMessage": {
                DeleteMessage deleteMessage = new DeleteMessage();
                deleteMessage.setChatId(message.getChatId());
                deleteMessage.setMessageId(message.getMessageId());
                outgoingMessage.setMessage(deleteMessage);
            }
            break;
        }

        return outgoingMessage;
    }
}
