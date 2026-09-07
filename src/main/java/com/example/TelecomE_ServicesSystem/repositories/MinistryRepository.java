package com.example.TelecomE_ServicesSystem.repositories;

import com.example.TelecomE_ServicesSystem.entites.Ministry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.math.BigDecimal;
import java.util.List;

@Repository
public interface MinistryRepository extends JpaRepository<Ministry, Long> {
    @Query("SELECT x FROM Ministry x WHERE x.isActive=true")
    List<Ministry> getAllMinistry();

    @Query("SELECT x FROM Ministry x WHERE x.isActive=true AND x.id=:id")
    Ministry getById(@Param("id") Long id);

}
