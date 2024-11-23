package com.arkadiy.microwave.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "mode")
public class Mode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private int power;
    @Column(nullable = false)
    private int duration;
}
