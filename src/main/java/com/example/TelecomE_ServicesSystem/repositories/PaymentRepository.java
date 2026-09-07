package com.example.TelecomE_ServicesSystem.repositories;
import com.example.TelecomE_ServicesSystem.entites.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.math.BigDecimal;
import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    @Query("SELECT x FROM Payment x WHERE x.isActive=true")
    List<Payment> getAllPayment();

    @Query("SELECT x FROM Payment x WHERE x.isActive=true AND x.id=:id")
    Payment getById(@Param("id") Long id);

}
