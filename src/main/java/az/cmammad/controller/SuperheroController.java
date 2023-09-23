package az.cmammad.controller;

import az.cmammad.service.ProducerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api")
public class SuperheroController {

    private final ProducerService producerService;

    @GetMapping(value = "/publish")
    public ResponseEntity<String> sendMessageToKafkaTopic(@RequestParam("message") @Valid String message) {
        producerService.sendMessage(message);
        return ResponseEntity.ok().build();
    }
}
