package com.jdbc.sql.controller;

import com.jdbc.sql.entity.Employee;
import com.jdbc.sql.entity.Parking;
import com.jdbc.sql.repository.EmployeeRepo;
import com.jdbc.sql.repository.ParkingRepo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    private final EmployeeRepo employeeRepo;
    private final ParkingRepo parkingRepo;

    public EmployeeController(EmployeeRepo employeeRepo, ParkingRepo parkingRepo) {
        this.employeeRepo = employeeRepo;
        this.parkingRepo = parkingRepo;
    }

    @GetMapping("/all-employees")
    public List<Employee> getEmployee(){
        return employeeRepo.findAll();
    }

    @GetMapping("/employee/{employee_id}")
    public Employee getEmployee(@PathVariable("employee_id") Integer employee_id){
        return employeeRepo.findById(employee_id).get();
    }

    @GetMapping("/employee-parking/{employee_id}")
    public Parking getEmployeeParking(@PathVariable("employee_id") Integer employee_id){
        return employeeRepo.findById(employee_id).get().getParkingSpot();
    }

    @PostMapping("/employee")
    public String addEmployee(@RequestBody Employee employee){
        if(employee.getParkingSpot()!=null){
            employee.getParkingSpot().setEmployee(employee);
        }
        employeeRepo.save(employee);
        return "success";
    }

    //----------------------Parking------------------------------

    @GetMapping("/parkings")
    public List<Parking> getParking(){
        return parkingRepo.findAll();
    }

    @GetMapping("/parking/{parking-id}")
    public Parking getParking(@PathVariable("parking-id") Integer parking_id){
        return parkingRepo.findById(parking_id).get();
    }

    @GetMapping("/parking-employee/{parking_id}")
    public Employee getParkingEmployee(@PathVariable("parking_id") Integer parking_id){
        return parkingRepo.findById(parking_id).get().getEmployee();
    }

    @PostMapping("/parking")
    public String addParking(@RequestBody Parking park){
        parkingRepo.save(park);
        return "success";
    }

}
