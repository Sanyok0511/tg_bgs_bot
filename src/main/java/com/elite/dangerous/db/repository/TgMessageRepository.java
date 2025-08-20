package com.elite.dangerous.db.repository;

import com.elite.dangerous.db.TypeMessage;
import com.elite.dangerous.db.entity.Faction;
import com.elite.dangerous.db.entity.TgMessage;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;

@Lazy
public interface TgMessageRepository extends JpaRepository<TgMessage, Long> {
//    String messagesForUpdate = """
//            SELECT distinct tm
//            FROM TgMessage tm
//             join Faction f ON tm.faction = f
//             join StarSystem ss ON ss.id = t не понятно что тута
//            WHERE ss.name = ?1
//            """;
    TgMessage findByChatIdAndFaction(Long chatId, Faction factionDto);
    TgMessage findByChatIdAndFactionAndTypeMessage(Long chatId, Faction factionDto, TypeMessage typeMessage);

    // TODO: вспомнить, какого хрена тут StarSystem
//    @Query(value = messagesForUpdate)
//    List<TgMessage> findMessageForUpdate(String starSystem);
}
