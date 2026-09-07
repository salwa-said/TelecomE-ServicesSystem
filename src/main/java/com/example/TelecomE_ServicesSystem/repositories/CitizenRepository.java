package com.example.TelecomE_ServicesSystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.math.BigDecimal;
import java.util.List;
import com.example.TelecomE_ServicesSystem.entites.*;

@Repository

public interface CitizenRepository extends JpaRepository<Citizen, Long> {
    @Query("SELECT x FROM Citizen x WHERE x.isActive=true")
    List<Citizen> getAllCitizen();

    @Query("SELECT x FROM Citizen x WHERE x.isActive=true AND x.id=:id")
    Citizen getById(@Param("id") Long id);

}