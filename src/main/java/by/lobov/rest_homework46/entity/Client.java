package by.lobov.rest_homework46.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clients_HW46")
@Data
@RequiredArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstname")
    @NotBlank
    private String firstname;

    @Column(name = "lastname")
    @NotBlank
    private String lastname;

    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @ToString.Exclude
    private List<BankBook> bankBooks = new ArrayList<>();

    public void addBankBooksOfClient(BankBook bankBook) {
        bankBook.setOwner(this);
        bankBooks.add(bankBook);
    }

    public void deleteBankBookOfClient(BankBook bankBook) {
        bankBooks.remove(bankBook);
    }
//
//    @OneToMany(mappedBy = "number", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
//    @ToString.Exclude
//    public List<CreditCard> creditCards = new ArrayList<>();

//    public void addCreditCards(CreditCard creditCard) {
//        creditCard.setBankBook(this.getBankBooks().);
//    }
}
