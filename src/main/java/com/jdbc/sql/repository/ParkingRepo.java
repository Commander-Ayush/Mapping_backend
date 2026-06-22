package com.jdbc.sql.repository;

import com.jdbc.sql.entity.Parking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingRepo extends JpaRepository<Parking, Integer> {
}
