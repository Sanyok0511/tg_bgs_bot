package com.elite.dangerous.db.repository;

import com.elite.dangerous.db.entity.Mission;
import com.elite.dangerous.db.entity.MissionSummary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MissionRepository extends CrudRepository<Mission, Long> {
    String query = """
            SELECT s.name as systemName,
                   sum(case when m.status = 'MissionCompleted' then m.influence else 0 end) as completed,
                   sum(case when m.status = 'MissionAccepted' then m.influence else 0 end) as accepted,
                   sum(case when m.status = 'MissionFailed' then m.influence else 0 end) as failed,
                   DATE(m.last_update)
            FROM mission m
                     JOIN faction f ON m.faction_id = f.id
                     JOIN star_system s ON m.star_system_id = s.id
            WHERE DATE(m.last_update) = current_date AND f.name = ?1
            GROUP BY s.name, f.name, DATE(m.last_update)
            """;

    @Query(nativeQuery = true, value = query)
    List<MissionSummary> getMissionSummaryByFaction(String faction);

    Mission findMissionByMissionId(String missionId);
}
