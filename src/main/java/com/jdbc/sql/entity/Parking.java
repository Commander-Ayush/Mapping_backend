package com.jdbc.sql.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Parking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String parkingSpot;

    @OneToOne
    @JoinColumn(name="employee_id")
    @JsonIgnoreProperties("parkingSpot")
    private Employee employee;
}
