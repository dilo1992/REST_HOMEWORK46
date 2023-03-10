package by.lobov.rest_homework46.service;


import by.lobov.rest_homework46.entity.BankBook;
import by.lobov.rest_homework46.entity.Client;
import by.lobov.rest_homework46.exception.NotFoundException;
import by.lobov.rest_homework46.repository.BankBookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BankBookService {

    private final BankBookRepository bankBookRepository;
    private final ClientService clientService;


    public List<BankBook> findAll() {
        return bankBookRepository.findAll();
    }

    public List<BankBook> findByOwner(Long id) throws NotFoundException {
        Client owner = clientService.findById(id);
        if (owner != null) {
            return bankBookRepository.findBankBooksByOwner(owner);
        } else {
            throw new NotFoundException("This human is not client our bank");
        }
    }

    public BankBook findByNumber(Long number) throws NotFoundException {
        if (bankBookRepository.findByNumber(number) != null) {
            return bankBookRepository.findByNumber(number);
        } else {
            throw new NotFoundException("BankBook with this number is not found");
        }
    }

    public void save(BankBook bankBook, Long clientId) throws NotFoundException {
        try {
            Client client = clientService.findById(clientId);
            client.addBankBooksOfClient(bankBook);
            bankBookRepository.save(bankBook);
        } catch (NullPointerException e) {
            throw new NotFoundException("This client is not found");
        }
    }

    @Transactional
    public void delete(BankBook bankBook) throws NotFoundException {
        try {
            bankBookRepository.findById(bankBook.getId()).orElseThrow(NullPointerException::new);
            log.info("OUR bankbook: {}", bankBook);
            bankBookRepository.delete(bankBook);
        } catch (NullPointerException e) {
            throw new NotFoundException("This bankBook has not been deleted because it did not find");
        }
    }
}
