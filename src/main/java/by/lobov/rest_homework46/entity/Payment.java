package by.lobov.rest_homework46.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "payments_HW46")
@Data
@AllArgsConstructor
//@RequiredArgsConstructor
@NoArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sum_of_payment")
    @NotNull(message = "sum can`t be null")
    private Long sum;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.PERSIST)
    @Valid
    @JoinColumn(name = "payer")
    private BankBook payer;

    //    @ManyToOne(cascade = CascadeType.PERSIST)
//    @NotNull
    @Column(name = "receiver_bankbook_number")
    private Long paymentReceiverNumber;

    @Column(name = "date_of_operation")
    private LocalDateTime dateTime;
}
