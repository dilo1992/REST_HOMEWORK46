package by.lobov.rest_homework46.service;



import by.lobov.rest_homework46.entity.BankBook;
import by.lobov.rest_homework46.entity.Payment;
import by.lobov.rest_homework46.exception.NotFoundException;
import by.lobov.rest_homework46.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final BankBookService bankBookService;


    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }

    public List<Payment> findPaymentsByPaymentReceiverNumber(Long paymentReceiverNumber) throws NotFoundException {
        try {
            return paymentRepository.findPaymentsByPaymentReceiverNumber(paymentReceiverNumber).orElseThrow(NoSuchElementException::new);
        } catch (NoSuchElementException e) {
            throw new NotFoundException("No one payment does not done");
        }
    }

//    public ResponseEntity save(@Valid Payment payment) {
//        paymentRepository.save(payment);
//        return ResponseEntity.ok("Save is done");
//    }

    @Transactional
    public Payment make(Long payerNumber, Long receiverNumber, Long sum) throws NotFoundException {
        try {
            BankBook bankBookPayer = bankBookService.findByNumber(payerNumber);
            BankBook bankBookReceiverPayment = bankBookService.findByNumber(receiverNumber);

            Payment payment = new Payment();
            payment.setSum(sum);
            payment.setPayer(bankBookPayer);
            payment.setPaymentReceiverNumber(receiverNumber);
            payment.setDateTime(LocalDateTime.now());
            paymentRepository.save(payment);
            bankBookPayer.addPayment(payment);
            bankBookReceiverPayment.addPayment(payment);
            bankBookPayer.setBalance(bankBookPayer.getBalance() - sum);
            bankBookReceiverPayment.setBalance(bankBookReceiverPayment.getBalance() + sum);
            return payment;
        } catch (NotFoundException e) {
            throw new NotFoundException("This payer or payment receiver are not found");
        }
    }
}
