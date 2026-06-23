package com.jdbc.sql.controller;

import com.jdbc.sql.entity.Doctor;
import com.jdbc.sql.entity.Patient;
import com.jdbc.sql.repository.DoctorRepo;
import com.jdbc.sql.repository.PatientRepo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DocPatientController {

    private final PatientRepo patientRepo;
    private final DoctorRepo doctorRepo;

    public DocPatientController(PatientRepo patientRepo, DoctorRepo doctorRepo) {
        this.patientRepo = patientRepo;
        this.doctorRepo = doctorRepo;
    }

    @GetMapping("/patients")
    public List<Patient> getAllPatients(){
        return patientRepo.findAll();
    }

    @GetMapping("/doctors")
    public List<Doctor> getAllDoctors(){
        return doctorRepo.findAll();
    }

    @PostMapping("/patient")
    public Patient addPatient(@RequestBody Patient patient){
        return patientRepo.save(patient);
    }
}
