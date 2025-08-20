package com.elite.dangerous.db.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "commander")
public class Commander {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
}

