package by.lobov.rest_homework46.controller;


import by.lobov.rest_homework46.exception.NotFoundException;
import by.lobov.rest_homework46.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("payment")
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping
    public ResponseEntity findAll() {
        return ResponseEntity.ok(paymentService.findAll());
    }

    @GetMapping("/{receiverNumber}")
    public ResponseEntity findPaymentsByPaymentReceiverNumber(@PathVariable Long receiverNumber) {
        try {
            return ResponseEntity.ok(paymentService.findPaymentsByPaymentReceiverNumber(receiverNumber));
        } catch (NotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("new")
    public ResponseEntity create(@RequestParam Long bankBookOfPayerNumber,
                                 @RequestParam Long bankBookOfPaymentReceiverNumber,
                                 @RequestParam Long sum) throws NotFoundException {
        try {
            return ResponseEntity.ok(paymentService.make(bankBookOfPayerNumber, bankBookOfPaymentReceiverNumber, sum));
        } catch (NotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
