package com.elite.dangerous.db.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@Entity
@Table(name = "influence")
public class Influence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "faction_id", nullable = false, foreignKey = @ForeignKey(name = "FK_influence_faction"))
    private Faction faction;
    @ManyToOne
    @JoinColumn(name = "star_system_id", nullable = false, foreignKey = @ForeignKey(name = "FK_influence_star_system"))
    private StarSystem starSystem;
    private Float influence;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime lastUpdate;
}
