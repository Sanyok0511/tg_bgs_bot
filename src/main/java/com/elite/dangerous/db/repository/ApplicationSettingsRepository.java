package com.elite.dangerous.db.repository;

import com.elite.dangerous.db.ApplicationSettingName;
import com.elite.dangerous.db.entity.ApplicationSetting;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ApplicationSettingsRepository extends CrudRepository<ApplicationSetting, Long> {
    @Override
    Iterable<ApplicationSetting> findAll();

    List<ApplicationSetting> findByName(ApplicationSettingName name);
    List<ApplicationSetting> findByChatIdAndName(Long chatId, ApplicationSettingName name);
    ApplicationSetting findByNameAndValue(ApplicationSettingName name, String value);

    ApplicationSetting findByNameAndChatId(ApplicationSettingName name, Long chatId);
    ApplicationSetting findByNameAndValueAndChatId(ApplicationSettingName name, String value, Long chatId);
}
