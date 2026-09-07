package com.example.TelecomE_ServicesSystem.repositories;
import com.example.TelecomE_ServicesSystem.entites.Inspection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.math.BigDecimal;
import java.util.List;

@Repository
public interface InspectionRepository extends JpaRepository<Inspection, Long> {
    @Query("SELECT x FROM Inspection x WHERE x.isActive=true")
    List<Inspection> getAllInspection();

    @Query("SELECT x FROM Inspection x WHERE x.isActive=true AND x.id=:id")
    Inspection getById(@Param("id") Long id);

}