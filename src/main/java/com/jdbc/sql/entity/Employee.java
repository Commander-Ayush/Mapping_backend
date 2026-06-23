package com.jdbc.sql.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstName;
    private String department;

    @OneToOne(mappedBy="employee", cascade = CascadeType.ALL)
    private Parking parkingSpot;


}
