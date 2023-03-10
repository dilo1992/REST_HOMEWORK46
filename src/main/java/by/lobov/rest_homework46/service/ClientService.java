package by.lobov.rest_homework46.service;


import by.lobov.rest_homework46.entity.BankBook;
import by.lobov.rest_homework46.entity.Client;
import by.lobov.rest_homework46.exception.ClientAlreadyExistException;
import by.lobov.rest_homework46.exception.NotFoundException;
import by.lobov.rest_homework46.repository.BankBookRepository;
import by.lobov.rest_homework46.repository.ClientRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final BankBookRepository bankBookRepository;


    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public Client findById(Long id) throws NotFoundException {
        try {
            Client client = clientRepository.findById(id).orElseThrow(NullPointerException::new);
            return client;
        } catch (NullPointerException e) {
            throw new NotFoundException("This client is not found");
        }
    }

    public Client findClientByFirstnameAndLastname(String firstname, String lastname) throws NotFoundException {
        try {
            Client client = clientRepository.findClientByFirstnameAndLastname(firstname, lastname).orElseThrow(NullPointerException::new);
            return client;
        } catch (NullPointerException e) {
            throw new NotFoundException("This client is not found");
        }
    }

    public Client findClientByBankBook(Long bankBookNumber) throws NotFoundException {
        try {
            BankBook bankBook = bankBookRepository.findByNumber(bankBookNumber);
            Client client = clientRepository.findClientByBankBooks(bankBook).orElseThrow(NullPointerException::new);
            return client;
        } catch (NullPointerException e) {
            throw new NotFoundException("This client is not found");
        }
    }

    public Client save(@Valid Client client) throws ClientAlreadyExistException {
        if (clientRepository.findClientByFirstnameAndLastname(client.getFirstname(), client.getLastname()).isPresent()) {
            throw new ClientAlreadyExistException("this client has been saved");
        }
        return clientRepository.save(client);
    }

    public void delete(Long id) throws NotFoundException {
//        if (findClientByFirstnameAndLastname(client.getFirstname(), client.getLastname()) != null) {
        try {
            Client client = findById(id);
            clientRepository.delete(client);
        } catch (NotFoundException e) {
            throw new NotFoundException("This client is not found");
        }
    }


//    public List<Client> findAll() {
//        return clientRepository.findAll();
//    }
//
//    public Client findById(Long id) {
//        return clientRepository.findById(id).orElseThrow();
//    }
//
//    public Client findByFirstnameAndLastname(String firstname, String lastname) {
//        return clientRepository.findClientByFirstnameAndLastname(firstname, lastname);
//    }
//
//    public void save(Client client) {
////        if (clientRepository.findClientByFirstnameAndLastname(client.getFirstname(), client.getLastname()) != null) {
////            throw new RuntimeException();
//            //return ResponseEntity.badRequest().body("This client is contains in the database");
////        }
//        clientRepository.save(client);
////        ResponseEntity.ok("Client has been saved");
//    }
//
//    public void delete(Client client) {
//        clientRepository.delete(client);
//    }
}
