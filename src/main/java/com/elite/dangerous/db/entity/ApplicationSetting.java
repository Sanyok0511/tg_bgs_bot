package com.elite.dangerous.db.entity;

import com.elite.dangerous.db.ApplicationSettingName;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class ApplicationSetting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ApplicationSettingName name;
    @Column(nullable = false)
    private String value;
    @Column(name = "chat_id", nullable = false)
    private Long chatId;
    @Column(name = "message_thread_id")
    private Integer messageThreadId;

}
