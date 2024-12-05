package com.elite.dangerous.tg.messageCreator;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class SendMessageCreator {
    private ConflictMessageCreator election = null;
    private ConflictMessageCreator war = null;
    public void setWar(ConflictMessageCreator war) {
        this.war = war;
    }

    public void setElection(ConflictMessageCreator election) {
        this.election = election;
    }

    public SendMessage createMessage(String type,String systemName,Long chatId) {
        switch (type) {
            case "War" :
            case "CivilWar" :
                return war.createMessage(systemName, chatId);
            case "Election":
                return election.createMessage(systemName, chatId);
        }
        return null;
    }
}
