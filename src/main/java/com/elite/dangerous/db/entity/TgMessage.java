package com.elite.dangerous.db.entity;

import com.elite.dangerous.db.TypeMessage;

import javax.persistence.*;

@Entity
public class TgMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "message_id")
    private Long messageId;
    @Column(name = "chat_id")
    private Long chatId;
    @ManyToOne
    @JoinColumn(name = "faction_id", nullable = false, foreignKey = @ForeignKey(name = "FK_messages_faction"))
    private Faction faction;
    @Column(name = "type_message")
    @Enumerated(EnumType.STRING)
    private TypeMessage typeMessage;

}
