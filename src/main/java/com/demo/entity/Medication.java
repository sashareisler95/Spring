package com.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "medication")
public class Medication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    public String name;
    @Column(name = "last_date")
    public String lastDate;
    @Column(name = "next_date")
    public String nextDate;
    public Long interval;

}
