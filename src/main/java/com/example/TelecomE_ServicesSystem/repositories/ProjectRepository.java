package com.example.TelecomE_ServicesSystem.repositories;
import com.example.TelecomE_ServicesSystem.entites.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    @Query("SELECT x FROM Project x WHERE x.isActive=true")
    List<Project> getAllProject();

    @Query("SELECT x FROM Project x WHERE x.isActive=true AND x.id=:id")
    Project getById(@Param("id") Long id);

    @Query("SELECT p FROM Project p WHERE p.isActive=true AND p.budget>:threshold")
    List<Project> findOverBudget(@Param("threshold") BigDecimal threshold);

}
