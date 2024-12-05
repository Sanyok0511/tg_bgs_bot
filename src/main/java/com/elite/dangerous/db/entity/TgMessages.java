package com.elite.dangerous.db.entity;

import javax.persistence.*;

@Entity
public class TgMessages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "message_id")
    private Long messageId;
    @Column(name = "chat_id")
    private Long chatId;
    //private TypeMessage typeMessage;

}
