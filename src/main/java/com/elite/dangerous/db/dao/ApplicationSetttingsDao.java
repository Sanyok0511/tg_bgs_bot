package com.elite.dangerous.db.dao;

import com.elite.dangerous.db.ApplicationSettingName;
import com.elite.dangerous.db.entity.ApplicationSetting;
import com.elite.dangerous.db.repository.ApplicationSettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ApplicationSetttingsDao {
    private ApplicationSettingsRepository applicationSettingsRepository;

    @Autowired
    public ApplicationSetttingsDao(ApplicationSettingsRepository applicationSettingsRepository) {
        this.applicationSettingsRepository = applicationSettingsRepository;
    }

    public List<ApplicationSetting> findByName(ApplicationSettingName name) {
        return applicationSettingsRepository.findByName(name);
    }

    public List<ApplicationSetting> findFactionByChatId(Long chatId) {
        return applicationSettingsRepository.findByChatIdAndName(chatId, ApplicationSettingName.FACTION);
    }


    public ApplicationSetting findByNameAndValue(ApplicationSettingName name, String value) {
        return applicationSettingsRepository.findByNameAndValue(name, value);
    }

    public ApplicationSetting findByNameAndChatId(ApplicationSettingName name, Long chatId) {
        return applicationSettingsRepository.findByNameAndChatId(name, chatId);
    }
    public ApplicationSetting findByNameAndValueAndChatId(ApplicationSettingName name, String value, Long chatId) {
        return applicationSettingsRepository.findByNameAndValueAndChatId(name, value, chatId);
    }
    public void save(ApplicationSetting setting) {
        applicationSettingsRepository.save(setting);
    }
    public void remove(ApplicationSetting setting) {
        applicationSettingsRepository.delete(setting);
    }
}
