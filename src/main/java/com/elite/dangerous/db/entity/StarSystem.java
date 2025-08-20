package com.elite.dangerous.db.entity;

import lombok.Data;

import jakarta.persistence.*;

@Data
@Entity
@Table(name = "star_system")
public class StarSystem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;

    public StarSystem() {}

    public StarSystem(String name) {
        this.name = name;
    }
}
