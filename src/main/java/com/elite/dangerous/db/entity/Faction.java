package com.elite.dangerous.db.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "faction")
public class Faction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Faction() {}

    public Faction(String name) {
        this.name = name;
    }
}
