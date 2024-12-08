package com.elite.dangerous.db.entity;

import lombok.Data;
import com.elite.dangerous.db.StatusMission;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Mission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "faction_id", nullable = false, foreignKey = @ForeignKey(name = "FK_mission_faction"))
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Faction faction;
    @ManyToOne
    @JoinColumn(name = "star_system_id", nullable = false, foreignKey = @ForeignKey(name = "FK_mission_star_system"))
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private StarSystem starSystem;
    @ManyToOne
    @JoinColumn(name = "destination_faction_id", nullable = false, foreignKey = @ForeignKey(name = "FK_mission_destination_faction"))
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Faction destinationFaction;
    @ManyToOne
    @JoinColumn(name = "destination_star_system_id", nullable = false, foreignKey = @ForeignKey(name = "FK_mission_destination_star_system"))
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private StarSystem destinationStarSystem;
    private Byte influence;
    @Transient
    private StatusMission status;
    @Column(name = "status")
    private String statusValue;
    private String missionId;
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdate;

    @PostLoad
    void fillTransient() {
        if (statusValue != null) {
            this.status = StatusMission.of(statusValue);
        }
    }


    @PrePersist
    void fillPersistent() {
        if (status != null) {
            this.statusValue = status.getStatus();
        }
    }

    @PreUpdate
    void onUpdate() {
        if (status != null) {
            this.statusValue = status.getStatus();
        }
    }

    @Override
    public String toString() {
        return "Mission{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", faction=" + faction +
                ", starSystem=" + starSystem +
                ", destinationFaction=" + destinationFaction +
                ", destinationStarSystem=" + destinationStarSystem +
                ", influence=" + influence +
                ", status=" + status +
                ", missionId='" + missionId + '\'' +
                ", lastUpdate=" + lastUpdate +
                '}';
    }
}
