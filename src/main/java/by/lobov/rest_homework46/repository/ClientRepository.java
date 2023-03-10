package by.lobov.rest_homework46.repository;



import by.lobov.rest_homework46.entity.BankBook;
import by.lobov.rest_homework46.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findClientByFirstnameAndLastname(String firstname, String lastname);

    Optional<Client> findClientByBankBooks(BankBook bankBook);
}
