package by.lobov.rest_homework46.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bank_book_HW46")
@Data
@RequiredArgsConstructor
public class BankBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long number;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @Valid
    private Client owner;

    @Column(name = "balance")
    @Positive(message = "The balance of bankbook cannot be negative")
    private Long balance;

    @OneToMany(mappedBy = "payer", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<Payment> payments = new ArrayList<>();

    public void addPayment(Payment payment) {
        payment.setPayer(this);
        payments.add(payment);
    }
}
