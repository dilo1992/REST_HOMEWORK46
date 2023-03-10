package by.lobov.rest_homework46.repository;



import by.lobov.rest_homework46.entity.BankBook;
import by.lobov.rest_homework46.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankBookRepository extends JpaRepository<BankBook, Long> {

    List<BankBook> findBankBooksByOwner(Client client);

    BankBook findByNumber(Long number);

//    void deleteBankBookByNumber(Long number);
}
