package com.elite.dangerous.db.entity;

import lombok.Data;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "influence")
public class Influence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "faction_id", nullable = false, foreignKey = @ForeignKey(name = "FK_influence_faction"))
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Faction faction;
    @ManyToOne
    @JoinColumn(name = "star_system_id", nullable = false, foreignKey = @ForeignKey(name = "FK_influence_star_system"))
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private StarSystem starSystem;
    private Float influence;
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdate;
}
