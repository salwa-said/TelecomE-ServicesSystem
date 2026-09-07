package com.example.TelecomE_ServicesSystem.repositories;
import com.example.TelecomE_ServicesSystem.entites.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.math.BigDecimal;
import java.util.List;
@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long> {
    @Query("SELECT x FROM Vendor x WHERE x.isActive=true")
    List<Vendor> getAllVendor();

    @Query("SELECT x FROM Vendor x WHERE x.isActive=true AND x.id=:id")
    Vendor getById(@Param("id") Long id);

}
