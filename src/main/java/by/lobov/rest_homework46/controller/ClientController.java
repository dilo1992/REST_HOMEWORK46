package by.lobov.rest_homework46.controller;



import by.lobov.rest_homework46.entity.Client;
import by.lobov.rest_homework46.exception.ClientAlreadyExistException;
import by.lobov.rest_homework46.exception.NotFoundException;
import by.lobov.rest_homework46.service.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("client")
@Slf4j
public class ClientController {

    private final ClientService service;

    @PostMapping
    public ResponseEntity save(@RequestBody Client client) {
        try {
            service.save(client);
            return ResponseEntity.ok("User is save");
        } catch (ClientAlreadyExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("User is not saved");
        }
    }

    @GetMapping("{id}")
    public ResponseEntity findById(@PathVariable Long id) {
        try {
            Client client = service.findById(id);
            return ResponseEntity.ok(client);
        } catch (NotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("User is not found");
        }
    }

    @GetMapping()
    public ResponseEntity findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        try {
            service.delete(id);
            return ResponseEntity.ok("This client has been deleted");
        } catch (NotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
//    private final ClientService service;
//
//    @GetMapping
//    public List<Client> findAll() {
//        return service.findAll();
//    }
//
//    @GetMapping("{id}")
//    public Client findById(@PathVariable Long id) {
//        return service.findById(id);
//    }


