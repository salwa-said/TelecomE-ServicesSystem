package com.example.TelecomE_ServicesSystem.repositories;

import com.example.TelecomE_ServicesSystem.entites.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ServiceRepository  extends JpaRepository<Service, Long> {
    @Query("SELECT x FROM Service x WHERE x.isActive=true")
    List<Service> getAllService();

    @Query("SELECT x FROM Service x WHERE x.isActive=true AND x.id=:id")
    Service getById(@Param("id") Long id);

}

