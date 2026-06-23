package com.jdbc.sql.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String age;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "Patient_Doctor_Table",
            joinColumns = {@JoinColumn(name="patient_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "doctor_id", referencedColumnName = "id")}
    )
    private Set<Doctor> doctors;



}
