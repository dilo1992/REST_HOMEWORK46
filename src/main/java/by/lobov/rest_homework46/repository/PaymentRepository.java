package by.lobov.rest_homework46.repository;


import by.lobov.rest_homework46.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Optional<List<Payment>> findPaymentsByPaymentReceiverNumber(Long receiverNumber);
}
