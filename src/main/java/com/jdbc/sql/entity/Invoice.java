package com.jdbc.sql.entity;

import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;

@Entity
@Data
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "invoice")
    List<Service> services = new ArrayList<>();
}
