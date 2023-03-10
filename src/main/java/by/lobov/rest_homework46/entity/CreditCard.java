package by.lobov.rest_homework46.entity;//package by.lobov.restapihomework46.entity;
//
//import jakarta.persistence.*;
//import jakarta.validation.Valid;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.hibernate.validator.constraints.CreditCardNumber;
//import org.hibernate.validator.constraints.Length;
//
//@Entity
//@Table(name = "credit_card_HW46")
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//public class CreditCard {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @CreditCardNumber
//    @Column(name = "credit_card_number")
//    private Long number;
//
//    @Length(min = 3, max = 3, message = "this number consist of 3 digits")
//    @Column(name = "credit_card_CVV")
//    private String CVV;
//
//    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
//    @Valid
//    private BankBook bankBook;
//
//}
