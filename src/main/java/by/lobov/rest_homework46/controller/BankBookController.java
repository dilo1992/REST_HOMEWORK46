package by.lobov.rest_homework46.controller;


import by.lobov.rest_homework46.entity.BankBook;
import by.lobov.rest_homework46.exception.NotFoundException;
import by.lobov.rest_homework46.service.BankBookService;
import by.lobov.rest_homework46.service.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("bankbook")
@RequiredArgsConstructor
@Slf4j
public class BankBookController {

    private final BankBookService bankBookService;
    private final ClientService clientService;

    @GetMapping("all")
    public ResponseEntity<List<BankBook>> findAll() {
        return ResponseEntity.ok(bankBookService.findAll());
    }

    @GetMapping("/byOwnerId/{id}")
    public ResponseEntity findByOwner(@PathVariable String id) {
        try {
            return ResponseEntity.ok(bankBookService.findByOwner(Long.parseLong(id)));
        } catch (NotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/new/{clientId}")
    public ResponseEntity save(@RequestBody BankBook bankBook, @PathVariable Long clientId) {
        try {
            bankBookService.save(bankBook, clientId);
            return ResponseEntity.ok("This bankbook was saved");
        } catch (NotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(@RequestBody BankBook bankBook) {
        try {
            bankBookService.delete(bankBook);
            return ResponseEntity.ok("Bankbook has been deleted");
        } catch (NotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
