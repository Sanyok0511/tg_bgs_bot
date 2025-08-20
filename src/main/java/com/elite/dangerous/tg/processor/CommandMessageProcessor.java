package com.elite.dangerous.tg.processor;

import com.elite.dangerous.db.ApplicationSettingName;
import com.elite.dangerous.db.entity.ApplicationSetting;
import com.elite.dangerous.db.entity.Faction;
import com.elite.dangerous.tg.dto.NextAction;
import com.elite.dangerous.tg.dto.OutgoingMessages;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.message.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardRow;

import java.util.List;

@Component
public class CommandMessageProcessor {
    private final static String ANSWER_DELETING_FACTION = "Отправьте ответом название удаляемой фракции";
    private final static String ANSWER_ADDING_FACTION = "Отправьте ответом название добавляемой фракции";
    private final static String SETTINGS_SAVED = "Настройки сохранены. ";
    private final static String SELECT_NEXT_ACTION = "Выберите дальнейшее действие";
    private final static String SELECT_ACTION = "Выберите действие";
    private final static String FACTION_NOT_FOUND = "Ошибка удаления. Удаляемая фракция не найдена. ";

    public OutgoingMessages answerToIncomingMessage(Message incomingMessage) {
        String textMessage = incomingMessage.getText();
        System.out.println(textMessage);
        OutgoingMessages outgoingMessages = new OutgoingMessages();
    /*    if (textMessage.startsWith("/settings")) {
            SendMessage message = new SendMessage(incomingMessage.getChatId(), SELECT_ACTION);
            if (incomingMessage.getMessageThreadId() != null) {
                message.setMessageThreadId(incomingMessage.getMessageThreadId());
            }
            message.setReplyMarkup(createButtons());
            outgoingMessages.setMessage(message);
        } else if (incomingMessage.getReplyToMessage() != null) {
            Message repliedMessage = incomingMessage.getReplyToMessage();
            String repliedMessageText = repliedMessage.getText();
            if (ANSWER_ADDING_FACTION.startsWith(repliedMessageText)) {
                ApplicationSetting applicationSetting = applicationSetttingsDao.findByNameAndValueAndChatId(ApplicationSettingName.FACTION, textMessage, incomingMessage.getChatId());
                if (applicationSetting == null) {
                    applicationSetting = new ApplicationSetting();
                }
                applicationSetting.setName(ApplicationSettingName.FACTION);
                applicationSetting.setValue(textMessage);
                applicationSetting.setChatId(repliedMessage.getChatId());
                if (repliedMessage.getMessageThreadId() != null) {
                    applicationSetting.setMessageThreadId(repliedMessage.getMessageThreadId());
                }
                applicationSetttingsDao.save(applicationSetting);
                EditMessageText editMessageText = new EditMessageText();
                editMessageText.setText(SETTINGS_SAVED + SELECT_NEXT_ACTION);
                editMessageText.setChatId(repliedMessage.getChatId());
                editMessageText.setMessageId(repliedMessage.getMessageId());
                editMessageText.setReplyMarkup(createButtons());
                outgoingMessages.setMessage(editMessageText);
            } else if (ANSWER_DELETING_FACTION.startsWith(repliedMessageText)) {
                ApplicationSetting applicationSetting = applicationSetttingsDao.findByNameAndValueAndChatId(ApplicationSettingName.FACTION, textMessage, incomingMessage.getChatId());
                if (applicationSetting != null) {
                    applicationSetttingsDao.remove(applicationSetting);
                    EditMessageText editMessageText = new EditMessageText();
                    editMessageText.setText(SETTINGS_SAVED + SELECT_NEXT_ACTION);
                    editMessageText.setChatId(incomingMessage.getChatId());
                    editMessageText.setMessageId(incomingMessage.getMessageId());
                    editMessageText.setReplyMarkup(createButtons());
                    outgoingMessages.setMessage(editMessageText);
                } else {
                    EditMessageText editMessageText = new EditMessageText();
                    editMessageText.setText(FACTION_NOT_FOUND + SELECT_NEXT_ACTION);
                    editMessageText.setChatId(repliedMessage.getChatId());
                    editMessageText.setMessageId(repliedMessage.getMessageId());
                    editMessageText.setReplyMarkup(createButtons());
                    outgoingMessages.setMessage(editMessageText);
                }
            }
        }// else if (incomingMessage)

      */
        return outgoingMessages;
    }

    private InlineKeyboardMarkup createButtons() {
        InlineKeyboardRow listEditFactionActionButtons = new InlineKeyboardRow();
        listEditFactionActionButtons.add(new InlineKeyboardButton("Добавить фракцию",null, "addFaction", null, null, null, null, null, null, null, null));
        listEditFactionActionButtons.add(new InlineKeyboardButton("Удалить фракцию",null, "deleteFaction", null, null, null, null, null, null, null, null));

        InlineKeyboardRow listEditMessageFactionButtons = new InlineKeyboardRow();
        listEditMessageFactionButtons.add(new InlineKeyboardButton("Добавить статистику влияния",null, "addInfluenceMessage", null, null, null, null, null, null, null, null));
        listEditMessageFactionButtons.add(new InlineKeyboardButton("Удалить статистику влияния",null, "deleteInfluenceMessage", null, null, null, null, null, null, null, null));

        InlineKeyboardRow listEditMessageMissionButtons = new InlineKeyboardRow();
        listEditMessageMissionButtons.add(new InlineKeyboardButton("Показать миссии",null, "addMissionMessage", null, null, null, null, null, null, null, null));
        listEditMessageMissionButtons.add(new InlineKeyboardButton("Удалить миссии",null, "deleteMissionMessage", null, null, null, null, null, null, null, null));

        InlineKeyboardRow listEditConflictMissionButtons = new InlineKeyboardRow();
        listEditConflictMissionButtons.add(new InlineKeyboardButton("Показать миссии",null, "addMissionMessage", null, null, null, null, null, null, null, null));
        listEditConflictMissionButtons.add(new InlineKeyboardButton("Удалить миссии",null, "deleteMissionMessage", null, null, null, null, null, null, null, null));

        InlineKeyboardRow closeButtonRow = new InlineKeyboardRow(new InlineKeyboardButton("Удалить это сообщение",null, "deleteMessage", null, null, null, null, null, null, null, null));

        return new InlineKeyboardMarkup(List.of(listEditFactionActionButtons, listEditMessageFactionButtons, listEditMessageMissionButtons, closeButtonRow));
    }

    public OutgoingMessages processCallback(CallbackQuery callbackQuery) {
        Message message = (Message) callbackQuery.getMessage();
        OutgoingMessages outgoingMessages = new OutgoingMessages();
        String command = callbackQuery.getData();


//        switch (command) {
//            case "addInfluenceMessage": {
//                Long chatId = callbackQuery.getMessage().getChatId();
//                List<ApplicationSetting> settings = applicationSetttingsDao.findFactionByChatId(chatId);
//                if (!settings.isEmpty()) {
//                    outgoingMessages.setNextAction(NextAction.CREATE_INFLUENCE_MESSAGE);
//                    settings.forEach(setting -> {
//                        if (setting.getName().equals(ApplicationSettingName.FACTION)) {
//                            Faction faction = factionDao.findFactionByName(setting.getValue());
//                            if (faction == null) {
//                                faction = new Faction();
//                                faction.setName(setting.getValue());
//                                factionDao.save(faction);
//                            }
//                            outgoingMessages.setMessage(influenceMessageCreator.createMessage(setting), faction);
//                        }
//                    });
//                } else {
//                    AnswerCallbackQuery query = new AnswerCallbackQuery(callbackQuery.getId());
//                    query.setText("Для данного канала не выбрана отлеживаемая фракция");
//                    outgoingMessages.setMessage(query);
//                }
//            }
//            break;
//            case "addFaction": {
//                EditMessageText editMessageText = new EditMessageText(ANSWER_ADDING_FACTION);
//                editMessageText.setMessageId(message.getMessageId());
//                editMessageText.setChatId(message.getChatId());
//                outgoingMessages.setMessage(editMessageText);
//            }
//            break;
//            case "deleteFaction": {
//                EditMessageText editMessageText = new EditMessageText(ANSWER_DELETING_FACTION);
//                editMessageText.setMessageId(message.getMessageId());
//                editMessageText.setChatId(message.getChatId());
//                outgoingMessages.setMessage(editMessageText);
//            }
//            break;
//            case "deleteMessage": {
//                DeleteMessage deleteMessage = new DeleteMessage(message.getChatId().toString(), message.getMessageId());
//                outgoingMessages.setMessage(deleteMessage);
//            }
//            break;
//        }

        return outgoingMessages;
    }
}
