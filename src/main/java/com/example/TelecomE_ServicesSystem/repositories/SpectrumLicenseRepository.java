package com.example.TelecomE_ServicesSystem.repositories;
import com.example.TelecomE_ServicesSystem.entites.SpectrumLicense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.math.BigDecimal;
import java.util.List;

@Repository
public interface SpectrumLicenseRepository extends JpaRepository<SpectrumLicense, Long> {
    @Query("SELECT x FROM SpectrumLicense x WHERE x.isActive=true")
    List<SpectrumLicense> getAllSpectrumLicense();

    @Query("SELECT x FROM SpectrumLicense x WHERE x.isActive=true AND x.id=:id")
    SpectrumLicense getById(@Param("id") Long id);

    @Query("SELECT s FROM SpectrumLicense s WHERE s.isActive=true AND s.expiryDate BETWEEN :today AND :soon")
    List<SpectrumLicense> findExpiringSoon(@Param("today") LocalDate today, @Param("soon") LocalDate soon);

}
