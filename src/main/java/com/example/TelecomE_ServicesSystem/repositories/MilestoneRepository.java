package com.example.TelecomE_ServicesSystem.repositories;
import com.example.TelecomE_ServicesSystem.entites.Milestone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.math.BigDecimal;
import java.util.List;

@Repository
public interface MilestoneRepository extends JpaRepository<Milestone, Long> {
    @Query("SELECT x FROM Milestone x WHERE x.isActive=true")
    List<Milestone> getAllMilestone();

    @Query("SELECT x FROM Milestone x WHERE x.isActive=true AND x.id=:id")
    Milestone getById(@Param("id") Long id);

}
