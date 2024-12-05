package com.elite.dangerous.db.entity;

import lombok.Data;
import com.elite.dangerous.db.WarType;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Conflict {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "left_faction_id", nullable = false, foreignKey = @ForeignKey(name = "FK_conflict_rigth_faction"))
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Faction factionLeft;
    @Column(name = "left_won_days")
    private Byte wonDateFactionLeft;
    @ManyToOne
    @JoinColumn(name = "right_faction_id", nullable = false, foreignKey = @ForeignKey(name = "FK_conflict_left_faction"))
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Faction factionRight;
    @Column(name = "right_won_days")
    private Byte wonDateFactionRight;
    @ManyToOne
    @JoinColumn(name = "star_system_id", nullable = false, foreignKey = @ForeignKey(name = "FK_conflict_star_system"))
    private StarSystem starSystem;

    private String status;
    @Transient
    private WarType warType;
    @Column(name = "war_type")
    private String warTypeValue;
    private Date lastUpdate;

    @PostLoad
    void fillTransient() {
        if (warTypeValue != null) {
            this.warType = WarType.of(warTypeValue);
        }
    }

    @PrePersist
    void fillPersistent() {
        if (status != null) {
            this.warTypeValue = warType.getStatus();
        }
    }

    @Override
    public String toString() {
        return "Conflict{" +
                "id=" + id +
                ", factionLeft=" + factionLeft +
                ", wonDateFactionLeft=" + wonDateFactionLeft +
                ", factionRight=" + factionRight +
                ", wonDateFactionRight=" + wonDateFactionRight +
                ", starSystem=" + starSystem +
                ", status='" + status + '\'' +
                ", warType=" + warType +
                ", lastUpdate=" + lastUpdate +
                '}';
    }
}
