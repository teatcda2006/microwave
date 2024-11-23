package com.arkadiy.microwave.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "history")
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private int power;
    @Column(nullable = false)
    private int Duration;
    @Column(updatable = false)
    private LocalDateTime usageData;

    @PrePersist
    protected void onCreate() {
        this.usageData = LocalDateTime.now();
    }
}
