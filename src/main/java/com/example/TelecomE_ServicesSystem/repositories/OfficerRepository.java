package com.example.TelecomE_ServicesSystem.repositories;
import com.example.TelecomE_ServicesSystem.entites.Officer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.math.BigDecimal;
import java.util.List;


@Repository
public interface OfficerRepository  extends JpaRepository<Officer, Long> {
    @Query("SELECT x FROM Officer x WHERE x.isActive=true")
    List<Officer> getAllOfficer();

    @Query("SELECT x FROM Officer x WHERE x.isActive=true AND x.id=:id")
    Officer getById(@Param("id") Long id);

}
